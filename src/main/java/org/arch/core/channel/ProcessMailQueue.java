package org.arch.core.channel;

import org.alterq.dto.MailQueueDto;
import org.springframework.integration.annotation.Gateway;

public interface ProcessMailQueue {

	/**
	 * Process a mailDto.
	 */
	@Gateway(requestChannel = "processMailQueue")
	public void process(MailQueueDto mailDto);

}
