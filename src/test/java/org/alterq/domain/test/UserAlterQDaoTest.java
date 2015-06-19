package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.RolNameEnum;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.UserAlterQDao;
import org.apache.commons.lang3.time.DateFormatUtils;
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
public class UserAlterQDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;

	@Test
	public void AA_testCreate() throws Exception {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setName("Primera");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setPwd("password");
		userAlterQ.setId("idmail@arroba.es");
		userAlterQ.setBalance("10");
		userAlterQ.setActive(true);
		userAlterQ.setCompany(AlterQConstants.COMPANY);
		userAlterQ.setDateCreated(new Date());
		
		RolCompany rc=new RolCompany();
		rc.setCompany(AlterQConstants.COMPANY);
		rc.setRol(RolNameEnum.ROL_USER.getValue());
		
		ArrayList<RolCompany> rcL=new ArrayList<RolCompany>();
		rcL.add(rc);
		
		userAlterQ.setRols(rcL);
		dao.create(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);

		log.debug(userAlterQ.getPwd());
		return;
	}
	@Test
	public void AB_testUpdate() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		userAlterQ.setName("Primera-");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setId("idmail@arroba.es");
		userAlterQ.setBalance("11");
		userAlterQ.setDateCreated(new Date());
		
		dao.save(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);
		
		log.debug(userAlterQ.getPwd());
		return;
	}
	@Test
	public void AC_testFindById() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");

		Assert.assertEquals("idmail@arroba.es", userAlterQ.getId());
		Assert.assertEquals("2125552121", userAlterQ.getPhoneNumber());
		log.debug("DATECREATED==============="+DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(userAlterQ.getDateCreated()));
		return;
	}
	@Test
	public void AD_testIsUserInRolForCompany() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		RolCompany rc=new RolCompany();
		rc.setCompany(AlterQConstants.COMPANY);
		rc.setRol(RolNameEnum.ROL_USER.getValue());
		Assert.assertTrue(dao.isUserRolForCompany(userAlterQ, rc));
		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
		Assert.assertFalse(dao.isUserRolForCompany(userAlterQ, rc));
		log.debug( userAlterQ.getId());
	}
	@Test
	public void AD_testRolForCompany() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		RolCompany rc=new RolCompany();
		rc.setCompany(AlterQConstants.COMPANY);
		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
		//Add RolAdmin
		dao.addRolForCompany(userAlterQ, rc);
		userAlterQ = dao.findById("idmail@arroba.es");
		log.debug("addRolAdmin");
		List<RolCompany> rcL= userAlterQ.getRols();
		for (RolCompany rolCompany : rcL) {
			log.debug("company:rol="+rolCompany.getCompany()+"-"+rolCompany.getRol());
		} 
		Assert.assertTrue(rcL.contains(rc));
		
		//Check if user with RolAdmin authorized for execute action with RolUserAdvanced
		RolCompany rcAdvanced=new RolCompany();
		rcAdvanced.setCompany(AlterQConstants.COMPANY);
		rcAdvanced.setRol(RolNameEnum.ROL_USERADVANCED.getValue());
		Assert.assertTrue(dao.isUserAuthorizedRolForCompany(userAlterQ, rcAdvanced));
		dao.deleteRolForCompany(userAlterQ, rc);
		userAlterQ = dao.findById("idmail@arroba.es");
		log.debug("deleteRolAdmin");
		rcL= userAlterQ.getRols();
		for (RolCompany rolCompany : rcL) {
			log.debug("company:rol="+rolCompany.getCompany()+"-"+rolCompany.getRol());
		} 
		Assert.assertFalse(rcL.contains(rc));
		//Check if user with RolAdmin authorized for execute action with RolUserAdvanced
		rcAdvanced.setCompany(AlterQConstants.COMPANY);
		rcAdvanced.setRol(RolNameEnum.ROL_USERADVANCED.getValue());
		Assert.assertFalse(dao.isUserAuthorizedRolForCompany(userAlterQ, rcAdvanced));

		log.debug( userAlterQ.getId());
	}
	@Test
	public void AD_testUserRol() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		List<RolCompany> rcL= userAlterQ.getRols();
		for (RolCompany rolCompany : rcL) {
			log.debug("company:rol="+rolCompany.getCompany()+"-"+rolCompany.getRol());
		} 
		log.debug( userAlterQ.getId());
	}
	@Test
	public void AD_testUserAdmin() {
		UserAlterQ userAlterQ = dao.findAdminByCompany(AlterQConstants.COMPANY);
		log.debug( userAlterQ.getId());
	}
	@Test
	public void AD_testValidateLogin() {
		UserAlterQ userAlterQ = dao.validateLogin("idmail@arroba.es","password");
		Assert.assertEquals("idmail@arroba.es", userAlterQ.getId());
	}
	
	@Test
	public void AD_testRemoveUser() throws Exception {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		dao.remove(userAlterQ);
	}

	
	


}
