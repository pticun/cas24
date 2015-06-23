package org.alterq.validator;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ErrorDto;
import org.alterq.exception.ValidatorException;
import org.alterq.repo.UserAlterQDao;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.apache.commons.lang3.BooleanUtils;
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

	@Autowired
	private UserAlterQDao dao;

	public void createUserAlterQ(UserAlterQ user) throws ValidatorException {
		ValidatorException ve = new ValidatorException("errorValidation");
		if (user == null) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_NULL);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NULL));
			ve.addErrorDto(dto);
		}
		// CONDICIONES
		if (!BooleanUtils.toBoolean(user.getAccept())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_ACCEPT_CONDITION);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_ACCEPT_CONDITION));
			ve.addErrorDto(dto);
		}
		// USERNAME
		if (StringUtils.isBlank(user.getName())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_NAME_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NAME_ERROR));
			ve.addErrorDto(dto);
		} else {
			if (StringUtils.length(user.getName()) < 5) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(MessageResourcesNameEnum.USER_NAME_LENGTH);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NAME_LENGTH));
				ve.addErrorDto(dto);
			}
		}
		// PASSWORD
		if (StringUtils.isBlank(user.getPwd())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_PASSWORD_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_PASSWORD_ERROR));
			ve.addErrorDto(dto);
		} else {
			if (StringUtils.length(user.getPwd()) < 5) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(MessageResourcesNameEnum.USER_PASSWORD_LENGTH);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_PASSWORD_LENGTH));
				ve.addErrorDto(dto);
			}
		}
		// PHONE
		if (StringUtils.isBlank(user.getPhoneNumber())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_PHONE_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NAME_ERROR));
			ve.addErrorDto(dto);
		} else {
			if (!StringUtils.isNumeric(user.getPhoneNumber())) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(MessageResourcesNameEnum.USER_PHONE_NUMERIC);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_PHONE_NUMERIC));
				ve.addErrorDto(dto);
			}
			if (StringUtils.length(user.getPhoneNumber()) < 9) {
				ErrorDto dto = new ErrorDto();
				dto.setIdError(MessageResourcesNameEnum.USER_PHONE_LENGTH);
				dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_PHONE_LENGTH));
				ve.addErrorDto(dto);
			}
		}
		// AUTOMATIC
		if (!StringUtils.isNumeric("" + user.getAutomatics())) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_AUTOMATIC_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_AUTOMATIC_ERROR));
			ve.addErrorDto(dto);
		}
		// NIFCIF
		NIFCIFValidator nifvalidator = new NIFCIFValidator();
		boolean nifValid = nifvalidator.isValidNIF(user.getIdCard());
		if (!nifValid) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_NIF_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_NIF_ERROR));
			ve.addErrorDto(dto);
		}
		// EMAIL - ID
		EmailValidator validator = EmailValidator.getInstance();
		boolean emailValid = validator.isValid(user.getId());
		if (!emailValid) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_MAIL_ERROR);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_MAIL_ERROR));
			ve.addErrorDto(dto);
		}
		// User already exists
		UserAlterQ bean = dao.findById(user.getId());
		if (bean == null) {
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.USER_ALREADY_EXIST);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.USER_ALREADY_EXIST));
			ve.addErrorDto(dto);
		}
		// CONTROL ERROR
		if (!ve.getErrorDto().isEmpty()) {
			throw ve;
		}

	}

}
