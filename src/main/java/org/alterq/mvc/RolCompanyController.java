package org.alterq.mvc;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.validator.UserAlterQValidator;
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
@RequestMapping(value = "/myaccount/{id:.+}/rolcompany")
public class RolCompanyController {
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

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ResponseDto updateCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable String id, @RequestBody UserAlterQ user) {
		ResponseDto dto = new ResponseDto();
		return dto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseDto addCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession,@RequestBody UserAlterQ user,List<RolCompany> rcList) {
		ResponseDto dto = new ResponseDto();
		try {
			userSecurity.isSameUserInSession(user.getId(), cookieSession);
			//get user from mongo
			//compare rol from user mongo to List<RolCompany> rcList
			//if company not in list add rol_company only ROL_USER
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseDto getCompanyRol(@RequestBody UserAlterQ user, HttpServletResponse response) {
		ResponseDto dto = new ResponseDto();
//		userSecurity.isSameUserInSession(user.getId(), cookieSession);
		//get user from mongo
		//get company return all List<RolCompany> rcList for COMPANY
		return dto;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody ResponseDto deleteCompanyRol(@RequestBody UserAlterQ user, HttpServletResponse response) {
		ResponseDto dto = new ResponseDto();
		//get user from mongo
		//compare rol from user mongo to List<RolCompany> rcList
		//if company in list add delete rol_company only ROL_USER
		return dto;
	}
}
