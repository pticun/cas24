package org.alterq.mvc;


import java.util.Date;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.BetTools;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.validator.CompanyValidator;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/adminCompany" })
public class AdminCompanyController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private RoundBetDao roundBetDao;
	@Autowired
	private UserAlterQDao userAlterQDao;
	@Autowired
	private RoundRankingDao roundRankingDao;
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private UserAlterQSecurity userSecurity;
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private BetTools betTools;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init adminCompany.jsp");
		return "adminCompany";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/closeAC")
	public @ResponseBody ResponseDto closeRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		log.debug("closeRound: start");
		float priceBet=betTools.getPriceBet();

		try {
			userSecurity.isAdminUserInSession(cookieSession, company);

			// CLOSING PROCESS STEPS (Admin Company)
			//


			// STEP 1: Automatics Bets
			//
			// STEP 1.1 - Get Users with automatic bets
			List<UserAlterQ> lUsers = userAlterQDao.findUserWithTypeSpecialBets(company,BetTypeEnum.BET_AUTOMATIC);
			// STEP 1.2 - For each user do as bets as automatic bets (It has to
			// check user amount before make automatics bets)
			
			for (UserAlterQ user : lUsers) {
				int numApu=0;
				List<Bet> specialBet = user.getSpecialBets();
				for (Bet bet : specialBet) {
					if (bet.getType() == BetTypeEnum.BET_AUTOMATIC.getValue() && bet.getCompany()==company) {
						numApu = bet.getNumBets();
					}
				}
				// STEP 1.2.1 - Check User Balance
				float balance = new Float(user.getBalance()).floatValue();
				for (int i = 0; i < numApu; i++) {
					if (balance < priceBet) {
						log.debug("closeRound: user(" + user.getName() + ") No enough money for automatic bet");
						// STEP 1.2.1.error - Send an email to the user
						// ("NOT ENOUGH MONEY")
						continue;
					}
					// STEP 1.2.2 - Calc RandomBet
					String randomBet = betTools.randomBet();
					// STEP 1.2.3 - Make Automatic User Bet
					Bet bet = new Bet();
					bet.setPrice(betTools.getPriceBet());
					bet.setBet(randomBet);
					bet.setUser(user.getId());
					bet.setCompany(company);
					bet.setDateCreated(new Date());
					bet.setDateUpdated(new Date());
					bet.setNumBets(1);
					bet.setReduction("NNNNNNNNNNNNNN");
					bet.setType(BetTypeEnum.BET_NORMAL.getValue());
					bet.setId(new ObjectId().toHexString());
					
					roundBetDao.addBet(company, season, round, bet);
					//update new balance minus value bet
					balance-=priceBet;
				}

				// STEP 1.2.4 - Update User Balance
				try {
					user.setBalance(Float.toString((float) (balance)));
					userAlterQDao.save(user);
					/*
					 * if(userAlterQDao.getLastError() != null){
					 * log.debug("closeRound: user("
					 * +user.getName()+") Error updating balance."); //STEP
					 * 2.2.4.error - Send an email to the admin
					 * ("ERROR updating user balance") continue; }
					 */
				} catch (Exception e) {
					log.debug("closeRound: user(" + user.getName() + ") Error updating balance.");
					// STEP 1.2.4.error - Send an email to the admin
					// ("ERROR updating user balance")
					response.addErrorDto("AdminController:closeRound", " user(" + user.getName() + ") Error updating balance.");
					continue;
				}
			}

			// STEP 2: Fixed Bets 
			lUsers = userAlterQDao.findUserWithTypeSpecialBets(company,BetTypeEnum.BET_FIXED);
			for (UserAlterQ user : lUsers) {
				// loop for number of fixed bets
				float balance = new Float(user.getBalance()).floatValue();
				List<Bet> specialBets=user.getSpecialBets();
				for (Bet betSpecial : specialBets) {
					if (balance < priceBet) {
						log.debug("closeRound: user(" + user.getName() + ") No enough money for automatic bet");
						// STEP 1.2.1.error - Send an email to the user
						// ("NOT ENOUGH MONEY")
						continue;
					}
					// STEP 1.2.3 - Make Automatic User Bet
					if (betSpecial.getType() == BetTypeEnum.BET_FIXED.getValue() && betSpecial.getCompany()==company) {
						log.debug("bet:"+betSpecial.getBet());
						Bet bet = new Bet();
						bet.setPrice(betTools.getPriceBet());
						bet.setBet(betSpecial.getBet());
						bet.setUser(user.getId());
						bet.setCompany(company);
						bet.setDateCreated(new Date());
						bet.setDateUpdated(new Date());
						bet.setNumBets(1);
						bet.setReduction("NNNNNNNNNNNNNN");
						bet.setType(BetTypeEnum.BET_NORMAL.getValue());
						bet.setId(new ObjectId().toHexString());
						
						roundBetDao.addBet(company, season, round, bet);
						//update new balance minus value bet
						balance-=priceBet;
					}
				}
				// STEP 1.2.4 - Update User Balance
				try {
					user.setBalance(Float.toString((float) (balance)));
					userAlterQDao.save(user);
					/*
					 * if(userAlterQDao.getLastError() != null){
					 * log.debug("closeRound: user("
					 * +user.getName()+") Error updating balance."); //STEP
					 * 2.2.4.error - Send an email to the admin
					 * ("ERROR updating user balance") continue; }
					 */
				} catch (Exception e) {
					log.debug("closeRound: user(" + user.getName() + ") Error updating balance.");
					// STEP 1.2.4.error - Send an email to the admin
					// ("ERROR updating user balance")
					response.addErrorDto("AdminController:closeRound", " user(" + user.getName() + ") Error updating balance.");
					continue;
				}

			}

			
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_ADMIN);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_ADMIN));
			response.addErrorDto(error);
			log.error(ExceptionUtils.getStackTrace(e));
		}

		log.debug("closeRound: end");
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{id:.+}/{company}/{season}/{round}/summary")
	public @ResponseBody ResponseDto summary(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable(value = "id") String id, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		String responseString = new String();
		RoundBets roundBets;
		
		log.debug("getSummary: start");
		try {
			userSecurity.isAdminUserInSession(cookieSession, company);
			companyValidator.isCompanyOk(company);

			roundBets = roundBetDao.findRoundBetWithBets(season, round, company);

			response.setRoundBet(roundBets);
			
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_ADMIN);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_ADMIN));
			response.addErrorDto(error);
			log.error(ExceptionUtils.getStackTrace(e));
		}

		log.debug("getSummary: end");
		return response;		
	}
		

}
