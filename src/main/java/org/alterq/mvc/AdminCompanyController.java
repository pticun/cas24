package org.alterq.mvc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alterq.domain.Bet;
import org.alterq.domain.Prize;
import org.alterq.domain.Ranking;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
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
import org.alterq.util.CalculateRigths;
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

	private static int doubles = 0;
	private static int triples = 0;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init adminCompany.jsp");
		return "adminCompany";
	}

	/**
	 * 
	 * */
	private String getAdmin() {
		UserAlterQ admin = userAlterQDao.findAdminByCompany(AlterQConstants.DEFECT_COMPANY);
		return admin.getId();
	}


	private float calcQuinielaPrice(int doubles, int triples, int type) {
		return new Double(getQuinielaNumBets(type) * betTools.getPriceBet() * Math.pow(2, doubles) * Math.pow(3, triples)).floatValue();
	}

	private int getQuinielaNumBets(int type) {
		switch (type) {
		case 0:
			return 1; // Sencilla
		case 1:
			return 9; // Reduccion Primera (4T)
		case 2:
			return 16; // Reduccion Segunda (7D)
		case 3:
			return 24; // Reduccion Tercera (3D + 3T)
		case 4:
			return 64; // Reduccion Cuarta (6D + 2T)
		case 5:
			return 81; // Reduccion Quinta (8T)
		default:
			return 1;
		}
	}



	private double calcUserWeight(String user) {

		UserAlterQ userAlterQ;

		userAlterQ = userAlterQDao.findById(user);

		if (userAlterQ != null)
			return userAlterQ.getWeight();

		return 0.0;
	}

	/**
	 * Function calcUserRightSigns that calcs bet user's right sings
	 * 
	 * ResultBet Signs: 1 = 100 = 4 X = 010 = 2 2 = 001 = 1
	 * 
	 * Bet Signs: 1 = 100 = 4 X = 010 = 2 2 = 001 = 1 1X = 110 = 6 1 2 = 101 = 5
	 * X2 = 011 = 3 1X2 = 111 = 7
	 * 
	 * Pleno15 Signs: 0 = 0001 = 1 1 = 0010 = 2 01 = 0011 = 3 2 = 0100 = 4 0 2 =
	 * 0101 = 5 12 = 0110 = 6 012 = 0111 = 7 M = 1000 = 8 0 M = 1001 = 9 1 M =
	 * 1010 = a 0 2M = 1011 = b 2M = 1100 = c 01 M = 1101 = d 12M = 1110 = e
	 * 012M = 1111 = f
	 * 
	 * Gets users Bets, and calculate right signs for each bet
	 * 
	 * @return int[] Devuelve un vector de int donde: int[0] es el numero de
	 *         aciertos int[1] es el número de doses acertados int[2] es el
	 *         número de equis acertadas int[3] es el número de unos acertados
	 *
	 * @param String
	 *            resultBet : result round bet
	 * @param String
	 *            apu : user bet
	 * @param String
	 *            user : user id
	 * 
	 *            Descripcion: Calculate right signs for each user bet
	 * */

	private int[] calcUserRightSigns(String resultBet, String apu, UserAlterQ user, int company) {

		int rdo, doses, equis, unos, p15;
		int[] salida = new int[5];
		salida[0] = salida[1] = salida[2] = salida[3] = salida[4] = -1;

		rdo = 0;
		doses = 0;
		equis = 0;
		unos = 0;
		p15 = 0;

		int singBet;
		int singRes;

		try {
			// Translate 1X2 to 421
			// Pleno15 012M to 1248

			for (int i = 0; i < apu.length(); i++) {
				singBet = Integer.parseInt(apu.substring(i, i + 1));
				singRes = Integer.parseInt(resultBet.substring(i, i + 1));
				if (i < 14) {
					switch (singRes) {
					case 4:// sign 1
						if ((singBet == 4) || (singBet == 6) || (singBet == 5) || (singBet == 7)) {
							rdo++;
							unos++;
						}
						break;
					case 2:// sign X
						if ((singBet == 2) || (singBet == 3) || (singBet == 6) || (singBet == 7)) {
							rdo++;
							unos++;
						}
						break;
					case 1: // sign 2
						if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7)) {
							rdo++;
							unos++;
						}
						break;
					default: // something wrong
						break;
					}
				} else {
					switch (singRes) {
					case 1:// sign 0
						if ((singBet == 1) || (singBet == 3) || (singBet == 5) || (singBet == 7) || (singBet == 9) || (singBet == 11) || (singBet == 13) || (singBet == 15)) {
							p15++;
						}
						break;
					case 2:// sign 1
						if ((singBet == 2) || (singBet == 3) || (singBet == 6) || (singBet == 7) || (singBet == 10) || (singBet == 13) || (singBet == 14) || (singBet == 15)) {
							p15++;
						}
						break;
					case 4: // sign 2
						if ((singBet == 4) || (singBet == 5) || (singBet == 6) || (singBet == 7) || (singBet == 11) || (singBet == 12) || (singBet == 14) || (singBet == 15)) {
							p15++;
						}
						break;
					case 8: // sign M
						if ((singBet == 8) || (singBet == 9) || (singBet == 10) || (singBet == 11) || (singBet == 12) || (singBet == 13) || (singBet == 14) || (singBet == 15)) {
							p15++;
						}
						break;
					default: // something wrong
						break;
					}

				}
			}
		} catch (Exception e) {
			rdo = 0;
			doses = 0;
			equis = 0;
			unos = 0;
			p15 = 0;
		}
		// Asignamos los resultados al vector final
		// Numero de apuestas acertadas
		salida[0] = rdo;
		// Número de doses acertados
		salida[1] = doses;
		// Número de equis acertadas
		salida[2] = equis;
		// Número de unos acertados
		salida[3] = unos;
		salida[4] = p15;

		return salida;
	}



	// Funciona
	private void updateRoundRanking(int company, int season, int round, UserAlterQ user, int points, int ones, int equs, int twos) {
		Ranking rnk = new Ranking();
		rnk.setCompany(company);
		rnk.setOnes(ones);
		rnk.setEqus(equs);
		rnk.setTwos(twos);
		rnk.setPoints(points);
		rnk.setUser(user);

		roundRankingDao.addRanking(company, season, round, rnk);
	}



	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = "/company/{company}/season/{season}/round/{round}/closeAC")
	public @ResponseBody ResponseDto closeRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		log.debug("closeRound: start");

		try {
			userSecurity.isAdminUserInSession(cookieSession, company);

			// CLOSING PROCESS STEPS (Admin Company)
			//


			// STEP 1: Automatics Bets
			//
			// STEP 1.1 - Get Users with automatic bets
			List<UserAlterQ> lUsers = userAlterQDao.findUserWithAutomatics(company);
			// STEP 1.2 - For each user do as bets as automatic bets (It has to
			// check user amount before make automatics bets)
			for (UserAlterQ user : lUsers) {
				// loop for number of automatic bets
				int numAutom = user.getAutomatics();
				for (int i = 0; i < numAutom; i++) {
					// STEP 1.2.1 - Check User Balance
					float balance = new Float(user.getBalance()).floatValue();
					if (balance < betTools.getPriceBet()) {
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
					bet.setId(new ObjectId().toStringMongod());

					roundBetDao.addBet(company, season, round, bet);

					// STEP 1.2.4 - Update User Balance
					try {
						user.setBalance(Float.toString((float) (balance - betTools.getPriceBet())));
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
			}

			// STEP 2: Fixed Bets (¿?)
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





	/*
	 * // @RequestMapping(method = RequestMethod.POST, produces =
	 * "application/json", value =
	 * "/company/{company}/season/{season}/round/{round}/prize15/{count15}/{amount15}/prize14/{count14}/{amount14}/prize13/{count13}/{amount13}/prize12/{count12}/{amount12}/prize11/{count11}/{amount11}/prize10/{count10}/{amount10}"
	 * ) // public @ResponseBody // ResponseDto prizesRound(@PathVariable int
	 * company, @PathVariable int season, @PathVariable int round, @PathVariable
	 * int count15, @PathVariable float amount15, @PathVariable int count14,
	 * @PathVariable float amount14, @PathVariable int count13, @PathVariable
	 * float amount13, @PathVariable int count12, @PathVariable float amount12,
	 * @PathVariable int count11, @PathVariable float amount11, @PathVariable
	 * int count10, @PathVariable float amount10) {
	 * 
	 * @RequestMapping(method = RequestMethod.POST, produces =
	 * "application/json", value =
	 * "/company/{company}/season/{season}/round/{round}/prizesBetPenya")
	 * //public @ResponseBody ResponseDto prizesRound(@CookieValue(value =
	 * "session", defaultValue = "") String cookieSession, HttpServletRequest
	 * request, @RequestBody PrizesRound prizesRound) { public @ResponseBody
	 * ResponseDto prizesRoundPenya(@CookieValue(value = "session", defaultValue
	 * = "") String cookieSession, HttpServletRequest request, @PathVariable int
	 * company, @PathVariable int season, @PathVariable int round) { ResponseDto
	 * response = new ResponseDto(); RoundBets roundBets; int numBets; double
	 * rewardGlobal; double betReward = 0; UserAlterQ userAlterQ;
	 * 
	 * try { userSecurity.isAdminUserInSession( cookieSession); roundBets =
	 * roundBetDao.findAllBets(season, round);
	 * 
	 * List<Prize> lPrizes = new ArrayList<Prize>(); Map<String, String[]>
	 * parameters = request.getParameterMap();
	 * 
	 * for (int i=0;i<=5;i++) { Prize priceTmp = new Prize();
	 * priceTmp.setId(i+10);
	 * priceTmp.setCount(Integer.parseInt(parameters.get("count"+(i+10))[0]));
	 * priceTmp.setAmount(Float.parseFloat(parameters.get("prize"+(i+10))[0]));
	 * lPrizes.add(priceTmp); }
	 * 
	 * 
	 * roundBets.setPrizes(lPrizes);
	 * 
	 * 
	 * numBets = roundBets.getBets().size() - 1; //Admin bet is not a real bet
	 * //rewardGlobal = roundBets.getJackpot() + count15*amount15 +
	 * count14*amount14 + count13*amount13 + count12*amount12 + count11*amount11
	 * + count10*amount10; rewardGlobal = roundBets.getJackpot(); //List<Prize>
	 * lPrizes = roundBets.getPrizes(); for (Prize prize : lPrizes){
	 * rewardGlobal+= prize.getAmount() * prize.getCount(); }
	 * 
	 * 
	 * betReward = rewardGlobal / numBets;
	 * 
	 * 
	 * List<Bet> lBets = roundBets.getBets(); for (Bet bet : lBets){ String user
	 * = bet.getUser();
	 * 
	 * userAlterQ = userAlterQDao.findById(user);
	 * 
	 * if (userAlterQ== null){
	 * log.debug("pricesRound: user("+user+") Error resultBet user not find");
	 * //STEP 1.1.error - Send an email to the admin
	 * ("ERROR pricesRound user not find") continue; }
	 * 
	 * //Admin unser don't win money, because his bet is not a real bet if
	 * (userAlterQ.isAdmin()) { continue; }
	 * 
	 * userAlterQ.setBalance(Double.toString(
	 * Double.parseDouble(userAlterQ.getBalance()) + betReward));
	 * userAlterQDao.save(userAlterQ); }
	 * 
	 * roundBetDao.update(roundBets); } catch (SecurityException e) { // TODO
	 * Auto-generated catch block
	 * response.addErrorDto("AdminController:prizesRound", "SecurityException");
	 * e.printStackTrace(); }
	 * 
	 * 
	 * return response; }
	 */

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
	public @ResponseBody ResponseDto prizesRound(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		RoundBets roundBets;
		int numBets;
		double rewardGlobal;
		double betReward = 0;
		UserAlterQ userAlterQ;
		int countPrizes[] = { 0, 0, 0, 0, 0 };

		try {
			companyValidator.isCompanyOk(company);
			userSecurity.isAdminUserInSession(cookieSession, company);
			roundBets = roundBetDao.findAllBets(season, round);

			List<Prize> lPrizes = new ArrayList<Prize>();
			Map<String, String[]> parameters = request.getParameterMap();

			List<Bet> lBets = roundBets.getBets();
			for (Bet bet : lBets) {
				String user = bet.getUser();

				userAlterQ = userAlterQDao.findById(user);

				if (userAlterQ == null) {
					log.debug("pricesRound: user(" + user + ") Error resultBet user not find");
					// STEP 1.1.error - Send an email to the admin
					// ("ERROR pricesRound user not find")
					continue;
				}

				CalculateRigths util = new CalculateRigths();
				countPrizes = util.calculate(parameters.get("results").toString(), bet.getBet(), bet.getReduction(), bet.getTypeReduction());
				for (int i = 0; i <= 5; i++) {
					Prize priceTmp = new Prize();
					priceTmp.setId(i + 10);
					priceTmp.setCount(countPrizes[i]);
					priceTmp.setAmount(Float.parseFloat(parameters.get("prize" + (i + 10))[0]));
					lPrizes.add(priceTmp);
				}

				bet.setPrizes(lPrizes);

				betReward = 0;

				for (Prize prize : lPrizes) {
					betReward += prize.getAmount() * prize.getCount();
				}

				userAlterQ.setBalance(Double.toString(Double.parseDouble(userAlterQ.getBalance()) + betReward));
				userAlterQDao.save(userAlterQ);
			}

			roundBetDao.update(roundBets);
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_ADMIN);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_ADMIN));
			response.addErrorDto(error);
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return response;
	}

//	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
//	public @ResponseBody ResponseDto getRound(@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, 
//			@PathVariable(value = "round") int round, @PathVariable(value = "company") int company) {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/{id:.+}/{company}/{season}/{round}/summary")
	public @ResponseBody ResponseDto summary(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable(value = "id") String id, @PathVariable int company, @PathVariable int season, @PathVariable int round) {
		ResponseDto response = new ResponseDto();
		String responseString = new String();
		RoundBets roundBets;
		
		log.debug("getSummary: start");
		try {
			userSecurity.isAdminUserInSession(cookieSession, company);
			companyValidator.isCompanyOk(company);

			roundBets = roundBetDao.findAllBets(season, round, company);

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
