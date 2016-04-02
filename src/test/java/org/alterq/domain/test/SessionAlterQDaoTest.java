package org.alterq.domain.test;

import java.util.Date;

import org.alterq.domain.UserAlterQ;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.BetTools;
import org.alterq.util.NumericUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionAlterQDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionAlterQDao dao;

	@Test
	public void AA_testDeleteInactiveSession() throws Exception {
		Date timeToInactive=DateUtils.addMinutes(new Date(), -30);
		dao.deleteInactiveSession(timeToInactive);

		return;
	}

}
