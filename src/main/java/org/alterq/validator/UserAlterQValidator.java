package org.alterq.validator;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.dto.ErrorDto;
import org.alterq.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserAlterQValidator {
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;

	public void createUserAlterQ(UserAlterQ user) throws ValidatorException {
		ValidatorException ve = new ValidatorException("errorValidation");
		if (user == null) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(AlterQConstants.USER_NULL);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_NULL));
			ve.addErrorDto(dto);
		}
		//USERNAME
		if (StringUtils.isBlank(user.getName())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(AlterQConstants.USER_NAME_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_NAME_ERROR));
			ve.addErrorDto(dto);
		} else {
			if (StringUtils.length(user.getName()) < 5) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(AlterQConstants.USER_NAME_LENGTH);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_NAME_LENGTH));
				ve.addErrorDto(dto);
			}
		}
		//PHONE 
		if (StringUtils.isBlank(user.getPhoneNumber())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(AlterQConstants.USER_PHONE_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_NAME_ERROR));
			ve.addErrorDto(dto);
		} else {
			if (!StringUtils.isNumeric(user.getPhoneNumber())) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(AlterQConstants.USER_PHONE_NUMERIC);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_PHONE_NUMERIC));
				ve.addErrorDto(dto);
			}
			if (StringUtils.length(user.getPhoneNumber()) < 9) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(AlterQConstants.USER_PHONE_LENGTH);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_PHONE_LENGTH));
				ve.addErrorDto(dto);
			}
		}
		//EMAIL - ID
		EmailValidator validator = EmailValidator.getInstance();
		boolean emailValid = validator.isValid(user.getId());
		if (!emailValid) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(AlterQConstants.USER_MAIL_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_MAIL_ERROR));
			ve.addErrorDto(dto);
		}
		//AUTOMATIC
		if (!StringUtils.isNumeric("" + user.getAutomatics())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(AlterQConstants.USER_AUTOMATIC_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_AUTOMATIC_ERROR));
			ve.addErrorDto(dto);
		}
		//NIFCIF
		NIFCIFValidator nifvalidator=new NIFCIFValidator();
		boolean nifValid=nifvalidator.isValidNIF(user.getIdCard());
		if (!nifValid) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(AlterQConstants.USER_NIF_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(AlterQConstants.USER_NIF_ERROR));
			ve.addErrorDto(dto);
		}
		//CONTROL ERROR
		if (ve.getErrorDto()!=null){
			throw ve;
		}

	}

}
