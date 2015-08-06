package org.alterq.domain.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.apache.commons.io.FileUtils;
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
public class PopulateMIgrateUser {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;

	@Test
	public void AA_testCreate() throws Exception {
//		UserAlterQ userAlterQ = new UserAlterQ();
//		userAlterQ.setName("Primera");
//		userAlterQ.setPhoneNumber("2125552121");
//		userAlterQ.setPwd("password");
//		userAlterQ.setId("idmail@arroba.es");
//		userAlterQ.setBalance("10");
//		userAlterQ.setActive(true);
//		userAlterQ.setCompany(AlterQConstants.DEFECT_COMPANY);
//		userAlterQ.setDateCreated(new Date());
//
//		RolCompany rc = new RolCompany();
//		rc.setCompany(AlterQConstants.DEFECT_COMPANY);
//		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
//
//		ArrayList<RolCompany> rcL = new ArrayList<RolCompany>();
//		rcL.add(rc);
//
//		userAlterQ.setRols(rcL);
//		dao.create(userAlterQ);
//		String id = userAlterQ.getId();
//		Assert.assertNotNull(id);
//
//		log.debug(userAlterQ.getPwd());
		return;
	}

	@Test
	public void readCSVFile() throws IOException{
		List<String> lines=FileUtils.readLines(new File("/home/kotto/git/quinimobile/src/test/resources/PERSONAS.csv"));
		for (String allLine : lines) {
			System.out.println(allLine);
			StringTokenizer st=new StringTokenizer(allLine, ";");
			while (st.hasMoreElements()) {
				String object = (String) st.nextElement();
				System.out.println(object);
			}
		}
	}
	
}
