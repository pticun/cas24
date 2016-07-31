package org.alterq.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.alterq.converter.UserAlterQConverter;
import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.AlterQException;
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
	private UserAlterQSecurity userSecurity;
	@Autowired
	private UserAlterQValidator userValidator;
	@Autowired
	private RolCompanyValidator rolCompanyValidator;
	@Autowired
	private UserAlterQConverter userAlterQConverter;
	
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ResponseDto updateCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession, @PathVariable String id, @RequestBody UserAlterQ user) {
		ResponseDto dto = new ResponseDto();
		//NOT IMPLEMENTED
		return dto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseDto addCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession,@RequestBody UserAlterQ user) {
		ResponseDto dto = new ResponseDto();
		try {
			userValidator.isUserIdOk(user);
			userSecurity.notExistsUserAlterQ(user);
			userSecurity.isSameUserInSession(user.getId(), cookieSession);
			//Validate List<RolCompany>
			rolCompanyValidator.isRolCompanyEmptyAll(user.getRols());
			List<RolCompany> rolCompany=new ArrayList<RolCompany>();
			//get from json web part
			List<RolCompany> rolCompanyForUpdate=new ArrayList<RolCompany>();
			rolCompanyForUpdate=user.getRols();
			UserAlterQ userRetrieve=userDao.findById(user.getId());
			rolCompany=userRetrieve.getRols();
			
			//compare rol from user mongo to List<RolCompany> rcList
			//if company not in list add rol_company only ROL_USER
			for (RolCompany rolCompanyTemp : rolCompanyForUpdate) {
				if(RolNameEnum.ROL_USER.getValue()== rolCompanyTemp.getRol()){
					if (!rolCompany.contains(rolCompanyTemp)){
						rolCompany.add(rolCompanyTemp);
						log.debug("el usuario:"+userRetrieve.getId()+" se ha unido(ROL_USER) a la company:"+rolCompanyTemp.getCompany());
//						userDao.addRolForCompany(user, rolCompany);
					}
				}
			}
			Collections.sort(rolCompany, new RolCompanyComparator());
			userRetrieve.setRols(rolCompany);
			//update user
			userDao.save(userRetrieve);
			dto.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userRetrieve));
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
	public @ResponseBody ResponseDto deleteCompanyRol(@CookieValue(value = "session", defaultValue = "") String cookieSession,@RequestBody UserAlterQ user) {
		ResponseDto dto = new ResponseDto();
		//get user from mongo
		//compare rol from user mongo to List<RolCompany> rcList
		//if company in list add delete rol_company only ROL_USER
		try {
			userValidator.isUserIdOk(user);
			userSecurity.notExistsUserAlterQ(user);
			userSecurity.isSameUserInSession(user.getId(), cookieSession);
			//Validate List<RolCompany>
			rolCompanyValidator.isRolCompanyEmptyAll(user.getRols());
			List<RolCompany> rolCompany=new ArrayList<RolCompany>();
			//get from json web part
			List<RolCompany> rolCompanyForUpdate=new ArrayList<RolCompany>();
			rolCompanyForUpdate=user.getRols();
			UserAlterQ userRetrieve=userDao.findById(user.getId());
			rolCompany=userRetrieve.getRols();
			for (RolCompany rolCompanyTemp : rolCompanyForUpdate) {
				if(RolNameEnum.ROL_USER.getValue()== rolCompanyTemp.getRol()){
					if (rolCompany.contains(rolCompanyTemp)){
						rolCompany.remove(rolCompanyTemp);
						log.debug("el usuario:"+userRetrieve.getId()+" se ha borrado(ROL_USER) de la company:"+rolCompanyTemp.getCompany());
					}
				}
			}
			Collections.sort(rolCompany, new RolCompanyComparator());
			userRetrieve.setRols(rolCompany);
			//update user
			userDao.save(userRetrieve);
			dto.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userRetrieve));
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
}
