package org.alterq.mvc;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/myaccount")
public class AccountController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;

	@RequestMapping(method = RequestMethod.GET)
	public String initPage() {
		log.debug("init myaccount.jsp");
		return "myaccount";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id:.+}")
	public @ResponseBody
	ResponseDto updateUserAlterQ(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable String id, UserAlterQ user) {
		if (log.isDebugEnabled()){
			log.debug("init AccountController.updateUserAlterQ");
			log.debug("session:" + cookieSession);
			log.debug("id:" + id);
			log.debug("user.getId:" + user.getId());
		}
		ResponseDto dto = new ResponseDto();
		if (StringUtils.isNotBlank(cookieSession)) {
			String idUserAlterQ = sessionDao.findUserAlterQIdBySessionId(cookieSession);
			log.debug("idUserAlterQ:" + idUserAlterQ);
			if (StringUtils.equals(idUserAlterQ, id)) {
				UserAlterQ userAlterQ = userDao.findById(idUserAlterQ);

				dto.setUserAlterQ(userAlterQ);
			} else {
				// id not match with sessionID
				ErrorDto error = new ErrorDto();
				error.setIdError("3");
				error.setStringError("id not match with sessionID (i18n error)");
				dto.setErrorDto(error);
				dto.setUserAlterQ(null);
			}
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
