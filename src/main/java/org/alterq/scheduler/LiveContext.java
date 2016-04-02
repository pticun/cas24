package org.alterq.scheduler;

import java.util.Date;

import org.alterq.repo.SessionAlterQDao;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class LiveContext {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao sessionDao;

	@Scheduled(cron = "${app.scheduler.liveContext}")
	public void liveContext() {
		log.debug("Method executed at every 30 minuts.");
		deleteSessionTimeOut();
	}

	private void deleteSessionTimeOut() {
		Date timeToInactive=DateUtils.addMinutes(new Date(), -30);
		sessionDao.deleteInactiveSession(timeToInactive);
	}
}