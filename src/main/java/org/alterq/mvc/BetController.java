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
		int pro[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int dobles = 0;
		int triples = 0;

		Map<String, String[]> parameters = request.getParameterMap();
		for (String parameter : parameters.keySet()) {
			StringTokenizer st = new StringTokenizer(parameter, "_");
			try {
				int indice = Integer.parseInt(st.nextToken());
				String signo = st.nextToken();
				int signoN = (signo.equals("1")) ? 4 : (signo.equals("2") ? 1 : 2);
				pro[indice] += signoN;
			} catch (Exception e) {
				// TODO: handle exception
			}
			// log.debug(sb.toString());
		}
		for (int i = 0; i < pro.length; i++) {
			if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6))
				dobles++;
			else if (pro[i] == 7)
				triples++;
		}
		//pendiente revisar el pleno al 15, ya que puede ser multiple y eso cambia el precio de la apuesta
		Bet bet = new Bet();
		bet.setPrice(new Double(0.5 * Math.pow(2, dobles) * Math.pow(3, triples)).floatValue());
		RoundBets roundBet = new RoundBets();
		roundBet.addBet(bet);

		dto.setRoundBet(roundBet);

		return dto;

	}

	public int isBetAllowed(int dobles, int doblesRed, int triples, int triplesRed){
		boolean redOk = false;
		boolean betOk = false;
		
		if ((triplesRed>0) && (doblesRed>0)) //QUINIELA REDUCIDA
		{
			if((triplesRed == 4) && (doblesRed == 0)){ //Reduccion 1 (4T: 9 apuestas)
				redOk= true;
				if ( ((triples == 7) && (dobles == 0)) || ((triples == 6) && (dobles <= 2)) ||
					 ((triples == 5) && (dobles <= 3)) || ((triples == 4) && (dobles <= 5)) ||
					 ((triples == 3) && (dobles <= 7))){
					betOk = true;
				}
				
			}else if((triplesRed == 0) && (doblesRed == 7)){ //Reduccion 2 (7D: 16 apuestas)
				redOk = true; 
				if ( ((triples == 7) && (dobles == 0)) || ((triples == 6) && (dobles <= 1)) ||
					 ((triples == 5) && (dobles <= 3)) || ((triples == 4) && (dobles <= 5)) ||
					 ((triples == 3) && (dobles <= 7)) || ((triples == 2) && (dobles <= 8)) ||
					 ((triples == 1) && (dobles <= 10))|| ((triples == 0) && (dobles <= 12)))
				{
					betOk = true;
				}
			}else if((triplesRed == 3) && (doblesRed == 3)){ //Reduccion 3 (3T + 3D: 24 apuestas)
				redOk = true;
				if ( ((triples == 6) && (dobles == 0)) || ((triples == 5) && (dobles <= 2)) ||
					 ((triples == 4) && (dobles <= 4)) || ((triples == 3) && (dobles <= 5)) ||
					 ((triples == 2) && (dobles <= 6)) || ((triples == 1) && (dobles <= 9)) ||
					 ((triples == 0) && (dobles <= 11)))
				{
					betOk = true;
				}
			}else if((triplesRed == 2) && (doblesRed == 6)){ //Reduccion 4 (2T + 6D: 64 apuestas)
				redOk = true;
				if ( ((triples == 5) && (dobles <= 1)) || ((triples == 4) && (dobles <= 2)) ||
					 ((triples == 3) && (dobles <= 4)) || ((triples == 2) && (dobles <= 6)) ||
					 ((triples == 1) && (dobles <= 8)) || ((triples == 0) && (dobles <= 9)))
				{
					betOk = true;
				}
			}else if((triplesRed == 8) && (doblesRed == 0)){ //Reduccion 5 (8T: 81 apuestas)
				redOk = true;
				if ( ((triples == 5) && (dobles == 0)) || ((triples == 4) && (dobles <= 2)) ||
					 ((triples == 3) && (dobles <= 3)) || ((triples == 2) && (dobles <= 5)) ||
					 ((triples == 1) && (dobles <= 8)) || ((triples == 0) && (dobles <= 9)))
				{
					betOk = true;
				}
			}else if((triplesRed == 0) && (doblesRed == 11)){ //Reduccion 6 (11D: 132 apuestas)
				redOk = true;
				if ( ((triples == 3) && (dobles <= 4)) || ((triples == 2) && (dobles <= 6)) ||
					 ((triples == 1) && (dobles <= 8)) || ((triples == 0) && (dobles <= 9)))
					{
						betOk = true;
					}
			}
			
			if (!redOk)
				return -1;
			
			if (!betOk)
				return -2;
			
		}else{ //QUINIELA DIRECTA
			if ((triples == 9) && (dobles == 0)){
				betOk = true;
			}else if ((triples == 8) && (dobles <= 2)){
				betOk = true;
			}else if ((triples == 7) && (dobles <= 3)){
				betOk = true;
			}else if ((triples == 6) && (dobles <= 5)){
				betOk = true;
			}else if ((triples == 5) && (dobles <= 7)){
				betOk = true;
			}else if ((triples == 4) && (dobles <= 8)){
				betOk = true;
			}else if ((triples == 3) && (dobles <= 10)){
				betOk = true;
			}else if ((triples == 2) && (dobles <= 11)){
				betOk = true;
			}else if ((triples == 1) && (dobles <= 13)){
				betOk = true;
			}else if ((triples == 0) && (dobles <= 14)){
				betOk = true;
			}
			
			if (!betOk)
				return -2;
			
		}
		
		
		return 0;
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
			
			switch (isBetAllowed(dobles, doblesRed, triples, triplesRed))
			{
			case 0:
				float price = new Double(0.5 * Math.pow(2, dobles) * Math.pow(3, triples)).floatValue();

				float balance = new Float(userAlterQ.getBalance()).floatValue();
				if (balance - price > 0) {
					balance -= price;
					userAlterQ.setBalance("" + balance);
					Bet bet = new Bet();
					bet.setPrice(price);
					bet.setBet(apuesta);
					bet.setUser(userAlterQ.getId());
					bet.setCompany(company);
					bet.setDateCreated(new Date());
					bet.setDateUpdated(new Date());
					bet.setId(new ObjectId().toStringMongod());
					StringBuffer sb = new StringBuffer();
					sb.append("New Bet: season=" + season + " round=" + round + " user=" + bet.getUser() + " bet=" + bet.getBet());
					log.debug(sb.toString());

					// Insert new bet into the BBDD
					betDao.addBet(season, round, bet);
					userDao.save(userAlterQ);

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
