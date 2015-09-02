package org.alterq.mvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.AdminData;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.apache.commons.lang3.StringUtils;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private AdminDataDao adminDataDao;

	// TODO get company from user, session .....

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseDto login(@RequestBody UserAlterQ user, HttpServletResponse response) {
		log.debug("login name:" + user.getId() + "-:pwd:********");
		UserAlterQ userValidate = userDao.validateLogin(user.getId(), user.getPwd());
		ResponseDto dto = new ResponseDto();
		AdminData ad;
		if (userValidate != null) {
			String sessionID = sessionDao.startSession(userValidate.getId());
			log.debug("Session ID is:" + sessionID);
			response.addCookie(new Cookie("session", sessionID));
			//TODO create function to hide privacy data
			userValidate.setPwd("********");
			dto.setUserAlterQ(userValidate);
			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		} else {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_VALIDATE);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_VALIDATE));
			dto.addErrorDto(error);
			dto.setUserAlterQ(null);
			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		}
		dto.setAdminData(ad);
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ResponseDto getUser(@CookieValue(value = "session", defaultValue = "") String cookieSession) {
		log.debug("init LoginController.getUser");
		log.debug("session:" + cookieSession);
		ResponseDto dto = new ResponseDto();
		AdminData ad;
		if (StringUtils.isNotBlank(cookieSession)) {
			String idUserAlterQ = sessionDao.findUserAlterQIdBySessionId(cookieSession);
			UserAlterQ userAlterQ = userDao.findById(idUserAlterQ);
			//TODO create function to hide privacy data
			userAlterQ.setPwd("********");
			dto.setUserAlterQ(userAlterQ);
			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		} else {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_IN_SESSION);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NOT_IN_SESSION));
			dto.addErrorDto(error);
			dto.setUserAlterQ(null);
			ad = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		}
		dto.setAdminData(ad);
		
		return dto;
	}

}
