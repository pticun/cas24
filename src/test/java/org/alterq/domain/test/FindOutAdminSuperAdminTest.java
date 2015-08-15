package org.alterq.domain.test;

import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
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
public class FindOutAdminSuperAdminTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao userAlterQDao;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;

	@Test
	public void retrieveSuperAdmin() {
		UserAlterQ userAlterQ = userAlterQDao.findSuperAdmin();
		Assert.assertNotNull(userAlterQ);
		log.debug("retrieveSuperAdmin="+userAlterQ.getId());
		return;
	}

	@Test
	public void retrieveAdmin() {
		int company=1;
		UserAlterQ userAlterQ = userAlterQDao.findAdminByCompany(company);
		Assert.assertNotNull(userAlterQ);
		log.debug("retrieveAdmin="+userAlterQ.getId());
		return;
	}


}
