package org.alterq.validator;

import org.alterq.dto.ErrorDto;
import org.alterq.exception.ValidatorException;
import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.apache.commons.lang3.StringUtils;
import org.arch.core.i18n.resources.MessageLocalizedResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidator {
	@Autowired
	@Qualifier("messageLocalizedResources")
	private MessageLocalizedResources messageLocalizedResources;
	
	public void isCompanyOk(String company)throws ValidatorException {
		if (!StringUtils.isNumeric(company)){
			ErrorDto dto = new ErrorDto();
			dto.setIdError(MessageResourcesNameEnum.DATA_NOT_NUMERIC);
			dto.setStringError(messageLocalizedResources.resolveLocalizedErrorMessage(MessageResourcesNameEnum.DATA_NOT_NUMERIC));
			throw new ValidatorException(dto);
		}
	}
	public void isCompanyOk(int company)throws ValidatorException {
		isCompanyOk(""+company);
	}

}
