package org.arch.core.i18n.resources;

import java.util.Locale;

import org.alterq.util.enumeration.MessageResourcesNameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageLocalizedResources {
	@Autowired
	private MessageSource messageSource;
	Locale currentLocale;

	public MessageLocalizedResources() {
		currentLocale = LocaleContextHolder.getLocale();
	}

	public String resolveLocalizedErrorMessage(String fieldError) {
		String localizedErrorMessage = messageSource.getMessage(fieldError, null, currentLocale);
		return localizedErrorMessage;
	}
	public String resolveLocalizedErrorMessage(MessageResourcesNameEnum fieldError) {
		String localizedErrorMessage = messageSource.getMessage(fieldError.getValue(), null, currentLocale);
		return localizedErrorMessage;
	}

}
