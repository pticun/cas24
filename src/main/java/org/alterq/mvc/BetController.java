package org.alterq.mvc;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.alterq.domain.Bet;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ErrorType;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "/bet")
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

	// TODO get company from user, session .....
	int company = 1;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto getLastRound() {
		ResponseDto dto = new ResponseDto();
		Round j = new Round();
		try {
			// TODO create a new Service layer
			GeneralData generalData = generalDataDao.findByCompany(company);
			j = roundDao.findBySeasonRound(generalData.getSeason(), generalData.getRound());
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(ErrorType.GET_LAST_ROUND);
			error.setStringError("getLastJornada (i18n error)");
			dto.setErrorDto(error);
			dto.setRound(null);
		}
		dto.setRound(j);
		return dto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "price")
	public @ResponseBody
	ResponseDto price(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request) {
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
		Bet bet = new Bet();
		bet.setPrice(new Double(0.5 * Math.pow(2, dobles) * Math.pow(3, triples)).floatValue());
		RoundBets roundBet = new RoundBets();
		roundBet.addBet(bet);

		dto.setRoundBet(roundBet);

		// Insert new bet into the BBDD
		// betDao.addBet(seasonInt, roundInt, apuestaBet);

		return dto;

	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseDto addBet(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
		}
		UserAlterQ userAlterQ = null;
		if (StringUtils.isNotBlank(cookieSession)) {
			String idUserAlterQ = sessionDao.findUserAlterQIdBySessionId(cookieSession);
			userAlterQ = userDao.findById(idUserAlterQ);
		}
		// TODO control security
		ResponseDto dto = new ResponseDto();

		if (userAlterQ == null) {
			ErrorDto error = new ErrorDto();
			error.setIdError(ErrorType.USER_NOT_IN_SESSION);
			error.setStringError("user not in Session (i18n error)");
			dto.setErrorDto(error);
			dto.setUserAlterQ(null);
			return dto;
		}

		String apuesta = "";
		int pro[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

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
		int dobles = 0;
		int triples = 0;
		for (int i = 0; i < pro.length; i++) {
			apuesta += pro[i];
			if ((pro[i] == 3) || (pro[i] == 5) || (pro[i] == 6))
				dobles++;
			else if (pro[i] == 7)
				triples++;
		}

		// data for test only!!
		String season = request.getParameter("season");
		String round = request.getParameter("round");

		int seasonInt = Integer.parseInt(season);
		int roundInt = Integer.parseInt(round);

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
			StringBuffer sb = new StringBuffer();
			sb.append("New Bet: season=" + season + " round=" + round + " user=" + bet.getUser() + " bet=" + bet.getBet());
			log.debug(sb.toString());

			// Insert new bet into the BBDD
			betDao.addBet(seasonInt, roundInt, bet);
			userDao.save(userAlterQ);

		} else {
			ErrorDto error = new ErrorDto();
			error.setIdError(ErrorType.USER_NOT_ENOUGH_MONEY);
			error.setStringError("user not enough money (i18n error)");
			dto.setErrorDto(error);
			dto.setUserAlterQ(null);
		}

		return dto;

	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "season/{season}/round/{round}")
	public @ResponseBody
	RoundBets findAllBetsParams(@RequestParam(value = "season") int season, @RequestParam(value = "round") int round) {
		// TODO this call must be request for an AdminUser
		return betDao.findAllBets(season, round);
	}

	// TODO http://www.coderanch.com/t/622271/Spring/Spring-Path-Variable
	// TODO with /bet/season/2013/round/1/user/idmail@arroba.es not working but
	// working /bet/season/2013/round/1/user/idmail@arroba.es/
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "season/{season}/round/{round}/user/{id:.+}")
	public @ResponseBody
	ResponseDto findAllUserBetsParams(@PathVariable int season, @PathVariable int round, @PathVariable String id) {
		ResponseDto dto = new ResponseDto();
		RoundBets rb = betDao.findAllUserBets(season, round, id);
		dto.setRoundBet(rb);
		return dto;
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
