package org.alterq.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.AlterQException;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.RolCompanyComparator;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.alterq.validator.RolCompanyValidator;
import org.alterq.validator.UserAlterQValidator;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.arch.core.mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private SessionAlterQDao sessionDao;
	@Autowired
	private UserAlterQSecurity userAlterQSecurity;
	@Autowired
	private UserAlterQValidator userAlterQValidator;
	@Autowired
	private RolCompanyValidator rolCompanyValidator;

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ResponseDto updateCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable String id, @RequestBody UserAlterQ user) {
		ResponseDto dto = new ResponseDto();
		//NOT IMPLEMENTED
		return dto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseDto addCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession,@RequestBody UserAlterQ user,List<RolCompany> rcList) {
		ResponseDto dto = new ResponseDto();
		try {
			userAlterQValidator.isUserIdOk(user);
			userAlterQSecurity.existsUserAlterQ(user);
			userAlterQSecurity.isSameUserInSession(user.getId(), cookieSession);
			//Validate List<RolCompany>
			rolCompanyValidator.isRolCompanyEmptyAll(rcList);
			List<RolCompany> oldRolCompany=new ArrayList<RolCompany>();
			oldRolCompany=userDao.getRols(user);
			
			//SORT not necessary
			Collections.sort(oldRolCompany, new RolCompanyComparator());
			Collections.sort(rcList,new RolCompanyComparator());
			
			for (RolCompany rolCompanyForUpdate : rcList) {
				//only add for RolUser
				if(RolNameEnum.ROL_USER.getValue()== rolCompanyForUpdate.getRol()){
					if (!oldRolCompany.contains(rolCompanyForUpdate)){
						oldRolCompany.add(rolCompanyForUpdate);
					}
				}
			}
			
			//compare rol from user mongo to List<RolCompany> rcList
			//if company not in list add rol_company only ROL_USER
			
		}catch (AlterQException ex){
			dto.addErrorDto(ex.getErrorDto());
			log.error(ExceptionUtils.getStackTrace(ex));
		} catch (Exception e) {
			ErrorDto error = new ErrorDto();
			error.setIdError(MessageResourcesNameEnum.GENERIC_ERROR);
			error.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.GENERIC_ERROR));
			dto.addErrorDto(error);
			log.error(ExceptionUtils.getStackTrace(e));
		}
		
		return dto;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseDto getAllCompanyRolForUser(@RequestBody UserAlterQ user, HttpServletResponse response) {
		ResponseDto dto = new ResponseDto();
		UserAlterQ userReturn=new UserAlterQ();
		userReturn.setId(user.getId());
		userReturn.setRols(userDao.getRols(user));
		dto.setUserAlterQ(userReturn);
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
