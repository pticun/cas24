package org.alterq.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.alterq.converter.UserAlterQConverter;
import org.alterq.domain.Bet;
import org.alterq.domain.RolCompany;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.MailQueueDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.exception.ValidatorException;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.BetTools;
import org.alterq.util.MailTools;
import org.alterq.util.UserTools;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.util.enumeration.QueueMailEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.alterq.validator.CompanyValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.channel.ProcessMailQueue;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.arch.core.mail.SendMailer;
import org.arch.core.util.CoreUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myaccount/{id:.+}/{company}/{season}/{round}")
public class BetController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private UserAlterQSecurity userSecurity;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;
	@Autowired
	private BetTools betTools;
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private UserTools userTools;
	@Autowired
	private RoundBetDao roundBetDao;
	@Autowired
	private UserAlterQConverter userAlterQConverter;
	@Autowired
	MailTools mailTools;
	
	@Autowired
	ProcessMailQueue processMailQueue;

	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;

	@RequestMapping(method = RequestMethod.POST, value = "/bet/price")
	public @ResponseBody ResponseDto calculatePrice(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round,
			@PathVariable(value = "company") String company) {
		if (log.isDebugEnabled()) {
			log.debug("init BetController.price");
			log.debug("session:" + cookieSession);
		}
		/*
		 * UserAlterQ userAlterQ = null; if
		 * (StringUtils.isNotBlank(cookieSession)) { String idUserAlterQ =
		 * sessionDao.findUserAlterQIdBySessionId(cookieSession); userAlterQ =
		 * userDao.findById(idUserAlterQ); }
		 */
		// TODO control security
		// TODO business logic to calculate Price (price depends
		// company/round/season .......
		ResponseDto dto = new ResponseDto();
		/*
		 * if(userAlterQ==null){ ErrorDto error = new ErrorDto();
		 * error.setIdError(ErrorType.USER_NOT_IN_SESSION);
		 * error.setStringError("user not in Session (i18n error)");
		 * dto.setErrorDto(error); dto.setUserAlterQ(null); return dto; }
		 */

		int pro[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int red[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int dobles = 0;
		int triples = 0;
		int pleno1 = 1;
		int pleno2 = 1;

		Map<String, String[]> parameters = request.getParameterMap();
		for (String parameter : parameters.keySet()) {
			StringTokenizer st = new StringTokenizer(parameter, "_");
			try {
				int indice = Integer.parseInt(st.nextToken());
				String signo = st.nextToken();
				if (signo.equals("R")) {
					red[indice] = 1;
					continue;
				}

				int signoN = (signo.equals("1")) ? 4 : (signo.equals("2") ? 1 : 2);

				// el pleno al 15 tiene un tratamiento especial
				if (indice == 14) {
					if (signo.equals("0"))
						signoN = 1;
					else if (signo.equals("1"))
						signoN = 2;
					else if (signo.equals("2"))
						signoN = 4;
					else if (signo.equals("3"))
						signoN = 8;
				}
				if (indice == 15) {
					if (signo.equals("0"))
						signoN = 1;
					else if (signo.equals("1"))
						signoN = 2;
					else if (signo.equals("2"))
						signoN = 4;
					else if (signo.equals("3"))
						signoN = 8;
				}
				pro[indice] += signoN;
			} catch (Exception e) {
				// TODO: handle exception
			}
			// log.debug(sb.toString());
		}

		for (int i = 0; i < pro.length; i++) {
			if ((i != 14) && (i != 15)) {
				if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6))
					dobles++;
				else if (pro[i] == 7)
					triples++;
			} else { // gestionamos los múltiples en el pleno al 15
				if (i == 14) {
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
						pleno1 = 2;
					else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
						pleno1 = 3;
					else if ((pro[i] == 15))
						pleno1 = 4;
				} else if (i == 15) {
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
						pleno2 = 2;
					else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
						pleno2 = 3;
					else if ((pro[i] == 15))
						pleno2 = 4;
				}
			}
		}

		int doblesRed = 0;
		int triplesRed = 0;
		for (int i = 0; i < red.length; i++) {
			if (red[i] == 1) {
				if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6)) {
					doblesRed++;
					dobles--;
				} else if (pro[i] == 7) {
					triplesRed++;
					triples--;
				}
			}
		}

		switch (betTools.isBetAllowed(dobles, doblesRed, triples, triplesRed, pleno1, pleno2)) {
		case 0:
			int typeRed = betTools.getReductionType(doblesRed, triplesRed);
			int numBets = betTools.getNumberBets(typeRed, dobles, triples, pleno1, pleno2);
			float prize = new Double(betTools.getPriceBet() * numBets).floatValue();

			Bet bet = new Bet();
			bet.setPrice(prize);
			bet.setNumBets(numBets);
			RoundBets roundBet = new RoundBets();
			roundBet.addBet(bet);

			dto.setRoundBet(roundBet);
			break;
		case -1:
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.BET_NOT_ALLOWED);
			error.setStringError("apuesta no permitida (Error: no existe esta reducción)");
			dto.addErrorDto(error);
			dto.setUserAlterQ(null);
			break;
		case -2:
			ErrorDto error1 = new ErrorDto();
			error1.setIdError(MessageResourcesNameEnum.BET_NOT_ALLOWED);
			error1.setStringError("apuesta no permitida (Error: demasiados mútiples)");
			dto.addErrorDto(error1);
			dto.setUserAlterQ(null);
			break;
		}

		return dto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/bet")
	public @ResponseBody ResponseDto addBet(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round,
			@PathVariable(value = "company") int company) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
		}
		ResponseDto dto = new ResponseDto();
		boolean adminCompanyUser = false;

		try {

			adminCompanyUser = ((company != AlterQConstants.DEFECT_COMPANY) && userTools.isUserAdminCompany(id, company));

			userSecurity.isSameUserInSession(id, cookieSession);
			companyValidator.isCompanyOk(company);
			RolCompany rc = new RolCompany();
			rc.setCompany(company);
			rc.setRol(RolNameEnum.ROL_USER.getValue());
			UserAlterQ userAlterQ = userDao.findById(id);

			rolCompanySecurity.isUserAuthorizedRolForCompany(userAlterQ, rc);

			if (!adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA).isActive()) {
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.BET_NOT_ALLOWED);
				error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.BET_NOT_ALLOWED_FOR_ROUND_NOT_ACTIVE));
				dto.addErrorDto(error);
				dto.setUserAlterQ(null);
				return dto;
			}

			String apuesta = "";
			String reduccion = "";
			int pro[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int red[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			int pleno1 = 1;
			int pleno2 = 1;

			Map<String, String[]> parameters = request.getParameterMap();
			for (String parameter : parameters.keySet()) {
				StringTokenizer st = new StringTokenizer(parameter, "_");
				try {
					int indice = Integer.parseInt(st.nextToken());
					String signo = st.nextToken();
					if (signo.equals("R")) {
						red[indice] = 1;
						continue;
					}

					int signoN = (signo.equals("1")) ? 4 : (signo.equals("2") ? 1 : 2);

					// el pleno al 15 tiene un tratamiento especial
					if (indice == 14) {
						if (signo.equals("0"))
							signoN = 1;
						else if (signo.equals("1"))
							signoN = 2;
						else if (signo.equals("2"))
							signoN = 4;
						else if (signo.equals("3"))
							signoN = 8;
					}
					if (indice == 15) {
						if (signo.equals("0"))
							signoN = 1;
						else if (signo.equals("1"))
							signoN = 2;
						else if (signo.equals("2"))
							signoN = 4;
						else if (signo.equals("3"))
							signoN = 8;
					}
					pro[indice] += signoN;
				} catch (Exception e) {
					// TODO: handle exception
				}
				// log.debug(sb.toString());
			}
			int dobles = 0;
			int triples = 0;
			for (int i = 0; i < pro.length; i++) {
				if ((i == 14) || (i == 15))
					apuesta += Integer.toHexString(pro[i]);
				else
					apuesta += pro[i];
				if ((i != 14) && (i != 15)) {
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6))
						dobles++;
					else if (pro[i] == 7)
						triples++;
				} else { // gestionamos los múltiples en el pleno al 15
					if (i == 14) {
						if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
							pleno1 = 2;
						else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
							pleno1 = 3;
						else if ((pro[i] == 15))
							pleno1 = 4;
					} else if (i == 15) {
						if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
							pleno2 = 2;
						else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
							pleno2 = 3;
						else if ((pro[i] == 15))
							pleno2 = 4;
					}
				}
			}

			int doblesRed = 0;
			int triplesRed = 0;
			for (int i = 0; i < red.length; i++) {
				if (red[i] == 1) {
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6)) {
						reduccion += "D";
						doblesRed++;
						dobles--;
					} else if (pro[i] == 7) {
						reduccion += "T";
						triplesRed++;
						triples--;
					}
				} else {
					reduccion += "N";
				}
			}

			switch (betTools.isBetAllowed(dobles, doblesRed, triples, triplesRed, pleno1, pleno2)) {
			case 0:
				// float price = new Double(0.5 * Math.pow(2, dobles) *
				// Math.pow(3, triples)).floatValue();
				int numBets = betTools.getNumberBets(betTools.getReductionType(doblesRed, triplesRed), dobles, triples, pleno1, pleno2);
				float price = new Double(betTools.getPriceBet() * numBets).floatValue();

				float balance = new Float(userAlterQ.getBalance()).floatValue();

				//if user enoguht money add bet,
				//if user is adminCompany we allow make the bet 
				if ((balance - price > 0) || adminCompanyUser) {
					Bet bet = new Bet();
					bet.setId(new ObjectId().toHexString());
					bet.setPrice(price);
					bet.setBet(apuesta);
					bet.setUser(userAlterQ.getId());
					// bet.setCompany(userAlterQ.getCompany());
					bet.setCompany(company);
					bet.setDateCreated(new Date());
					bet.setDateUpdated(new Date());
					bet.setNumBets(numBets);
					bet.setType(BetTypeEnum.BET_NORMAL.getValue());
					bet.setTypeReduction(betTools.getReductionType(doblesRed, triplesRed));
					bet.setReduction(reduccion);
					StringBuffer sb = new StringBuffer();
					sb.append("New addBet: company=" + company + " season=" + season + " round=" + round + " user=" + bet.getUser() + " bet=" + bet.getBet());
					log.debug(sb.toString());

					// Pasamos los parámetros necesarios para la pantalla de
					// CONFIRMACION
					dto.setBet(bet);
					dto.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userAlterQ));

					Round r = new Round();
					r = roundDao.findBySeasonRound(season, round);

					dto.setRound(r);

				} else {
					ErrorDto error = new ErrorDto();
					error.setIdError(MessageResourcesNameEnum.USER_NOT_MONEY_ENOUGH);
					error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_MONEY_ENOUGH));
					dto.addErrorDto(error);
					dto.setUserAlterQ(null);
				}
				break;
			case -1:
				ErrorDto error = new ErrorDto();
				error.setIdError(MessageResourcesNameEnum.BET_NOT_ALLOWED);
				error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.BET_NOT_ALLOWED_NOT_REDUCTION));
				dto.addErrorDto(error);
				dto.setUserAlterQ(null);
				break;
			case -2:
				ErrorDto error1 = new ErrorDto();
				error1.setIdError(MessageResourcesNameEnum.BET_NOT_ALLOWED);
				error1.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.BET_NOT_ALLOWED_TOO_MULTIPLE));
				dto.addErrorDto(error1);
				dto.setUserAlterQ(null);
				break;
			}

		} catch (SecurityException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		} catch (ValidatorException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}

		return dto;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/bet/confirm")
	public @ResponseBody ResponseDto confirmBet(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round,
			@PathVariable(value = "company") int company) {
		RoundBets roundBets = null;
		

		if (log.isDebugEnabled()) {
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
		}
		ResponseDto dto = new ResponseDto();
		String ccoMail = null;
		String linkBet = null;
		
		boolean adminCompanyUser = false;

		try {

			userSecurity.isSameUserInSession(id, cookieSession);
			companyValidator.isCompanyOk(company);
			adminCompanyUser = ((company != AlterQConstants.DEFECT_COMPANY) && userTools.isUserAdminCompany(id, company));
			UserAlterQ userAlterQ = userDao.findById(id);
			RolCompany rc = new RolCompany();
			rc.setCompany(company);
			rc.setRol(RolNameEnum.ROL_USER.getValue());

			rolCompanySecurity.isUserAuthorizedRolForCompany(userAlterQ, rc);
			String apuesta = "";
			String reduccion = "";
			int tipoReduccion = 0;
			int numBets = 0;
			BetTypeEnum typeBet=BetTypeEnum.BET_NORMAL;

			Map<String, String[]> parameters = request.getParameterMap();
			for (String parameter : parameters.keySet()) {
				try {
					if (parameter.equals("param_apuesta")) {
						apuesta = request.getParameter(parameter);
					} else if (parameter.equals("param_reduccion")) {
						reduccion = request.getParameter(parameter);
					} else if (parameter.equals("param_tiporeduccion")) {
						tipoReduccion = Integer.parseInt(request.getParameter(parameter));
					} else if (parameter.equals("param_numbets")) {
						numBets = Integer.parseInt(request.getParameter(parameter));
					} else if (parameter.equals("param_betType")) {
						typeBet = BetTypeEnum.getBetType(request.getParameter(parameter));
					}
				} catch (Exception e) {
					// TODO: handle exceptionBets
					log.error(ExceptionUtils.getStackTrace(e));
				}
			}

			float price = new Double(betTools.getPriceBet() * numBets).floatValue();

			float balance = new Float(userAlterQ.getBalance()).floatValue();
			balance -= price;
			userAlterQ.setBalance("" + balance);
			Bet bet = new Bet();
			bet.setPrice(price);
			bet.setBet(apuesta);
			bet.setUser(userAlterQ.getId());
			// bet.setCompany(userAlterQ.getCompany());
			bet.setCompany(company);
			bet.setDateCreated(new Date());
			bet.setDateUpdated(new Date());
			bet.setId(new ObjectId().toHexString());
			bet.setReduction(reduccion);
			bet.setTypeReduction(tipoReduccion);
			bet.setNumBets(numBets);
//			bet.setType(BetTypeEnum.BET_NORMAL.getValue());
//			if (adminCompanyUser) {
//				bet.setType(BetTypeEnum.BET_FINAL.getValue());
//			}
			bet.setType(typeBet.getValue());
			StringBuffer sb = new StringBuffer();
			sb.append("New addBet: company=" + company + " season=" + season + " round=" + round + " user=" + bet.getUser() + " bet=" + bet.getBet());
			log.debug(sb.toString());

			// Pasamos los parámetros necesarios para la pantalla de
			// FINALIZACION
			dto.setBet(bet);
			dto.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userAlterQ));

			Round r = new Round();
			r = roundDao.findBySeasonRound(season, round);
			dto.setRound(r);

