package org.alterq.validator;

import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.dto.ErrorDto;
import org.alterq.exception.ValidatorException;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RolCompanyValidator {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;

	public void isRolCompanyEmptyRol(RolCompany rolCompany) throws ValidatorException {
		ValidatorException ve = new ValidatorException("errorValidation");
		// ROL
		if (StringUtils.isBlank("" + rolCompany.getRol())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.DATA_NOT_NUMERIC);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.DATA_NOT_NUMERIC));
			ve.addErrorDto(dto);
		}
		try {
			Integer.parseInt("" + rolCompany.getRol());
		} catch (NumberFormatException e) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.DATA_NOT_NUMERIC);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.DATA_NOT_NUMERIC));
			ve.addErrorDto(dto);
			log.error(ExceptionUtils.getStackTrace(e));
		}
		isRolValid(rolCompany);
		// CONTROL ERROR
		if (!ve.getErrorDto().isEmpty()) {
			throw ve;
		}
	}

	public void isRolValid(RolCompany rolCompany) throws ValidatorException {
		boolean isRolValid = false;
		for (RolNameEnum rc : RolNameEnum.values()) {
			if (rc.getValue() == rolCompany.getRol()) {
				isRolValid = true;
				break;
			}
		}
		if (!isRolValid) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.DATA_NOT_VALID);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.DATA_NOT_VALID));
			throw new ValidatorException(dto);
		}
	}

	public void isRolCompanyEmptyCompany(RolCompany rolCompany) throws ValidatorException {
		ValidatorException ve = new ValidatorException("errorValidation");
		// COMPANY
		if (StringUtils.isBlank("" + rolCompany.getCompany())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.DATA_NOT_NUMERIC);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.DATA_NOT_NUMERIC));
			ve.addErrorDto(dto);
		}
		try {
			Integer.parseInt("" + rolCompany.getCompany());
		} catch (NumberFormatException e) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.DATA_NOT_NUMERIC);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.DATA_NOT_NUMERIC));
			ve.addErrorDto(dto);
			log.error(ExceptionUtils.getStackTrace(e));
		}
		// CONTROL ERROR
		if (!ve.getErrorDto().isEmpty()) {
			throw ve;
		}
	}

	public void isRolCompanyEmptyAll(RolCompany rolCompany) throws ValidatorException {
		ValidatorException ve = new ValidatorException("errorValidation");
		try {
			if (rolCompany == null) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(MessageResourcesNameEnum.OBJECT_NULL);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.OBJECT_NULL));
				ve.addErrorDto(dto);
				throw ve;
			}
			isRolCompanyEmptyRol(rolCompany);
			isRolCompanyEmptyCompany(rolCompany);
		} catch (ValidatorException vex) {
			ve = vex;
		}
		// CONTROL ERROR
		if (!ve.getErrorDto().isEmpty()) {
			throw ve;
		}
	}

	public void isRolCompanyEmptyAll(List<RolCompany> rolCompanyList) throws ValidatorException {
		ValidatorException ve = new ValidatorException("errorValidation");
		try {
			for (RolCompany rolCompany : rolCompanyList) {
				if (rolCompany == null) {
					ErrorDto dto = new ErrorDto();
					dto.setIdError(MessageResourcesNameEnum.OBJECT_NULL);
					dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.OBJECT_NULL));
					ve.addErrorDto(dto);
					throw ve;
				}
				isRolCompanyEmptyAll(rolCompany);
			}
		} catch (ValidatorException vex) {
			ve = vex;
		}
		// CONTROL ERROR
		if (!ve.getErrorDto().isEmpty()) {
			throw ve;
		}
	}

}
