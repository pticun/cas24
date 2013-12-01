package org.alterq.domain.test;

import junit.framework.Assert;

import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class UserAlterQDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;

	@Test
	public void testCreate() {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setName("Primera");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setPwd("password");
		userAlterQ.setId("idmail@arroba.es");
		userAlterQ.setBalance("10");

		dao.create(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);

		log.debug(userAlterQ.getPwd());
		return;
	}
	@Test
	public void testUpdate() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		userAlterQ.setName("Primera-");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setId("idmail@arroba.es");
		userAlterQ.setBalance("11");
		
		dao.save(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);
		
		log.debug(userAlterQ.getPwd());
		return;
	}
	@Test
	public void testFindById() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");

		Assert.assertEquals("idmail@arroba.es", userAlterQ.getId());
		Assert.assertEquals("2125552121", userAlterQ.getPhoneNumber());
		return;
	}
	@Test
	public void testValidateLogin() {
		UserAlterQ userAlterQ = dao.validateLogin("idmail@arroba.es","password");
		Assert.assertEquals("idmail@arroba.es", userAlterQ.getId());
		

		
	}


}
