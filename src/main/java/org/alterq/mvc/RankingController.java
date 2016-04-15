package org.alterq.mvc;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.domain.Ranking;
import org.alterq.domain.RoundBets;
import org.alterq.domain.RoundRanking;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.BetTools;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.validator.CompanyValidator;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "/myaccount/{id:.+}/{company}/{season}/{round}/ranking")
public class RankingController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoundRankingDao rankingDao;

	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private RoundBetDao roundBetDao;
	@Autowired
	private UserAlterQDao userAlterQDao;
	@Autowired
	private BetTools betTools;
	@Autowired
	private RoundRankingDao roundRankingDao;
	
	// Funciona
	private void updateRoundRanking(int company, int season, int round, UserAlterQ user, int points, int ones, int equs, int twos) {
		Ranking rnk = new Ranking();
		rnk.setId(new ObjectId().toHexString());
		rnk.setOnes(ones);
		rnk.setEqus(equs);
		rnk.setTwos(twos);
		rnk.setPoints(points);
		rnk.setUser(user.getId());
		rnk.setNick(user.getNick());

		if (round==0){ //Global Ranking
			RoundRanking usrRnk = roundRankingDao.findUserRanking(company, season, round, rnk.getUser());
			if (usrRnk!=null){
				rnk.setPoints(rnk.getPoints() + usrRnk.getRankings().get(0).getPoints());
				rnk.setOnes(rnk.getOnes() + usrRnk.getRankings().get(0).getOnes());
				rnk.setEqus(rnk.getEqus() + usrRnk.getRankings().get(0).getEqus());
				rnk.setTwos(rnk.getTwos() + usrRnk.getRankings().get(0).getTwos());
				roundRankingDao.updateRanking(company, season, round, rnk);
			}
			else{
				roundRankingDao.addRanking(company, season, round, rnk);
			}
		}
		else
			roundRankingDao.addRanking(company, season, round, rnk);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseDto getRanking(@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round
			, @PathVariable(value = "company") int company) {
		ResponseDto dto = new ResponseDto();
		RoundRanking roundRanking = new RoundRanking();
		try {
			// TODO control security by id user
			// TODO control security by id-company
			companyValidator.isCompanyOk(company);
			roundRanking = rankingDao.findRanking(company, season, round);
			roundRanking.setRound(round);
			roundRanking.setSeason(season);
			
			List<Ranking> lRankings = roundRanking.getRankings();

			Collections.sort(lRankings, new Comparator<Ranking>() {
				@Override
				public int compare(Ranking p1, Ranking p2) {
					if (p2.getPoints()>p1.getPoints())
						return 1;
					else if (p2.getPoints()<p1.getPoints())
						return -1;
					else if (p2.getTwos()>p1.getTwos())
						return 1;
					else if (p2.getTwos()<p1.getTwos())
						return -1;
					else if (p2.getEqus()>p1.getEqus())
						return 1;
					else if (p2.getEqus()<p1.getEqus())
						return -1;
					else if (p2.getOnes()>p1.getOnes())
						return 1;
					else if (p2.getOnes()<p1.getOnes())
						return -1;
					
					return p2.getUser().compareTo(p1.getUser());
				}
			});
			
			roundRanking.setRankings(lRankings);
			
			
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.GET_LAST_ROUND);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.GET_LAST_ROUND));
			dto.addErrorDto(error);
			dto.setRound(null);
		}
		dto.setRoundRanking(roundRanking);
		return dto;
	}

	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/updateRanking")
	public @ResponseBody ResponseDto updateRankig(@PathVariable int company, @PathVariable int season, @PathVariable int round) {

		ResponseDto response = new ResponseDto();
		UserAlterQ userAlterQ;
		boolean bFoundResult = false;
		
		log.debug("updateRanking: start");
		
		try {
			//Get Round Result
			String resultBet ="";
			RoundBets bean = roundBetDao.findResultBet(season, round, AlterQConstants.DEFECT_COMPANY);
			List<Bet> lBets = bean.getBets();
			for (Bet bet : lBets) {
				if (bet.getType()==BetTypeEnum.BET_RESULT.getValue()) {
					resultBet = bet.getBet();
					bFoundResult = true;
				}
			}
			
			if (bFoundResult)
			{
				//FOR EACH COMPANY
				//Step calculate ranking
				List<Company> companyList = companyDao.findAll();
				for (Company co : companyList) {
					if (co.getCompany() != AlterQConstants.DEFECT_COMPANY){
						// STEP 1: get users with bet
						String lastUser = null;
						UserAlterQ lastUserAlterQ = null;
						int[] vMaxAciertos = { 0, 0, 0, 0, 0 };
						boolean bUpdate = false;
	
						bean = roundBetDao.findRoundBetWithBets(season, round, co.getCompany());
	
						// OJO!! hay que ordenar las apuestas por usuario para que funcione.
						lBets = bean.getBets();
	
						Collections.sort(lBets, new Comparator<Bet>() {
							@Override
							public int compare(Bet p1, Bet p2) {
								// Aqui esta el truco, ahora comparamos p2 con p1 y no al
								// reves como antes
								return p2.getUser().compareTo(p1.getUser());
							}
						});
	
						for (Bet bet : lBets) {
							String apu = bet.getBet();
							String user = bet.getUser();
							userAlterQ = userAlterQDao.findById(user);
	
							if (userAlterQ == null) {
								log.debug("closeRound: user(" + user + ") Error resultBet user not find");
								// STEP 1.1.error - Send an email to the admin
								// ("ERROR resultBet user not find")
								continue;
							}
	
							// La apuesta global no se debe gestionar para el ranking
							//if (user.equals(getAdmin())) {
							if (bet.getType()==BetTypeEnum.BET_FINAL.getValue()) {
								if (lastUser != null)
									bUpdate = true;
								continue;
							}
							// La apuesta resultado no se debe gestionar para el ranking
							//if (user.equals(getAdmin())) {
							if (bet.getType()==BetTypeEnum.BET_RESULT.getValue()) {
								if (lastUser != null)
									bUpdate = true;
								continue;
							}
	
							// STEP 2: calc user right signs
							int[] vAciertos = betTools.calcUserRightSigns(resultBet, apu);
							if (vAciertos[0] == -1) {
								log.debug("closeRound: user(" + user + ") Error updating right sings");
								// STEP 2.1.error - Send an email to the admin
								// ("ERROR updating user rigth signs")
								continue;
							}
							if (lastUser != null) {
								if (user.equals(lastUser)) {
									if (vMaxAciertos[0] < vAciertos[0]) {
										vMaxAciertos[0] = vAciertos[0];
										vMaxAciertos[1] = vAciertos[1];
										vMaxAciertos[2] = vAciertos[2];
										vMaxAciertos[3] = vAciertos[3];
										vMaxAciertos[4] = vAciertos[4];
									}
									bUpdate = false;
								} else {
									bUpdate = true;
								}
							} else {
								lastUser = user;
								lastUserAlterQ = userAlterQ;
								vMaxAciertos[0] = vAciertos[0];
								vMaxAciertos[1] = vAciertos[1];
								vMaxAciertos[2] = vAciertos[2];
								vMaxAciertos[3] = vAciertos[3];
								vMaxAciertos[4] = vAciertos[4];
								bUpdate = false;
							}
	
							// Update iter user
							if (bUpdate) {
								// STEP 4: update round ranking
								updateRoundRanking(co.getCompany(), season, round, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
								// SETP 5: update global ranking (round=0)
								updateRoundRanking(co.getCompany(), season, 0, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
	
								lastUser = user;
								lastUserAlterQ = userAlterQ;
	
								vMaxAciertos[0] = vAciertos[0];
								vMaxAciertos[1] = vAciertos[1];
								vMaxAciertos[2] = vAciertos[2];
								vMaxAciertos[3] = vAciertos[3];
	
								bUpdate = false;
							}
						}
						// Update last user
						if (bUpdate) {
							// STEP 4: update round ranking
							updateRoundRanking(co.getCompany(), season, round, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
							// SETP 5: update global ranking (round=0)
							updateRoundRanking(co.getCompany(), season, 0, lastUserAlterQ, vMaxAciertos[0], vMaxAciertos[3], vMaxAciertos[2], vMaxAciertos[1]);
						}
					}
				}
			}
			else{
				response.addErrorDto("Ranking:updateRanking", "ResultNotFound");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.addErrorDto("Ranking:updateRanking", "SecurityException");
			e.printStackTrace();
		}		
		
		log.debug("updateRanking: end");
		return response;
	}
	
}
