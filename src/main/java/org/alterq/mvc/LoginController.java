package org.alterq.mvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.MemberDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody
	ResponseDto login(UserAlterQ user, HttpServletResponse response) {
		log.debug("login name:" + user.getId() + "-:pwd:" + user.getPwd());
		UserAlterQ userValidate = userDao.validateLogin(user.getId(), user.getPwd());
		ResponseDto dto = new ResponseDto();
		if (userValidate != null) {
			String sessionID = sessionDao.startSession(userValidate.getId());
			log.debug("Session ID is:" + sessionID);

			response.addCookie(new Cookie("session", sessionID));
			dto.setUserAlterQ(userValidate);
		} else {
			ErrorDto error = new ErrorDto();
			error.setIdError("1");
			error.setStringError("user not validate (i18n error)");
			dto.setErrorDto(error);
			dto.setUserAlterQ(null);
		}
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto getUser(@CookieValue(value = "session", defaultValue = "") String cookieSession) {
		log.debug("init LoginController.getUser");
		log.debug("session:" + cookieSession);
		ResponseDto dto = new ResponseDto();
		if (StringUtils.isNotBlank(cookieSession)) {
			String idUserAlterQ = sessionDao.findUserAlterQIdBySessionId(cookieSession);
			UserAlterQ userAlterQ = userDao.findById(idUserAlterQ);
			dto.setUserAlterQ(userAlterQ);
		} else {
			ErrorDto error = new ErrorDto();
			error.setIdError("2");
			error.setStringError("user not in Session (i18n error)");
			dto.setErrorDto(error);
			dto.setUserAlterQ(null);
		}
		return dto;
	}

}
