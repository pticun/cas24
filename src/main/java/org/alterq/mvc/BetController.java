package org.alterq.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alterq.domain.Jornada;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.JornadaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/bet")
public class BetController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JornadaDao jornadaDao;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto getLastJornada() {
		ResponseDto dto = new ResponseDto();
		Jornada j = new Jornada();
		try {
			j = jornadaDao.findLastJornada();
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError("10");
			error.setStringError("getLastJornada (i18n error)");
			dto.setErrorDto(error);
			dto.setJornada(null);
		}
		dto.setJornada(j);
		return dto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseDto addBet(@CookieValue(value = "session", defaultValue = "") String cookieSession, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
		}

		Map<String, String[]> parameters = request.getParameterMap();
		for (String parameter : parameters.keySet()) {
			StringBuffer sb = new StringBuffer();
			sb.append("parameter:" + parameter + " value:");
			String[] values = parameters.get(parameter);
			for (int i = 0; i < values.length; i++) {
				sb.append(values[i].toString() + " -");
			}
			log.debug(sb.toString());
		}
		// TODO control security
		ResponseDto dto = new ResponseDto();
		return dto;

	}

}
