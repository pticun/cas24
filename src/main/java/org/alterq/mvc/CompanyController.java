package org.alterq.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.alterq.converter.UserAlterQConverter;
import org.alterq.domain.Company;
import org.alterq.domain.RolCompany;
import org.alterq.domain.SequenceId;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.ValidatorException;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.UserAlterQSecurity;
import org.alterq.util.RolCompanyComparator;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.alterq.util.enumeration.SequenceNameEnum;
import org.alterq.validator.CompanyValidator;
import org.alterq.validator.UserAlterQValidator;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.types.ObjectId;
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
@RequestMapping(value = {  "/company" })
public class CompanyController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CompanyValidator companyValidator;
	@Autowired
	private UserAlterQValidator userAlterQValidator;
	@Autowired
	private UserAlterQDao userDao;
	@Autowired
	private UserAlterQSecurity userSecurity;
	@Autowired
	private SequenceIdDao sequenceDao;
	@Autowired
	private UserAlterQConverter userAlterQConverter;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "{company}")
	public @ResponseBody ResponseDto getCompany(@PathVariable int company) {
		ResponseDto dto = new ResponseDto();
		log.debug("init CompanyController.getCompany");
		try {
			companyValidator.isCompanyOk(company);
			Company companyResult=companyDao.findByCompany(company);
			dto.setCompany(companyResult);
		} catch (ValidatorException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}
		return dto;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/myaccount/{idUser:.+}")
	public @ResponseBody ResponseDto getCompanyUser(@PathVariable String idUser) {
		ResponseDto dto = new ResponseDto();
		log.debug("init CompanyController.getCompany");
		try {
			UserAlterQ user=new UserAlterQ();
			user.setId(idUser);
			userAlterQValidator.isUserIdOk(user);
			UserAlterQ userAlterQ = userDao.findById(idUser);
			List<RolCompany> rc=userAlterQ.getRols();
			HashSet<Company> uniqueValues = new HashSet<Company>();
			for (RolCompany rolCompany : rc) {
				int companyId=rolCompany.getCompany();
				uniqueValues.add(companyDao.findByCompany(companyId));
//				dto.setCompany(companyDao.findByCompany(comçpanyId));
			}
			List<Company> listCompany = new ArrayList<Company>(uniqueValues);
			dto.setCompany(listCompany);
		} catch (ValidatorException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}
		return dto;

	}
	
	@RequestMapping(method = RequestMethod.POST, value="/{idUser:.+}")
	public @ResponseBody
	ResponseDto createCompany(@CookieValue(value = "session", defaultValue = "") String cookieSession,@PathVariable String idUser, @RequestBody Company company, HttpServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug("init CompanyController.createCompany");
		}
		ResponseDto dto = new ResponseDto();
		try {
			UserAlterQ user=new UserAlterQ();
			user.setId(idUser);
			userAlterQValidator.isUserIdOk(user);

			UserAlterQ userRetrieve=userDao.findById(user.getId());

			company.setId(new ObjectId().toHexString());
			SequenceId bean = sequenceDao.findById(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
			int seq = sequenceDao.getNextSequenceId(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
			company.setCompany(seq);
			companyDao.add(company);

			List<RolCompany> rolCompany=new ArrayList<RolCompany>();
			//get from json web part
			List<RolCompany> rolCompanyForUpdate=new ArrayList<RolCompany>();
			rolCompany=userRetrieve.getRols();
			
			RolCompany rolAdmin=new RolCompany();
			rolAdmin.setCompany(company.getCompany());
			rolAdmin.setRol(RolNameEnum.ROL_ADMIN.getValue());
			rolCompany.add(rolAdmin);
			
			userRetrieve.setRols(rolCompany);
			//update user
			userDao.save(userRetrieve);

			dto.setCompany(company);
			dto.setUserAlterQ(userAlterQConverter.converterUserAlterQInResponseDto(userRetrieve));
			
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
