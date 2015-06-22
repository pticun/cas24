package org.alterq.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.AlterQException;
import org.alterq.exception.SecurityException;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.alterq.validator.UserAlterQValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private GeneralDataDao generalDataDao;
	@Autowired
	SendMail sendMail;
	@Autowired
	private UserAlterQSecurity userSecurity;
	@Autowired
	private UserAlterQValidator userAlterQValidator;

	@RequestMapping(method = RequestMethod.PUT, value = "/{id:.+}/update")
	public @ResponseBody
	ResponseDto updateUserAlterQ(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable String id, @RequestBody UserAlterQ user) {
		ResponseDto dto = new ResponseDto();
		try {
			userSecurity.isSameUserInSession(id, cookieSession);
			UserAlterQ userAlterQ = userDao.findById(id);
			if (user.getNick() != null)
				userAlterQ.setNick(user.getNick());
			if (user.getName() != null)
				userAlterQ.setName(user.getName());
			if (user.getSurnames() != null)
				userAlterQ.setSurnames(user.getSurnames());
			if (user.getTypeID() != 0)
				userAlterQ.setTypeID(user.getTypeID());
			if (user.getIdCard() != null)
				userAlterQ.setIdCard(user.getIdCard());
			if (user.getPwd() != null)
				userAlterQ.setPwd(user.getPwd());
			if (user.getPhoneNumber() != null)
				userAlterQ.setPhoneNumber(user.getPhoneNumber());
			if (user.getBirthday() != null)
				userAlterQ.setBirthday(user.getBirthday());
			if (user.getCity() != null)
				userAlterQ.setCity(user.getCity());
			if (StringUtils.isNumeric(new String("" + user.getAutomatics())))
				userAlterQ.setAutomatics(user.getAutomatics());
			/*
			 * if (user.getBalance() != null)
			 * userAlterQ.setBalance(user.getBalance());
			 */
			userAlterQ.setDateUpdated(new Date());
			userDao.save(userAlterQ);
			dto.setUserAlterQ(userAlterQ);
		} catch (SecurityException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}

		return dto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/forgotPwd")
	public @ResponseBody
	ResponseDto forgotPwd(@RequestBody UserAlterQ user) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.forgotPwd");
			log.debug("user.getId:" + user.getId());
		}
		// TODO SEC control user exist
		// TODO SEC last time did action
		// TODO ARC send mail
		UserAlterQ userAlterQ = userDao.findById(user.getId());
		ResponseDto dto = new ResponseDto();
		if (userAlterQ != null) {
			String pwd = RandomStringUtils.random(10, true, true);
			sendMail.sendMailWithTemplate("racsor@gmail.com", "CAMBIO PWDALTERQ", pwd);
			userAlterQ.setPwd(pwd);
			userDao.save(userAlterQ);

			ErrorDto error = new ErrorDto();
			error.setIdError("KO sendmail");
			error.setStringError("A mail has been send to email address.");
			dto.addErrorDto(error);
		} else {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_NOT_EXIST);
			error.setStringError("User not exist");
			dto.addErrorDto(error);
		}

		return dto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ResponseDto createUserAlterQ(@RequestBody UserAlterQ user, HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug("init AccountController.createUserAlterQ");
			log.debug("user.getId:" + user.getId());
		}
		ResponseDto dto = new ResponseDto();
		try {
			//TODO check company validator
			userAlterQValidator.createUserAlterQ(user);
			user.setActive(true);
			user.setBalance("0");
//			user.setCompany(AlterQConstants.COMPANY);
			user.setDateCreated(new Date());
			user.setDateUpdated(new Date());
			//At this moment user belongs defect conpany
			List<RolCompany> rcL=new ArrayList<RolCompany>();
			RolCompany rc=new RolCompany();
			rc.setCompany(AlterQConstants.COMPANY);
			if(StringUtils.isBlank(""+user.getCompany())){
				rc.setCompany(AlterQConstants.COMPANY);
			}
			else{
				rc.setCompany(user.getCompany());
			}
			rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
			rcL.add(rc);
			user.setRols(rcL);
			userDao.create(user);
			dto.setUserAlterQ(user);
			String sessionID = sessionDao.startSession(user.getId());
			log.debug("Session ID is:" + sessionID);
			response.addCookie(new Cookie("session", sessionID));
		}catch (AlterQException ex){
			dto.addErrorDto(ex.getErrorDto());
			log.error(ExceptionUtils.getStackTrace(ex));
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.USER_ALREADY_EXIST);
			error.setStringError("User already exist");
			dto.addErrorDto(error);
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return dto;
	}
}
