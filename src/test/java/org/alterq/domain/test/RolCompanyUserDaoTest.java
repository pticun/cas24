package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.exception.SecurityException;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.enumeration.RolNameEnum;
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
public class RolCompanyUserDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;

	@Test
	public void AA_testCreate() throws Exception {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setName("Primera");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setPwd("password");
		userAlterQ.setId("idmail@arroba.es");
		userAlterQ.setBalance("10");
		userAlterQ.setActive(true);
		userAlterQ.setDateCreated(new Date());

		RolCompany rc = new RolCompany();
		rc.setCompany(AlterQConstants.DEFECT_COMPANY);
		rc.setRol(RolNameEnum.ROL_USER.getValue());

		ArrayList<RolCompany> rcL = new ArrayList<RolCompany>();
		rcL.add(rc);

		userAlterQ.setRols(rcL);
		dao.create(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);

		log.debug(userAlterQ.getPwd());
		return;
	}

	@Test
	public void AD_testIsUserInRolForCompany() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		RolCompany rc = new RolCompany();
		rc.setCompany(AlterQConstants.DEFECT_COMPANY);
		rc.setRol(RolNameEnum.ROL_USER.getValue());
		Assert.assertTrue(rolCompanySecurity.isUserRolForCompany(userAlterQ, rc));
		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
		Assert.assertFalse(rolCompanySecurity.isUserRolForCompany(userAlterQ, rc));
		log.debug(userAlterQ.getId());
	}

	@Test
	public void AD_testRolForCompany() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		RolCompany rc = new RolCompany();
		rc.setCompany(AlterQConstants.DEFECT_COMPANY);
		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
		// Add RolAdmin
		dao.addRolForCompany(userAlterQ, rc);
		userAlterQ = dao.findById("idmail@arroba.es");
		log.debug("addRolAdmin");
		List<RolCompany> rcL = userAlterQ.getRols();
		for (RolCompany rolCompany : rcL) {
			log.debug("company:rol=" + rolCompany.getCompany() + "-" + rolCompany.getRol());
		}
		Assert.assertTrue(rcL.contains(rc));

		// Check if user with RolAdmin authorized for execute action with
		// RolUserAdvanced
		RolCompany rcAdvanced = new RolCompany();
		rcAdvanced.setCompany(AlterQConstants.DEFECT_COMPANY);
		rcAdvanced.setRol(RolNameEnum.ROL_USERADVANCED.getValue());
		try {
			Assert.assertTrue(rolCompanySecurity.isUserAuthorizedRolForCompany(userAlterQ, rcAdvanced));
			dao.deleteRolForCompany(userAlterQ, rc);
			userAlterQ = dao.findById("idmail@arroba.es");
			log.debug("deleteRolAdmin");
			rcL = userAlterQ.getRols();
			for (RolCompany rolCompany : rcL) {
				log.debug("company:rol=" + rolCompany.getCompany() + "-" + rolCompany.getRol());
			}
			Assert.assertFalse(rcL.contains(rc));
			// Check if user with RolAdmin authorized for execute action with
			// RolUserAdvanced
			rcAdvanced.setCompany(AlterQConstants.DEFECT_COMPANY);
			rcAdvanced.setRol(RolNameEnum.ROL_USERADVANCED.getValue());
			Assert.assertFalse(rolCompanySecurity.isUserAuthorizedRolForCompany(userAlterQ, rcAdvanced));

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.debug(userAlterQ.getId());
	}

	@Test
	public void AD_testUserRol() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		List<RolCompany> rcL = userAlterQ.getRols();
		for (RolCompany rolCompany : rcL) {
			log.debug("company:rol=" + rolCompany.getCompany() + "-" + rolCompany.getRol());
		}
		log.debug(userAlterQ.getId());
	}

	@Test
	public void AZ_testRemoveUser() throws Exception {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		dao.remove(userAlterQ);
	}

}
