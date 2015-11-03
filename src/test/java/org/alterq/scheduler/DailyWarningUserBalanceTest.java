package org.alterq.scheduler;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DailyWarningUserBalanceTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DailyWarningUserBalance dailyWarningUserBalance;
	
	@Test
	public void test00() throws Exception {
		dailyWarningUserBalance.executeDailyWarning();
		log.debug("dailyWarning");
		return;
	}


}