//			if ((company != AlterQConstants.DEFECT_COMPANY) && userTools.isUserAdminCompany(userAlterQ.getId(), company)) {
			if (BetTypeEnum.BET_FINAL.getValue()==typeBet.getValue()) {
				roundBets = roundBetDao.findRoundBetWithBets(season, round, company);
				
				//if not existe roundBets create new roundBet
				if (roundBets == null) {
					roundBets = new RoundBets();
					roundBets.setId(new ObjectId().toHexString());
					roundBets.setCompany(company);
					roundBets.setRound(round);
					roundBets.setSeason(season);
					roundBets.setPrice(price);
					roundBetDao.add(roundBets); 
				}


				roundBets.setPrice(price);
				// Calculate Round Company JackPot
				//money to play for adminCompany
				float amountCompany = 0;
				//price of all bets for AdminCompany
				float priceCompany = 0;
				List<Bet> lBets = roundBets.getBets();
				if (lBets!=null){
					for (Bet bAux : lBets) {
						if (bAux.getType() == BetTypeEnum.BET_NORMAL.getValue()) {
							amountCompany += bAux.getPrice();
						} else if (bAux.getType() == BetTypeEnum.BET_FINAL.getValue()) {
							priceCompany += bAux.getPrice();
						}
					}
				}

				priceCompany += price;

				roundBets.setJackpot(amountCompany - priceCompany);
				roundBets.setPrice(priceCompany);

				if ((amountCompany - priceCompany) < 0) {
					ErrorDto error = new ErrorDto();
					error.setIdError(MessageResourcesNameEnum.USER_NOT_MONEY_ENOUGH);
					error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_MONEY_ENOUGH));
					dto.addErrorDto(error);
					dto.setUserAlterQ(null);

					return dto;
				}

				roundBetDao.update(roundBets);
				
				ccoMail = mailTools.getCCOFinalBet(company, season, round);
			}
			else{
				ccoMail = userAlterQ.getId();
			}

			// Insert new bet into the BBDD
			roundBetDao.addBet(company, season, round, bet);

			userDao.updateBalance(userAlterQ);
			
			//Send mail with Bet
			//******************************************************************
			log.debug("Mail: Bet= "+bet.getBet()+" CCO="+ccoMail);

			MailQueueDto mailDto=new MailQueueDto();
			mailDto.setType(QueueMailEnum.Q_FINALBETMAIL);
			
			RoundBets processRoundBetMail=new RoundBets();
			processRoundBetMail.setCompany(company);
			processRoundBetMail.setRound(round);
			processRoundBetMail.setSeason(season);
			processRoundBetMail.setPrice(bet.getPrice());
			//TODO jackpot 
