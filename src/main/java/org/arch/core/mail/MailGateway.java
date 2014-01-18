package org.arch.core.mail;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.router.RecipientListRouter.Recipient;

public interface MailGateway {
	@Gateway
	public void sendMail(Recipient recipient);
}
