package org.alterq.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alterq.converter.UserAlterQConverter;
import org.alterq.domain.Account;
import org.alterq.domain.AccountingEntry;
import org.alterq.domain.AdminData;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.domain.Game;
import org.alterq.domain.Prize;
import org.alterq.domain.Ranking;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.RoundRanking;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.MailQueueDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.AccountingDao;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.RoundResultsDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.BetTools;
import org.alterq.util.CalculateRigths;
import org.alterq.util.MailTools;
import org.alterq.util.UserTools;
import org.alterq.util.enumeration.AccountTypeEnum;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.CompanyTypeEnum;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.util.enumeration.QueueMailEnum;
import org.alterq.validator.CompanyValidator;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.channel.ProcessMailQueue;
import org.arch.core.file.BetElectronicFile;
import org.arch.core.file.HeaderBetElectronicFile;
import org.arch.core.file.RegistroBetElectronicFile;
import org.arch.core.mail.SendMail;
import org.arch.core.mail.SendMailer;
import org.arch.core.util.CoreUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
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
	private RolCompanySecurity rolCompanySecurity;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	private BetTools betTools;
	@Autowired
	private CalculateRigths calculateRights;
	@Autowired
	private RoundResultsDao roundResultsDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private AccountingDao accountingDao;
	@Autowired
	MailTools mailTools;
	@Autowired
	ProcessMailQueue processMailQueue;
	@Autowired
	private UserAlterQConverter userAlterQConverter;

	private static int doubles = 0;
	private static int triples = 0;


	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init admin.jsp");
		return "admin";
	}

	// pendiente de revision para incorportar el TYPE de la quiniela
	private void calcBasicDoublesAndTriples(int numBets, int type) {
		int nBets = betTools.getQuinielaNumBets(type);

		if ((type == 0) || (numBets < 9)) {
			triples = (int) ((numBets < 3) ? 0 : (Math.log(numBets) / Math.log(3)));
			doubles = (int) (((numBets - Math.pow(3, triples)) < 2) ? 0 : (Math.log((int) (numBets / Math.pow(3, triples))) / Math.log(2)));
		} else {// It's a quiniela with reduction
			triples = (int) ((numBets < (3 * nBets)) ? 0 : (Math.log(numBets) / Math.log(3 * nBets)));
			doubles = (int) (((numBets - Math.pow((3 * nBets), triples)) < (2 * nBets)) ? 0 : (Math.log((int) (numBets / Math.pow((3 * nBets), triples))) / Math.log((2 * nBets))));
		}
	}

	private static void calcFinalDoublesAndTriples(int type) {
		switch (type) {
		case 1: // Reduccion Primera (4T)
			triples += 4;
			break;
		case 2: // Reduccion Segunda (7D)
			doubles += 7;
			break;
		case 3: // Reduccion Tercera (3D + 3T)
			triples += 3;
			doubles += 3;
			break;
		case 4: // Reduccion Cuarta (6D + 2T)
			triples += 2;
			doubles += 6;
			break;
		case 5: // Reduccion Quinta (8T)
			triples += 8;
			break;

		default:
		}
	}

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

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/open")
	public @ResponseBody ResponseDto openRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		AdminData adminData = null;
		ResponseDto response = new ResponseDto();

		log.debug("AdminController:openRound: start");
		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);
			
			//SuperAdmin works always in company = 0
			adminData = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);

			// OPENING PROCESS STEPS
			// ---------------------

			// STEP 1: update generalData

			// if exist, update active=true
			if (adminData != null) {
				log.debug("AdminController:openRound: active=true");
				if ((AlterQConstants.DEFECT_COMPANY == adminData.getCompany()) && (season == adminData.getSeason()) && (round == adminData.getRound()) && (adminData.isActive())) {
					log.debug("AdminController:openRound: Round is already actived");
				} else {
					adminData.setCompany(AlterQConstants.DEFECT_ADMINDATA);
					adminData.setSeason(season);
					adminData.setRound(round);
					adminData.setActive(true);
					adminDataDao.update(adminData);
				}
			} else {// if not exist, create a new generalData (active=true)
				log.debug("AdminController:openRound: new generalData active=true");
				adminData = new AdminData();
				adminData.setActive(true);
				adminData.setCompany(AlterQConstants.DEFECT_COMPANY);
				adminData.setRound(round);
				adminData.setSeason(season);

				adminDataDao.add(adminData);
			}

			//FOR EACH COMPANY
			List<Company> companyList = companyDao.findAll();
			for (Company co : companyList) {
				// STEP 2: Create roundData (RoundBets collection)
				//RoundBets roundBets = roundBetDao.findAllBets(season, round, company);
				RoundBets roundBets = roundBetDao.findRoundBet(season, round, co.getCompany());
				// Check if exist this roundBet
				if (roundBets == null) {
					RoundBets bean = new RoundBets();
					bean.setId(new ObjectId().toHexString());
					bean.setCompany(co.getCompany());
					bean.setRound(round);
					bean.setSeason(season);
					roundBetDao.add(bean); 
				}
			}
		} catch (SecurityException e) {
			response.addErrorDto("AdminController:openRound", "SecurityException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.debug("AdminController:openRound: end");
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/close")
	public @ResponseBody ResponseDto closeRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		AdminData adminData = null;
		ResponseDto response = new ResponseDto();
		log.debug("AdminController:closeRound: start");
		float priceBet = betTools.getPriceBet();
		boolean bFinalCompanyBet = false;
		UserAlterQ userAlterQ;
		float betRefunded = 0;
		Account account = new Account();

		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);
			adminData = adminDataDao.findById(AlterQConstants.DEFECT_COMPANY);

			// CLOSING PROCESS STEPS
			// STEP 1: if exist, update active=false
			//
			if (adminData != null) {
				log.debug("AdminController:closeRound: active=true");
				adminData.setActive(false);
				// generalData.setActive(false);
				// dao.update(generalData);
				adminDataDao.update(adminData);
				
				//STEP 2: Make automatics bets of DEFECT_COMPANY
				// STEP 2.1 - Get Users with automatic bets
				List<UserAlterQ> lUsers = userAlterQDao.findUserWithTypeSpecialBets(AlterQConstants.DEFECT_COMPANY, BetTypeEnum.BET_AUTOMATIC);
				// STEP 2.2 - For each user do as bets as automatic bets (It has to
				// check user amount before make automatics bets)

				for (UserAlterQ user : lUsers) {
					int numApu = 0;
					List<Bet> specialBet = user.getSpecialBets();
					for (Bet bet : specialBet) {
						if (bet.getType() == BetTypeEnum.BET_AUTOMATIC.getValue() && bet.getCompany() == AlterQConstants.DEFECT_COMPANY) {
							numApu = bet.getNumBets();
						}
					}
					
					//if user doesn't have automatic bets we go to next user
					if (numApu==0)
						continue;
					
					// STEP 2.2.1 - Check User Balance
					float balance = new Float(user.getBalance()).floatValue();
					for (int i = 0; i < numApu; i++) {
						if (balance < priceBet) {
							log.debug("AdminController:closeRound: user(" + user.getName() + ") No enough money for automatic bet");
							// STEP 1.2.1.error - Send an email to the user
							// ("NOT ENOUGH MONEY")
							continue;
						}
						// STEP 2.2.2 - Calc RandomBet
						String randomBet = betTools.randomBet();
						// STEP 2.2.3 - Make Automatic User Bet
						Bet bet = new Bet();
						bet.setPrice(betTools.getPriceBet());
						bet.setBet(randomBet);
						bet.setUser(user.getId());
						bet.setCompany(AlterQConstants.DEFECT_COMPANY);
						bet.setDateCreated(new Date());
						bet.setDateUpdated(new Date());
						bet.setNumBets(1);
						bet.setReduction("NNNNNNNNNNNNNN");
						bet.setType(BetTypeEnum.BET_NORMAL.getValue());
						bet.setId(new ObjectId().toHexString());

						roundBetDao.addBet(AlterQConstants.DEFECT_COMPANY, season, round, bet);
						// update new balance minus value bet
						balance -= priceBet;
					}

					// STEP 2.2.4 - Update User Balance
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
						log.debug("AdminController:closeRound: user(" + user.getName() + ") Error updating balance.");
						// STEP 1.2.4.error - Send an email to the admin
						// ("ERROR updating user balance")
						response.addErrorDto("AdminController:AdminController:closeRound", " user(" + user.getName() + ") Error updating balance.");
						continue;
					}
				}
				//STEP 3 - Check that all Groups have Final Bet
				//Get All Companies
				List<Company> companyList = companyDao.findAll();
				
				//Loop for Companies
				for (Company co : companyList) {
					bFinalCompanyBet = false;
					//Direct bet
					if (co.getCompany() != AlterQConstants.DEFECT_COMPANY){

						RoundBets bean = roundBetDao.findRoundBetWithBets(season, round, co.getCompany());

						//Get All Bets
						List<Bet> lBets = bean.getBets();
						
						//Loop for Bets
						for (Bet bet : lBets){
							if (bet.getType() == BetTypeEnum.BET_FINAL.getValue())
							{
								bFinalCompanyBet = true;
								
							}
						}
						
						//We have to delete user bets and refund their money
						if ((lBets.size()>0) && (!bFinalCompanyBet))
						{
							for (Bet bet : lBets){
								if (bet.getType() == BetTypeEnum.BET_NORMAL.getValue())
								{
									String user = bet.getUser();
									userAlterQ = userAlterQDao.findById(user);

									if (userAlterQ == null) {
										log.debug("AdminController:closeRound: user(" + user + ") Error resultBet user not find");
										// STEP 1.1.error - Send an email to the admin
										// ("ERROR resultBet user not find")
										continue;
									}
									betRefunded = bet.getNumBets() * betTools.getPriceBet();
									//Update Balance User
									userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) + betRefunded));
									userAlterQDao.save(userAlterQ);
									
									//******************************************
									//PENDING(ACCOUNTING ENTRY) - User betReward
									//******************************************
									account.setAmount(Double.toString(betRefunded));
									account.setCompany(co.getCompany());
									account.setDate(new Date());
									account.setDescription("Devolución "+ co.getNick() +"Num Apu: "+bet.getNumBets()+" T "+season+"/"+(season+1-2000)+" J "+round);
									account.setRound(round);
									account.setSeason(season);
									account.setType(new Integer(AccountTypeEnum.ACCOUNT_REFUND.getValue()));
									account.setUser(user);
									
									accountingDao.add(account);
									
									log.debug("AdminController:closeRound: (ACCOUNTING ENTRY) user:" + user + " balance: "+userAlterQ.getBalance()+" betRefunded="+betRefunded);
									
								}
							}
						}

					}
				}
				
			} else { // there is not round to close
				response.addErrorDto("AdminController:closeRound", "There is not round to close");
				return response;
			}
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:closeRound", "SecurityException");
			e1.printStackTrace();
		}

		log.debug("AdminController:closeRound: end");
		return response;
	}


	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/{season}/{round}/{resultBet}/resultBet")
	public @ResponseBody ResponseDto resultBetRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int season, @PathVariable int round, @PathVariable String resultBet) {
		ResponseDto response = new ResponseDto();
		UserAlterQ userAlterQ;
		int company = AlterQConstants.DEFECT_COMPANY; //company default
		
		RoundBets rbAdminResultBet;

		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);
			
			// RESULT ROUND STEPS
			// ---------------------
			
			// STEP 0: add ResultBelt
			rbAdminResultBet = roundBetDao.findRoundBet(season, round, company);
			// Translate result from "1X2" to "421"
			resultBet = betTools.translateResult1x2(resultBet);

			
			if (rbAdminResultBet != null){
				UserAlterQ userAdmin = userAlterQDao.findSuperAdmin();
				Bet betResult = new Bet();
				betResult.setId(new ObjectId().toHexString());
				betResult.setBet(resultBet);
				//bAux.setUser(user);
				betResult.setCompany(AlterQConstants.DEFECT_COMPANY);
				betResult.setDateCreated(new Date());
				betResult.setDateUpdated(new Date());
				betResult.setType(BetTypeEnum.BET_RESULT.getValue());
				betResult.setUser(userAdmin.getId());
				roundBetDao.addBet(company, season, round, betResult);
			}
			
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

					RoundBets bean = roundBetDao.findRoundBetWithBets(season, round, co.getCompany());

					//Hay que controlar si la company tiene apuestas
					if (bean==null){
						log.debug("resultBetRound: company(" + co.getCompany() + ", "+co.getNick()+") sin apuestas.");
						continue;
					}
					// OJO!! hay que ordenar las apuestas por usuario para que funcione.
					List<Bet> lBets = bean.getBets();

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:addMatches", "SecurityException");
			e.printStackTrace();
		}

		return response;
	}

	/*
	 * PENDIENTE
	 * 
	 * Hay que mirar todas las apuestas, comprobar el numero de aciertos que
	 * tiene y repartir los premios en funcion a la cantidad de premios
	 * obtenidos
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/season/{season}/round/{round}/prizesBet")
	// public @ResponseBody ResponseDto prizesRound(@CookieValue(value =
	// "session", defaultValue = "") String cookieSession, HttpServletRequest
	// request, @RequestBody PrizesRound prizesRound) {
	public @ResponseBody ResponseDto prizesRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		RoundBets roundBets;
		float betReward = 0;
		UserAlterQ userAlterQ;
		int countPrizes[] = { 0, 0, 0, 0, 0 };
		int numCompanyBets = 0;
		String betResult = null;
		double rewardDivided = 0;
		//Account account = new Account();
		//String eMail;
		ArrayList<String> eMail = new ArrayList<String>();
		boolean firstFinalBet = true;

		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);

			List<Prize> lPrizes = null;
			Map<String, String[]> parameters = request.getParameterMap();

			//Get Result Bet
			roundBets = roundBetDao.findRoundBetWithBets(season, round, AlterQConstants.DEFECT_COMPANY);
			List<Bet> lBetsDef = roundBets.getBets();
			if (lBetsDef == null)
			{
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.GENERIC_ERROR);
				error.setStringError("Reparto premios (Error: no se ha podido obtener la apuestas)");
				response.addErrorDto(error);
				response.setUserAlterQ(null);
				return response;
				
			}
			
			for (Bet bet : lBetsDef) {
				if (bet.getType() != BetTypeEnum.BET_RESULT.getValue())
					continue;
				else
					betResult = bet.getBet();
			}
			//Check if Exist Bet Result
			if (betResult == null)
			{
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.GENERIC_ERROR);
				error.setStringError("Reparto premios (Error: no existe la apuesta Resultado)");
				response.addErrorDto(error);
				response.setUserAlterQ(null);
				return response;
			}
			
			//translate Result Bet
			betResult = betTools.translateResultTo1x2(betResult);
			
			//Get All Companies
			List<Company> companyList = companyDao.findAll();
			
			//Loop for Companies
			for (Company co : companyList) {
				firstFinalBet = true;
				//Direct bet
				if (co.getCompany() == AlterQConstants.DEFECT_COMPANY){

					RoundBets bean = roundBetDao.findRoundBetWithBets(season, round, co.getCompany());

					//Get All Bets
					List<Bet> lBets = bean.getBets();
					
					//Loop for Bets
					for (Bet bet : lBets){
						if ((bet.getType() == BetTypeEnum.BET_NORMAL.getValue()) || (bet.getType() == BetTypeEnum.BET_FIXED.getValue()) || (bet.getType() == BetTypeEnum.BET_AUTOMATIC.getValue()))
						{
							String user = bet.getUser();
	
							//Get User
							userAlterQ = userAlterQDao.findById(user);
	
							if (userAlterQ == null) {
								log.debug("prizesRound: user(" + user + ") Error resultBet user not find");
								// STEP 1.1.error - Send an email to the admin
								// ("ERROR pricesRound user not find")
								continue;
							}
							
							lPrizes = new ArrayList<Prize>();
							
							//Calc Bet Prizes
							countPrizes = calculateRights.calculate(betResult, bet.getBet(), bet.getReduction(), bet.getTypeReduction());
							for (int i = 0; i <= 5; i++) {
								Prize priceTmp = new Prize();
								priceTmp.setId(i + 10);
								priceTmp.setCount(countPrizes[i]);
								priceTmp.setAmount(Float.parseFloat(parameters.get("prize" + (i + 10))[0]));
								lPrizes.add(priceTmp);
								log.debug("prizesRound: ID:"+(i + 10)+" COUNT:"+countPrizes[i]+" AMOUNT:"+Float.parseFloat(parameters.get("prize" + (i + 10))[0]));
							}
	
							bet.setPrizes(lPrizes);
	
							betReward = 0;
	
							//Prizes larger than 2.500€, we have to discount 20% taxes 
							for (Prize prize : lPrizes) {
								if (prize.getAmount() <= 2500)
									betReward += prize.getAmount() * prize.getCount();
								else{
									betReward += (prize.getAmount() - ((prize.getAmount() - 2500)*20/100)) * prize.getCount();
									log.debug("prizesRound: AFTER TAXES - ID:"+(prize.getId())+" COUNT:"+prize.getCount()+" AMOUNT:"+ prize.getAmount());
								}
							}
	
							if (betReward > 0)
							{
								//Update Balance User
								userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) + betReward));
								userAlterQDao.save(userAlterQ);
								//******************************************
								//PENDING(ACCOUNTING ENTRY) - User betReward
								//******************************************
								Account account = new Account();
								account.setAmount(Double.toString(betReward));
								account.setCompany(co.getCompany());
								account.setDate(new Date());
								account.setDescription("Premio T "+season+"/"+(season+1-2000)+" J "+round);
								account.setRound(round);
								account.setSeason(season);
								account.setType(new Integer(AccountTypeEnum.ACCOUNT_PRIZE.getValue()));
								account.setUser(user);
								
								accountingDao.add(account);
								
								log.debug("prizesRound: (ACCOUNTING ENTRY) user:" + user + " balance: "+userAlterQ.getBalance()+" betReward="+betReward);

								//Send Mail to the user
								
								eMail.add(userAlterQ.getId());
								
								if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
									eMail.add("quinielagold@gmail.com");
								}
								

								MailQueueDto mailDto=new MailQueueDto();
								mailDto.setType(QueueMailEnum.Q_RESULTUSERMAIL);
								
								RoundBets processRoundBetMail=new RoundBets();
								processRoundBetMail.setCompany(co.getCompany());
								processRoundBetMail.setRound(round);
								processRoundBetMail.setSeason(season);
								processRoundBetMail.setReward(betReward);
								//bet.setNumBets(numCompanyBets);

								List<Bet> finalBet=new ArrayList<Bet>();
								finalBet.add(bet);
								processRoundBetMail.setBets(finalBet);
								
								mailDto.setRoundBet(processRoundBetMail);
								mailDto.setCco(eMail);
								
								processMailQueue.process(mailDto);
								
								
								
								//End Send Mail to the user
							}
						}
					}
					
				}else{
					numCompanyBets=0;
					RoundBets bean = roundBetDao.findRoundBetWithBets(season, round, co.getCompany());
					
					if(bean ==null)
					{
						log.debug("prizesRound: company(" + co.getCompany() + ", "+co.getNick()+") sin apuestas.");	
						continue;
					}
					//TODO create new method to get number BET_NORMAL,BET_FIXED??
					List<Bet> lBets = bean.getBets();
					//Get Number of Company Bets
					for (Bet bet : lBets) {
						if ((bet.getType() == BetTypeEnum.BET_NORMAL.getValue()))
							numCompanyBets+=bet.getNumBets();
					}
					
					//Get Final Bets
					for (Bet bet : lBets) {
						if (bet.getType() == BetTypeEnum.BET_FINAL.getValue()){
							//translate Result Bet
							//betResult = betTools.translateResultTo1x2(betResult);
							
							lPrizes = new ArrayList<Prize>();
							
							//Calc Bet Prizes
							countPrizes = calculateRights.calculate(betResult, bet.getBet(), bet.getReduction(), bet.getTypeReduction());
							for (int i = 0; i <= 5; i++) {
								Prize priceTmp = new Prize();
								priceTmp.setId(i + 10);
								priceTmp.setCount(countPrizes[i]);
								priceTmp.setAmount(Float.parseFloat(parameters.get("prize" + (i + 10))[0]));
								lPrizes.add(priceTmp);
								log.debug("prizesRound: ID:"+(i + 10)+" COUNT:"+countPrizes[i]+" AMOUNT:"+Float.parseFloat(parameters.get("prize" + (i + 10))[0]));								
							}

							bet.setPrizes(lPrizes);

							betReward = 0;

							//Prizes larger than 2.500€ have to discount 20% taxes 
							for (Prize prize : lPrizes) {
								if (prize.getAmount() <= 2500)
									betReward += prize.getAmount() * prize.getCount();
								else{
									betReward += (prize.getAmount() - ((prize.getAmount() - 2500)*20/100)) * prize.getCount();
									log.debug("prizesRound: AFTER TAXES - ID:"+(prize.getId())+" COUNT:"+prize.getCount()+" AMOUNT:"+ prize.getAmount());
								}
							}
							
							
							//Add Company Round Bet JackPot
							float jackPot = bean.getJackpot();
							
							//Divide Reward between All Users with Bet
							if (!firstFinalBet)
								jackPot = 0;
								
							if (jackPot > 0)
							{
								Account account = new Account();
								account.setAmount(Double.toString(jackPot));
								account.setCompany(co.getCompany());
								account.setDate(new Date());
								account.setDescription("Bote T "+season+"/"+(season+1-2000)+" J "+round);
								account.setRound(round);
								account.setSeason(season);
								account.setType(new Integer(AccountTypeEnum.ACCOUNT_JACKPOT.getValue()));
								account.setUser(bet.getUser());
								
								accountingDao.add(account);
								
							}
							
							rewardDivided = (betReward + jackPot) / numCompanyBets;
							
							if (rewardDivided > 0)
							{
								//Loop for Bets (normal & automatics & fixes)
								for (Bet bet2 : lBets) {
									String user = bet2.getUser();
									if ((bet2.getType() == BetTypeEnum.BET_NORMAL.getValue()))
									{
										//Get User
										userAlterQ = userAlterQDao.findById(user);
	
										if (userAlterQ == null) {
											log.debug("pricesRound: user(" + user + ") Error resultBet user not find");
											// STEP 1.1.error - Send an email to the admin
											// ("ERROR pricesRound user not find")
											continue;
										}
										//Update Balance User
										userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) + rewardDivided));
										userAlterQDao.save(userAlterQ);						
										//******************************************
										//PENDING(ACCOUNTING ENTRY) - User betReward
										//******************************************
										Account account = new Account();
										account.setAmount(Double.toString(rewardDivided));
										account.setCompany(co.getCompany());
										account.setDate(new Date());
										account.setDescription("Premio T "+season+"/"+(season+1-2000)+" J "+round);
										account.setRound(round);
										account.setSeason(season);
										account.setType(new Integer(AccountTypeEnum.ACCOUNT_PRIZE.getValue()));
										account.setUser(user);
										
										accountingDao.add(account);
										
										log.debug("pricesRound: (ACCOUNTING ENTRY) user:" + user + " balance: "+userAlterQ.getBalance()+" rewardDivided="+rewardDivided);
									}
								}
								//Send Results Mail
								ArrayList<String> ccoMail = new ArrayList<String>();
								ccoMail = mailTools.getCCOFinalBet(co.getCompany(),season,round);
								log.debug("ccoMail="+ccoMail);
								
								if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
									ccoMail.add("quinielagold@gmail.com");
								}
								
	
								//sendMailer.sendResultsMail(cco, round, jackPot, betReward, rewardDivided, lPrizes);
								MailQueueDto mailDto=new MailQueueDto();
								mailDto.setType(QueueMailEnum.Q_RESULTSMAIL);
								
								RoundBets processRoundBetMail=new RoundBets();
								processRoundBetMail.setCompany(co.getCompany());
								processRoundBetMail.setRound(round);
								processRoundBetMail.setSeason(season);
								processRoundBetMail.setJackpot(jackPot);
								processRoundBetMail.setReward(betReward);
								bet.setNumBets(numCompanyBets);
	
								List<Bet> finalBet=new ArrayList<Bet>();
								finalBet.add(bet);
								processRoundBetMail.setBets(finalBet);
								
								mailDto.setRoundBet(processRoundBetMail);
								mailDto.setCco(ccoMail);
								
								processMailQueue.process(mailDto);
							}
							
							firstFinalBet = false;
							
						}
					}
					//Update RoundBet reward
					bean.setReward(roundBets.getReward() + betReward);
					roundBetDao.update(bean);
				}
				
			}
			
			//Update RoundBet reward
			//roundBets.setReward(roundBets.getReward() + betReward);
			//roundBetDao.update(roundBets);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:prizesRound", "SecurityException");
			e.printStackTrace();
		}

		return response;
	}

	// @RequestMapping(method = RequestMethod.POST, produces =
	// "application/json", value =
	// "/company/{company}/season/{season}/round/{round}/{local01}/{visitor01}/{local02}/{visitor02}/{local03}/{visitor03}/{local04}/{visitor04}/{local05}/{visitor05}/{local06}/{visitor06}/{local07}/{visitor07}/{local08}/{visitor08}/{local09}/{visitor09}/{local10}/{visitor10}/{local11}/{visitor11}/{local12}/{visitor12}/{local13}/{visitor13}/{local14}/{visitor14}/{local15}/{visitor15}")
	// public @ResponseBody
	// ResponseDto addRoundGames(@PathVariable int company, @PathVariable int
	// season, @PathVariable int round, @PathVariable String local01,
	// @PathVariable String visitor01, @PathVariable String local02,
	// @PathVariable String visitor02, @PathVariable String local03,
	// @PathVariable String visitor03, @PathVariable String local04,
	// @PathVariable String visitor04, @PathVariable String local05,
	// @PathVariable String visitor05, @PathVariable String local06,
	// @PathVariable String visitor06, @PathVariable String local07,
	// @PathVariable String visitor07, @PathVariable String local08,
	// @PathVariable String visitor08, @PathVariable String local09,
	// @PathVariable String visitor09, @PathVariable String local10,
	// @PathVariable String visitor10, @PathVariable String local11,
	// @PathVariable String visitor11, @PathVariable String local12,
	// @PathVariable String visitor12, @PathVariable String local13,
	// @PathVariable String visitor13, @PathVariable String local14,
	// @PathVariable String visitor14, @PathVariable String local15,
	// @PathVariable String visitor15) {
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/matches")
	public @ResponseBody ResponseDto addMatches(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		Round myRound = new Round();
		Round tmpRound;

		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);
			Map<String, String[]> parameters = request.getParameterMap();
			List<Game> lGames = new ArrayList<Game>();
			//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = df.parse(parameters.get("dateMatches")[0]);

			for (int i = 1; i <= 15; i++) {
				Game gameTmp = new Game();
				gameTmp.setId(i);
				gameTmp.setPlayer1(parameters.get("matchlocal" + ((i < 10) ? "0" + i : i))[0]);
				gameTmp.setPlayer2(parameters.get("matchvisit" + ((i < 10) ? "0" + i : i))[0]);
				lGames.add(gameTmp);
			}

			myRound.setSeason(season);
			myRound.setRound(round);
			myRound.setDateRound(dt);
			myRound.setGames(lGames);

			tmpRound = roundDao.findBySeasonRound(season, round);

			if (tmpRound != null) {
				roundDao.deleteRound(season, round);
			}

			roundDao.addRound(myRound);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.addErrorDto("AdminController:addMatches", "SecurityException");
			e.printStackTrace();
		} catch (Exception e) {
			response.addErrorDto("AdminController:addMatches", "Exception");
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/company/{company}/user/{user}/balance")
	public @ResponseBody ResponseDto updateBalanceUser(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int company, @PathVariable String user,
			 @RequestBody Object   obj
			) {
		ResponseDto response = new ResponseDto();
		UserAlterQ userAlterQ = new UserAlterQ();
		Account account = new Account();
		AdminData ad;

		try {
			String balance=(String)((Map)obj).get("balance");
			String balanceIncrease=(String)((Map)obj).get("increaseBalance");
			String balanceDecrease=(String)((Map)obj).get("decreaseBalance");;
			
			userSecurity.isSuperAdminUserInSession(cookieSession);
			userAlterQ.setId(user);
			userSecurity.notExistsUserAlterQ(userAlterQ);

			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
			
			account.setDate(new Date());
			account.setUser(user);
			account.setCompany(company);
			account.setRound(ad.getRound());
			account.setSeason(ad.getSeason());
			

			userAlterQ = userAlterQDao.findById(user);

			if (balance!=null && (balance.compareTo("")!=0) && (balance.compareTo("none")!=0)){
				userAlterQ.setBalance(Double.toString(Double.parseDouble(balance)));
				account.setType(new Integer(AccountTypeEnum.ACCOUNT_INITIAL.getValue()));
				account.setAmount(balance);
				account.setDescription("Saldo inicial.");
			}
			else if (balanceIncrease!=null && (balanceIncrease.compareTo("")!=0) && (balanceIncrease.compareTo("none")!=0)){
				userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) + Double.parseDouble(balanceIncrease)));
				account.setType(new Integer(AccountTypeEnum.ACCOUNT_DEPOSIT.getValue()));
				account.setAmount(balanceIncrease);
				account.setDescription("Saldo incrementado.");
			}
			else if (balanceDecrease!=null && (balanceDecrease.compareTo("")!=0) && (balanceDecrease.compareTo("none")!=0)){
				userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) - Double.parseDouble(balanceDecrease)));
				account.setType(new Integer(AccountTypeEnum.ACCOUNT_WITHDRAWAL.getValue()));
				account.setAmount(balanceDecrease);
				account.setDescription("Saldo decrementado.");
			}
			
			userAlterQDao.updateBalance(userAlterQ);
			
			accountingDao.add(account);
			
			response.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userAlterQ));

		} catch (SecurityException e) {
			response.addErrorDto("AdminController:updateBalance", "SecurityException");
			e.printStackTrace();
		} catch (Exception e) {
			response.addErrorDto("AdminController:updateBalance", "Generic Update Error");
			e.printStackTrace();
		}

		
		return response;
	}
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/user/{user}/balance/{balance}/{balanceIncrease}/{balanceDecrease}/updateBalanceUser")
	public @ResponseBody ResponseDto updateBalanceUser_(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int company, @PathVariable String user, @PathVariable String balance, @PathVariable String balanceIncrease, @PathVariable String balanceDecrease) {
		ResponseDto response = new ResponseDto();
		UserAlterQ userAlterQ = new UserAlterQ();
		Account account = new Account();
		AdminData ad;

		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);
			userAlterQ.setId(user);
			userSecurity.notExistsUserAlterQ(userAlterQ);

			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
			
			account.setDate(new Date());
			account.setUser(user);
			account.setCompany(company);
			account.setRound(ad.getRound());
			account.setSeason(ad.getSeason());

			userAlterQ = userAlterQDao.findById(user);

			if (balance!=null && (balance.compareTo("")!=0) && (balance.compareTo("none")!=0)){
				userAlterQ.setBalance(Double.toString(Double.parseDouble(balance)));
				account.setType(new Integer(AccountTypeEnum.ACCOUNT_INITIAL.getValue()));
				account.setAmount(balance);
				account.setDescription("Saldo inicial.");
			}
			else if (balanceIncrease!=null && (balanceIncrease.compareTo("")!=0) && (balanceIncrease.compareTo("none")!=0)){
				userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) + Double.parseDouble(balanceIncrease)));
				account.setType(new Integer(AccountTypeEnum.ACCOUNT_DEPOSIT.getValue()));
				account.setAmount(balanceIncrease);
				account.setDescription("Saldo incrementado.");
			}
			else if (balanceDecrease!=null && (balanceDecrease.compareTo("")!=0) && (balanceDecrease.compareTo("none")!=0)){
				userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) - Double.parseDouble(balanceDecrease)));
				account.setType(new Integer(AccountTypeEnum.ACCOUNT_WITHDRAWAL.getValue()));
				account.setAmount(balanceDecrease);
				account.setDescription("Saldo decrementado.");
			}
			
			userAlterQDao.updateBalance(userAlterQ);
			
			accountingDao.add(account);
			
			response.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userAlterQ));
			

		} catch (SecurityException e) {
			response.addErrorDto("AdminController:updateBalance", "SecurityException");
			e.printStackTrace();
		} catch (Exception e) {
			response.addErrorDto("AdminController:updateBalance", "Generic Update Error");
			e.printStackTrace();
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE, value = "/season/{season}/round/{round}/type/{type}/getFile")
	public @ResponseBody void getElectricFile(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int season, @PathVariable int round, @PathVariable int type, HttpServletResponse resp) {
		AdminData adminData = null;
		ResponseDto response = new ResponseDto();
//		String responseString = new String();
		RoundBets roundBets = null;
		String rdo[] = new String[0];
		boolean bBets = false;
		boolean esAD243 = true;

		log.debug("getElectricFile: start");
		
		esAD243 = (type == 0);

		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);
			adminData = adminDataDao.findById(AlterQConstants.DEFECT_COMPANY);

			Round tmpRound = roundDao.findBySeasonRound(season, round);
			
			if (tmpRound == null){
				response.addErrorDto("AdminController:getElectricFile", "No Round");
				return;
			}
			
			//Get All Companies
			List<Company> companyList = companyDao.findAll();
			for (Company co : companyList) {
				/*
				if (!co.isVisibility())
					continue;
				*/
				
				if (co.getCompany() != AlterQConstants.DEFECT_COMPANY){
					//Solo hay que mirar las quinielas finales
					roundBets = roundBetDao.findFinalBet(season, round, co.getCompany());
				}else{
					//Todas las apuestas excepto la del SuperAdministrador
					roundBets = roundBetDao.findRoundBet(season, round, co.getCompany());
				}
				
				if (roundBets != null) {
					
					CalculateRigths aux = new CalculateRigths();
					
					String despApuesta[];
					List<Bet> lBets = roundBets.getBets();
					if (lBets == null)
						continue;
					
					for (Bet bet : lBets) {
						//Saltamos la apuesta resultado
						if (bet.getType() == BetTypeEnum.BET_RESULT.getValue())
							continue;
						
						bBets = true;
						
						// Calculamos el desglose de cada apuesta
						if ((bet.getBet() == null) || (bet.getBet().length() < 16)) {
							// Tenemos que enviar un aviso al usuario
							log.debug("APUESTA ERRONEA (BET=" + bet.getBet() + "USER=" + bet.getUser());
							continue;
						}
						if ((bet.getReduction() == null) || (bet.getReduction().length() < 14)) {
							// Tenemos que enviar un aviso al usuario
							log.debug("APUESTA ERRONEA (BET=" + bet.getBet() + " REDUCTION=" + bet.getReduction() + " USER=" + bet.getUser());
							continue;
						}
						if ((bet.getTypeReduction() < 0)) {
							// Tenemos que enviar un aviso al usuario
							log.debug("APUESTA ERRONEA (BET=" + bet.getBet() + " REDUCTION=" + bet.getReduction() + " TIPO=" + bet.getTypeReduction() + " USER=" + bet.getUser());
							continue;
						}
						try {

							despApuesta = aux.unfolding(bet.getBet(), bet.getReduction(), bet.getTypeReduction());
							if (despApuesta == null) {
								// Tenemos que enviar un aviso al usuario
								log.debug("ERROR EN DESGLOSE USER=" + bet.getUser());
								continue;
							}

						} catch (Exception e) {
							// Tenemos que enviar un aviso al usuario
							log.debug("ERROR EN DESGLOSE USER=" + bet.getUser());
							continue;
						}

						rdo = aux.acumula(rdo, despApuesta);

					}
				}
			}
			
			if (!bBets)
			{
				response.addErrorDto("AdminController:getElectricFile", "No Bets");
			}else{
				
				// En este punto tenemos ya el array con todas las apuestas
				// desplegadas
	
				// Hay que ordenar las apuestas por el pleno al 15
	
				// Hay que calcular cuantas apuestas y cuantos bloques hay para
				// cada pleno al 15 distinto
				int bloques = rdo.length;// Pendiente revisar
	
				// Creamos la cabecera del fichero
				HeaderBetElectronicFile cb = new HeaderBetElectronicFile();
				
				//Cogemos los datos de la delegacion en 
				//idDelegacion
				//idReceptor
					//cb.setIdDelegacion("12");
					//cb.setIdReceptor("24380");
				cb.setIdDelegacion(adminData.getIdDelegacion());
				cb.setIdReceptor(adminData.getIdReceptor());
				
	
				BetElectronicFile befile = new BetElectronicFile();
				befile.setCabecera(cb);
	
				RegistroBetElectronicFile[] registro = new RegistroBetElectronicFile[rdo.length];
	
				log.debug("numApuestas=" + rdo.length);
				
				if (esAD243) //FICHERO EN FORMATO AD243
				{
				
					MultiValueMap mhm = ordenarApuestas(rdo);
					Set<String> keys = mhm.keySet();
					int numBloquesPleno15 = keys.size();
					log.debug("numBloquesPleno15=" + numBloquesPleno15);
					int indexBloquesTotal = 1;
					for (Object k : keys) {
						log.debug("(" + k + " : " + mhm.get(k) + ")");
						int numApuestasIgualPleno15 = mhm.getCollection(k).size();
						int[] modulusBloque = modulusBloque(numApuestasIgualPleno15);
						int numBloques = modulusBloque[0];
						int indexIterator = 0;
						int numApuestaLastBloque = 0;
						int numBloquesContador = 1;
						log.debug("numBloque=" + modulusBloque[0]);
						log.debug("modBloque=" + modulusBloque[1]);
						log.debug("numApuestasIgualPleno15=" + numApuestasIgualPleno15);
						boolean lastBloque = false;
						StringBuffer pronosticoPartido = new StringBuffer();
						for (Iterator iterator = mhm.getCollection(k).iterator(); iterator.hasNext();) {
							if (numBloquesContador == numBloques) {
								lastBloque = true;
								numApuestaLastBloque++;
							}
							String linea = (String) iterator.next();
							indexIterator++;
							pronosticoPartido.append(StringUtils.left(linea, 14));
							// esto es un nuevo bloque
							if (indexIterator % modulusBloque[1] == 0 && !lastBloque) {
								RegistroBetElectronicFile registroBe = new RegistroBetElectronicFile();
								registroBe.setNumApuestaBloque("" + modulusBloque[1]);
								registroBe.setNumBloque(StringUtils.leftPad("" + indexBloquesTotal, 8, '0'));
								registroBe.setPronostico15(StringUtils.right("" + k, 2));
								registroBe.setPronosticoPartido(StringUtils.rightPad(pronosticoPartido.toString(), 112, ' '));
								registro[indexBloquesTotal] = registroBe;
		
								pronosticoPartido = new StringBuffer();
								numBloquesContador++;
								indexBloquesTotal++;
							}
							// log.debug(linea);
						}
						RegistroBetElectronicFile registroBe = new RegistroBetElectronicFile();
						registroBe.setNumApuestaBloque("" + numApuestaLastBloque);
						registroBe.setNumBloque(StringUtils.leftPad("" + indexBloquesTotal, 8, '0'));
						registroBe.setPronostico15(StringUtils.right("" + k, 2));
						registroBe.setPronosticoPartido(StringUtils.rightPad(pronosticoPartido.toString(), 112, ' '));
						registro[indexBloquesTotal] = registroBe;
						indexBloquesTotal++;
					}
		
					befile.setRegistro(registro);
		
					// check data round in create Round
					// cb.setFechaJornada("010115");
					Date dt = tmpRound.getDateRound();
					DateFormat df = new SimpleDateFormat("ddMMyy");
					String dtf = df.format(dt);
		
					cb.setFechaJornada(dtf);
					cb.setNumJornada(StringUtils.leftPad("" + tmpRound.getRound(), 2, '0'));
					cb.setNumTotalApuestas(StringUtils.leftPad("" + rdo.length, 6, '0'));
					cb.setNumTotalBloques(StringUtils.leftPad("" + (indexBloquesTotal - 1), 6, '0'));
		
					log.debug(befile.getCabeceraString());
					log.debug(befile.getRegistroString());
		
					//responseString = befile.getCabeceraString() + "" + befile.getRegistroString();
		
					resp.setContentType("application/force-download");
					resp.setHeader("Content-Disposition", "attachment; filename=\"ad243\"");
					resp.getOutputStream().write((befile.getCabeceraString() + "" + befile.getRegistroString()).getBytes());
					resp.flushBuffer();
				
				}
				else{ //FICHERO EN FORMATO TXT
					StringBuffer apuestas = new StringBuffer();
					//Generate TXT file with bets
					resp.setContentType("application/force-download");
					resp.setHeader("Content-Disposition", "attachment; filename=\"GoldBittle_"+season+"_"+round+"_Ap_"+rdo.length+".TXT\"");
			        for (int i=0; i<rdo.length; i++)
			        {
			        	apuestas.append(rdo[i]);
			        	apuestas.append("\n");
			        }
					resp.getOutputStream().write(apuestas.toString().getBytes());
					resp.flushBuffer();
					
				}
			}			

		} catch (Exception e) {
			response.addErrorDto("AdminController:getElectricFile", "SecurityException");
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "";
		}

		log.debug("getElectricFile: end");
		// return responseString;

		// return response;
	}

	private MultiValueMap ordenarApuestas(String rdo[]) {
		MultiValueMap mhm = new MultiValueMap();

		for (int i = 0; i < rdo.length; i++) {
			String linea = rdo[i];
			mhm.put(StringUtils.right(linea, 2), StringUtils.left(linea, 14));
		}
		log.debug("results: " + mhm);
		return mhm;
	}

	private int calculoNumBloques(int numApuestas, int modulo) {
		int numBloques = 0;

		int moduloNumBloques = numApuestas % modulo;
		numBloques = numApuestas / modulo;
		if (moduloNumBloques > 0) {
			numBloques++;
		}

		return numBloques;
	}

	/**
	 * @param numBets
	 * @return [numBlock,modBlock]
	 */
	private int[] modulusBloque(int numBets) {
		int[] modulusBloque = { 0, 0 };
		int numBlock = 0;
		int modBlock = 0;
		int i = 8;

		if (numBets == 0) {
			return modulusBloque;
		}

		for (; i > 0; i--) {
			int resto = numBets % i;
			modBlock = i;
			if (resto != 1) {
				break;
			}
		}

		int resto = numBets % i;
		numBlock = numBets / i;
		if (resto != 0) {
			numBlock++;
		}

		modulusBloque[0] = numBlock;
		modulusBloque[1] = modBlock;

		return modulusBloque;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public @ResponseBody ResponseDto getUsers(@CookieValue(value = "session", defaultValue = "") String cookieSession) {
		ResponseDto dto = new ResponseDto();
		UserAlterQ userAlterQ = null;
		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);

			List<UserAlterQ> lUsers = userAlterQDao.findAllUserActive();
			if (lUsers == null)
			{
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.GENERIC_ERROR);
				error.setStringError("Obtener Usuarios (Error: no hay usuarios)");
				dto.addErrorDto(error);
				dto.setUserAlterQ(null);
				return dto;
			}
			
			dto.setUsers(lUsers);

		} catch (SecurityException e) {
			dto.addErrorDto("AdminController:getUsers", "SecurityException");
			e.printStackTrace();
		} catch (Exception e) {
			dto.addErrorDto("AdminController:getUsers", "Generic Update Error");
			e.printStackTrace();
		}

		dto.setUserAlterQ(userAlterQ);

		return dto;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{company}/{season}/{round}/accounting")
	public @ResponseBody ResponseDto getAccounting(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable int company,@PathVariable int season, @PathVariable int round) {
		AccountingEntry accEnt = new AccountingEntry();
		List<Account> lAccounts = null;
		List<UserAlterQ> lUsers = null;
		float credit = 0;	// +
		float debit = 0;	// -
		float balance = 0;	// +
		
		float SumBets = 0; 				// +
		float SumPrizes = 0;			// -
		float SumDeposits = 0;			// +
		float SumWithDrawals = 0;		// -
		float SumInitialBalances = 0;	// +
		float SumRefunds = 0;			// -
		float SumFinalBets = 0;			// -
		float SumJackPots = 0;			// +
		
		
		ResponseDto dto = new ResponseDto();
		AdminData ad;
		try {
			userSecurity.isSuperAdminUserInSession(cookieSession);

			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
			
			if (round == 0)
				lAccounts = accountingDao.findAll();
			else
				lAccounts = accountingDao.findAccounts(season, round);
				
			if (lAccounts == null)
			{
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.GENERIC_ERROR);
				error.setStringError("Obtener Movimientos Contables (Error: no hay usuarios)");
				dto.addErrorDto(error);
				dto.setUserAlterQ(null);
				return dto;
			}
			
			accEnt.setAccounts(lAccounts);

			credit = 0;
			debit = 0;
			for (Account account : lAccounts) {
				if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_BET.getValue())){
					SumBets += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_DEPOSIT.getValue())){
					SumDeposits += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_INITIAL.getValue())){
					SumInitialBalances += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_PRIZE.getValue())){
					SumPrizes += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_REFUND.getValue())){
					SumRefunds += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_WITHDRAWAL.getValue())){
					SumWithDrawals += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_FINALBET.getValue())){
					SumFinalBets += new Float(account.getAmount()).floatValue();
				}else if (account.getType() == new Integer(AccountTypeEnum.ACCOUNT_JACKPOT.getValue())){
					SumJackPots += new Float(account.getAmount()).floatValue();
				}
			}
			
			accEnt.setBets(SumBets);
			accEnt.setDeposits(SumDeposits);
			accEnt.setInitialBalances(SumInitialBalances);
			
			accEnt.setPrizes(SumPrizes);
			accEnt.setWithDrawals(SumWithDrawals);
			accEnt.setRefunds(SumRefunds);
			accEnt.setFinalBets(SumFinalBets);
			accEnt.setJackpots(SumJackPots);
			
			credit = SumBets + SumDeposits + SumInitialBalances;
			debit = SumPrizes + SumWithDrawals + SumRefunds + SumFinalBets + SumJackPots;
			
			accEnt.setCredit(credit);
			accEnt.setDebit(debit);
			
			lUsers = userAlterQDao.findAllUserActive();
			if (lUsers == null)
			{
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.GENERIC_ERROR);
				error.setStringError("Obtener Usuarios (Error: no hay usuarios)");
				dto.addErrorDto(error);
				dto.setUserAlterQ(null);
				return dto;
			}
			
			balance = 0;
			for (UserAlterQ user : lUsers) {
				balance += new Float(user.getBalance()).floatValue();
			}
			
			accEnt.setBalance(balance);
			
//			dto.setUsers(lUsers);
			dto.setAccountEntry(accEnt);
			dto.setAdminData(ad);

		} catch (SecurityException e) {
			dto.addErrorDto("AdminController:getAccounting", "SecurityException");
			e.printStackTrace();
		} catch (Exception e) {
			dto.addErrorDto("AdminController:getAccounting", "Generic Update Error");
			e.printStackTrace();
		}

		return dto;
	}
}