//			processRoundBetMail.setJackpot(roundBets.getJackpot());
			List<Bet> finalBet=new ArrayList<Bet>();
			finalBet.add(bet);
			processRoundBetMail.setBets(finalBet);
			
			mailDto.setRoundBet(processRoundBetMail);
			
			if(!StringUtils.contains(CoreUtils.getCurrentHostName(),"pro")){
				//para pruebas
				ccoMail = "quinielagold@gmail.com";
			}
			
			mailDto.setCco(ccoMail);
			
			processMailQueue.process(mailDto);
			

//
//			//linkBet = "http://www.quinigold.com/getBetDetail/id_bet="+bet.getId();
//			linkBet = "http://localhost:8080/quinimobile/getBetDetail/id_bet="+bet.getId();
//			
//			sendMailer.sendFinalBetMail(ccoMail, round, season, bet.getId(), price, numBets, linkBet);
			
			
			//******************************************************************
			

		} catch (SecurityException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		} catch (ValidatorException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}

		return dto;

	}

	// if id=mail@mail.es means you must return FinalBet
	// user is amdinUser for this company
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/bet")
	public @ResponseBody ResponseDto findAllUserBetsParams(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request, @PathVariable(value = "id") String id, @PathVariable(value = "season") int season,
			@PathVariable(value = "round") int round, @PathVariable(value = "company") int company) {
		ResponseDto dto = new ResponseDto();

		try {
			companyValidator.isCompanyOk(company);
			RoundBets rb;
			RoundBets rbAdminCompanyFinalBets;
			// if (StringUtils.equals(id, "mail@mail.es")) {
			// TODO control user if exists
			rb = roundBetDao.findAllUserBets(season, round, id, company);

			rbAdminCompanyFinalBets = roundBetDao.findFinalBet(season, round, company);

			//User has bets
			if (rb != null)
			{
				if (rbAdminCompanyFinalBets != null) {
					for (Bet bet : rbAdminCompanyFinalBets.getBets()) {
						rb.addBet(bet);
					}
				}
			}
			else //User has not bets
			{
				//if user is an AdminCompany add final bets although he doesn't have bets
				if (userTools.isUserAdminCompany(id, company))
					rb = rbAdminCompanyFinalBets;
			}
			
			dto.setRoundBet(rb);
		} catch (ValidatorException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody RoundBets findAllBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round) {
		// TODO this call must be request for an AdminUser
		return roundBetDao.findRoundBet(season, round);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "addBet")
	public @ResponseBody boolean addBetParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round, @RequestParam(value = "user") String user, @RequestParam(value = "bet") String bet) {
		Bet bAux = new Bet();
		bAux.setBet(bet);
		bAux.setUser(user);
		bAux.setCompany(AlterQConstants.DEFECT_COMPANY);
		bAux.setDateCreated(new Date());
		bAux.setDateUpdated(new Date());
		return roundBetDao.addBet(AlterQConstants.DEFECT_COMPANY, season, round, bAux);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "delAllBets")
	public @ResponseBody boolean delAllUserBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round) {
		return roundBetDao.deleteAllBets(season, round);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "delUserBets")
	public @ResponseBody boolean delAllUserBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round, @RequestParam(value = "user") String user) {
		return roundBetDao.deleteAllUserBets(season, round, user);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "delUserBet")
	public @ResponseBody boolean delUserBet(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round, @RequestParam(value = "user") String user, @RequestParam(value = "bet") String bet) {
		Bet bAux = new Bet();
		bAux.setBet(bet);
		bAux.setUser(user);
		return roundBetDao.deleteUserBet(season, round, bAux);
	}

}
