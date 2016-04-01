package org.alterq.domain.test;

import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.BetTools;
import org.alterq.util.NumericUtil;
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
public class UserAlterQUpdateBalanceDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;
	@Autowired
	private BetTools betTools;
	@Autowired
	private NumericUtil numericUtil;

	@Test
	public void AA_testUpdateBalance() throws Exception {
		UserAlterQ userAlterQ = dao.findById("racsor@gmail.com");
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);

		userAlterQ.setCity("newCity");;
		userAlterQ.setBalance("25.34");
		dao.updateBalance(userAlterQ);

		UserAlterQ userAlterQNewBalance = dao.findById("racsor@gmail.com");

		log.debug("oldBalance:" + userAlterQ.getBalance() + "-newBalance:" + userAlterQNewBalance.getBalance());
		log.debug("oldcity:" + userAlterQ.getCity() + "-newCity:" + userAlterQNewBalance.getCity());
		Assert.assertEquals(userAlterQ.getBalance(), userAlterQNewBalance.getBalance());

		return;
	}

}
