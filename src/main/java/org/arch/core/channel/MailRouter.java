package org.arch.core.channel;

import org.alterq.dto.MailQueueDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

@MessageEndpoint
public class MailRouter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Process order. Routes based on whether or not the order is a delivery or
	 * pickup.
	 */
	@Router(inputChannel = "processMailQueue")
	public String process(MailQueueDto mailDto) {
		String result = null;

		switch (mailDto.getType()) {
		case Q_DAILYMAIL:
			result = "dailyMail";
			break;
		case Q_RESULTSMAIL:
			result = "resultsMail";
			break;
		case Q_FORGOTMAIL:
			result = "forgotMail";
			break;
		case Q_WITHOUTMONEYMAIL:
			result = "withoutMoneyMail";
			break;
		case Q_BIRTHDAYMAIL:
			result = "birthdayMail";
			break;
		case Q_FINALBETMAIL:
			result = "finalBetMail";
			break;
		case Q_JOINTOCOMPANYMAIL:
			result = "joinToCompany";
			break;
		}
		log.debug("processMailQueue:" + result);

		return result;
	}

}
