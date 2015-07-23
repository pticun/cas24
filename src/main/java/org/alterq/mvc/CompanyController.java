package org.alterq.mvc;

import java.util.List;

import org.alterq.domain.Company;
import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ResponseDto;
import org.alterq.exception.ValidatorException;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.validator.CompanyValidator;
import org.alterq.validator.UserAlterQValidator;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
			for (RolCompany rolCompany : rc) {
				int companyId=rolCompany.getCompany();
				dto.setCompany(companyDao.findByCompany(companyId));
			}
		} catch (ValidatorException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			dto.addErrorDto(e.getError());
		}
		return dto;

	}

	
}
