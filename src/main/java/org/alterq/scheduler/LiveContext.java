package org.alterq.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class LiveContext {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "0 30 * * * ?")
	public void demoServiceMethod() {
		log.debug("Method executed at every 30 minuts.");
	}
}
