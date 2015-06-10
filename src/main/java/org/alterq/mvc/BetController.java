package org.alterq.mvc;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.alterq.util.BetTools;
import org.alterq.domain.Round;

@Controller
@RequestMapping(value = "/myaccount/{id:.+}/season/{season}/round/{round}")
public class BetController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private RoundBetDao betDao;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private GeneralDataDao generalDataDao;
	@Autowired
	private UserAlterQSecurity userSecurity;
	
	BetTools betTools = new BetTools();

	// TODO get company from user, session .....
	int company = 1;

	@RequestMapping(method = RequestMethod.POST, value = "/bet/price")
	public @ResponseBody
	ResponseDto calculatePrice(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request,
			@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
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
				if (signo.equals("R"))
				{
					red[indice] = 1;
					continue;
				}
				
				int signoN = (signo.equals("1")) ? 4 : (signo.equals("2") ? 1 : 2);

				//el pleno al 15 tiene un tratamiento especial
				if (indice == 14)
				{
					if (signo.equals("0"))
						signoN = 1;
					else if (signo.equals("1"))
						signoN = 2;
					else if (signo.equals("2"))
						signoN = 4;
					else if (signo.equals("3"))
						signoN = 8;
				}
				if (indice == 15)
				{
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
			if ((i!=14)&&(i!=15)){
				if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6))
					dobles++;
				else if (pro[i] == 7)
					triples++;
			}else{ //gestionamos los múltiples en el pleno al 15
				if (i==14){
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
						pleno1=2;
					else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
						pleno1=3;
					else if((pro[i] == 15))
						pleno1=4;
				}else if (i==15){
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
						pleno2=2;
					else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
						pleno2=3;
					else if((pro[i] == 15))
						pleno2=4;
				}
			}
		}
		
		int doblesRed = 0;
		int triplesRed = 0;
		for (int i = 0; i < red.length; i++) {
			if (red[i] == 1){
				if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6)){
					doblesRed++;
					dobles--;
				}
				else if (pro[i] == 7){
					triplesRed++;
					triples--;
				}
			}
		}
		

		switch (betTools.isBetAllowed(dobles, doblesRed, triples, triplesRed, pleno1, pleno2))
		{
		case 0:
			int typeRed = betTools.getReductionType(doblesRed, triplesRed);
			double numBets = betTools.getNumberBets(typeRed, dobles, triples, pleno1, pleno2);
			float prize = new Double(0.5 * numBets).floatValue();

			Bet bet = new Bet();
			bet.setPrice(prize);
			bet.setNumBets(numBets);
			RoundBets roundBet = new RoundBets();
			roundBet.addBet(bet);

			dto.setRoundBet(roundBet);
			break;
		case -1:	
			ErrorDto error = new ErrorDto();
			error.setIdError(AlterQConstants.BET_NOT_ALLOWED);
			error.setStringError("apuesta no permitida (Error: no existe esta reducción)");
			dto.addErrorDto(error);
			dto.setUserAlterQ(null);
			break;
		case -2:
			ErrorDto error1 = new ErrorDto();
			error1.setIdError(AlterQConstants.BET_NOT_ALLOWED);
			error1.setStringError("apuesta no permitida (Error: demasiados mútiples)");
			dto.addErrorDto(error1);
			dto.setUserAlterQ(null);
			break;
		}

		return dto;
	}

	

	


	@RequestMapping(method = RequestMethod.POST, value = "/bet")
	public @ResponseBody
	ResponseDto addBet(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request,
			@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
		}
		ResponseDto dto = new ResponseDto();

		try {
			userSecurity.isSameUserInSession(id, cookieSession);
			UserAlterQ userAlterQ = userDao.findById(id);
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
					if (signo.equals("R"))
					{
						red[indice] = 1;
						continue;
					}
					
					int signoN = (signo.equals("1")) ? 4 : (signo.equals("2") ? 1 : 2);

					//el pleno al 15 tiene un tratamiento especial
					if (indice == 14)
					{
						if (signo.equals("0"))
							signoN = 1;
						else if (signo.equals("1"))
							signoN = 2;
						else if (signo.equals("2"))
							signoN = 4;
						else if (signo.equals("3"))
							signoN = 8;
					}
					if (indice == 15)
					{
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
				if ((i==14)||(i==15))
					apuesta +=  Integer.toHexString(pro[i]);
				else
					apuesta += pro[i];
				if ((i!=14)&&(i!=15)){
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6))
						dobles++;
					else if (pro[i] == 7)
						triples++;
				}else{ //gestionamos los múltiples en el pleno al 15
					if (i==14){
						if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
							pleno1=2;
						else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
							pleno1=3;
						else if((pro[i] == 15))
							pleno1=4;
					}else if (i==15){
						if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 9) || (pro[i] == 6) || (pro[i] == 10) || (pro[i] == 12))
							pleno2=2;
						else if ((pro[i] == 7) || (pro[i] == 11) || (pro[i] == 14))
							pleno2=3;
						else if((pro[i] == 15))
							pleno2=4;
					}
				}
			}
			
			int doblesRed = 0;
			int triplesRed = 0;
			for (int i = 0; i < red.length; i++) {
				if (red[i] == 1){
					if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6)){
						reduccion += "D";
						doblesRed++;
						dobles--;
					}
					else if (pro[i] == 7){
						reduccion += "T";
						triplesRed++;
						triples--;
					}
				}else{
					reduccion += "N";
				}
			}
			
			switch (betTools.isBetAllowed(dobles, doblesRed, triples, triplesRed, pleno1, pleno2))
			{
			case 0:
				//float price = new Double(0.5 * Math.pow(2, dobles) * Math.pow(3, triples)).floatValue();
				double numBets = betTools.getNumberBets(betTools.getReductionType(doblesRed, triplesRed), dobles, triples, pleno1, pleno2);
				float price = new Double(0.5 * numBets).floatValue();

				float balance = new Float(userAlterQ.getBalance()).floatValue();
				if (balance - price > 0) {
					Bet bet = new Bet();
					bet.setPrice(price);
					bet.setBet(apuesta);
					bet.setUser(userAlterQ.getId());
					bet.setCompany(userAlterQ.getCompany());
					bet.setDateCreated(new Date());
					bet.setDateUpdated(new Date());
					bet.setId(new ObjectId().toStringMongod());
					bet.setReduction(reduccion);
					bet.setTypeReduction(betTools.getReductionType(doblesRed, triplesRed));
					bet.setNumBets(numBets);
					StringBuffer sb = new StringBuffer();
					sb.append("New Bet: season=" + season + " round=" + round + " user=" + bet.getUser() + " bet=" + bet.getBet());
					log.debug(sb.toString());

					//Pasamos los parámetros necesarios para la pantalla de CONFIRMACION
					dto.setBet(bet);
					dto.setUserAlterQ(userAlterQ);
					
					Round r = new Round();
					r = roundDao.findBySeasonRound(season, round);
					
					dto.setRound(r);

				} else {
					ErrorDto error = new ErrorDto();
					error.setIdError(AlterQConstants.USER_NOT_MONEY_ENOUGH);
					error.setStringError("user not enough money (i18n error)");
					dto.addErrorDto(error);
					dto.setUserAlterQ(null);
				}
				break;
			case -1:	
				ErrorDto error = new ErrorDto();
				error.setIdError(AlterQConstants.BET_NOT_ALLOWED);
				error.setStringError("apuesta no permitida (Error: no existe esta reducción)");
				dto.addErrorDto(error);
				dto.setUserAlterQ(null);
				break;
			case -2:
				ErrorDto error1 = new ErrorDto();
				error1.setIdError(AlterQConstants.BET_NOT_ALLOWED);
				error1.setStringError("apuesta no permitida (Error: demasiados mútiples)");
				dto.addErrorDto(error1);
				dto.setUserAlterQ(null);
				break;
			}

		} catch (SecurityException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}

		return dto;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/bet/confirm")
	public @ResponseBody
	ResponseDto confirmBet(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request,
			@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
		}
		ResponseDto dto = new ResponseDto();

		try {
			userSecurity.isSameUserInSession(id, cookieSession);
			UserAlterQ userAlterQ = userDao.findById(id);
			String apuesta = "";
			String reduccion = "";
			int tipoReduccion = 0;
			int numBets = 0;

			Map<String, String[]> parameters = request.getParameterMap();
			for (String parameter : parameters.keySet()) {
				try {
					if (parameter.equals("param_apuesta"))
					{
						apuesta = request.getParameter(parameter) ;
					}else if (parameter.equals("param_reduccion")){
						reduccion = request.getParameter(parameter) ;
					}else if (parameter.equals("param_tiporeduccion")){
						tipoReduccion = Integer.parseInt(request.getParameter(parameter));
					}else if (parameter.equals("param_numbets")){
						numBets = Integer.parseInt(request.getParameter(parameter));
					}
				} catch (Exception e) {
					// TODO: handle exceptionBets
				}
			}
			
			float price = new Double(0.5 * numBets).floatValue();

			float balance = new Float(userAlterQ.getBalance()).floatValue();
			balance -= price;
			userAlterQ.setBalance("" + balance);
			Bet bet = new Bet();
			bet.setPrice(price);
			bet.setBet(apuesta);
			bet.setUser(userAlterQ.getId());
			bet.setCompany(userAlterQ.getCompany());
			bet.setDateCreated(new Date());
			bet.setDateUpdated(new Date());
			bet.setId(new ObjectId().toStringMongod());
			bet.setReduction(reduccion);
			bet.setTypeReduction(tipoReduccion);
			bet.setNumBets(numBets);
			StringBuffer sb = new StringBuffer();
			sb.append("New Bet: season=" + season + " round=" + round + " user=" + bet.getUser() + " bet=" + bet.getBet());
			log.debug(sb.toString());

			//Pasamos los parámetros necesarios para la pantalla de FINALIZACION
			dto.setBet(bet);
			dto.setUserAlterQ(userAlterQ);
			
			Round r = new Round();
			r = roundDao.findBySeasonRound(season, round);
			dto.setRound(r);
			
			
			// Insert new bet into the BBDD
			betDao.addBet(season, round, bet);
			userDao.save(userAlterQ);


		} catch (SecurityException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}

		return dto;

	}

	//if id=mail@mail.es means you must return FinalBet
	//user is amdinUser for this company
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/bet")
	public @ResponseBody
	ResponseDto findAllUserBetsParams(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request,
			@PathVariable(value = "id") String id, @PathVariable(value = "season") int season, @PathVariable(value = "round") int round) {
		ResponseDto dto = new ResponseDto();
		if (StringUtils.equals(id, "mail@mail.es")){
			//TODO we must controller company 
			UserAlterQ adminCompany= userDao.findAdminByCompany(company);
			id=adminCompany.getId();
		}
		RoundBets rb = betDao.findAllUserBets(season, round, id);
		dto.setRoundBet(rb);
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "season/{season}/round/{round}")
	public @ResponseBody
	RoundBets findAllBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round) {
		// TODO this call must be request for an AdminUser
		return betDao.findAllBets(season, round);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "addBet", params = { "season", "round", "user", "bet" })
	public @ResponseBody
	boolean addBetParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round, @RequestParam(value = "user") String user,
			@RequestParam(value = "bet") String bet) {
		Bet bAux = new Bet();
		bAux.setBet(bet);
		bAux.setUser(user);
		bAux.setCompany(company);
		bAux.setDateCreated(new Date());
		bAux.setDateUpdated(new Date());
		return betDao.addBet(season, round, bAux);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "delAllBets", params = { "season", "round" })
	public @ResponseBody
	boolean delAllUserBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round) {
		return betDao.deleteAllBets(season, round);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "delUserBets", params = { "season", "round", "user" })
	public @ResponseBody
	boolean delAllUserBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round, @RequestParam(value = "user") String user) {
		return betDao.deleteAllUserBets(season, round, user);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "delUserBet", params = { "season", "round", "user", "bet" })
	public @ResponseBody
	boolean delUserBet(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round, @RequestParam(value = "user") String user,
			@RequestParam(value = "bet") String bet) {
		Bet bAux = new Bet();
		bAux.setBet(bet);
		bAux.setUser(user);
		return betDao.deleteUserBet(season, round, bAux);
	}

}
