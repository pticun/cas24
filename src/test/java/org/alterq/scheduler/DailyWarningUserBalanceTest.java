package org.alterq.scheduler;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)public class DailyWarningUserBalanceTest {

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
