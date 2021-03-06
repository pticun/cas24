package org.alterq.mvc;

import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.validator.UserAlterQValidator;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class ControllerMVCForTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private RoundDao roundDao;
	@Autowired
	SendMail sendMail;
	@Autowired
	private UserAlterQSecurity userAlterQSecurity;
	@Autowired
	private UserAlterQValidator userAlterQValidator;


	@RequestMapping(method = RequestMethod.POST, value = "/deleteUser")
	public @ResponseBody
	ResponseDto deleteUser(@RequestBody UserAlterQ user, HttpServletResponse response) {
		ResponseDto dto = new ResponseDto();
		try {
			if (log.isDebugEnabled()) {
				log.debug("init AccountControllerMVCForTest.deleteUser");
				log.debug("user.getId:" + user.getId());
			}
			userDao.remove(user);
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
