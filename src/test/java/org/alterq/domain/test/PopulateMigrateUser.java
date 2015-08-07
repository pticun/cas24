package org.alterq.domain.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.enumeration.RolNameEnum;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PopulateMigrateUser {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private UserAlterQDao dao;
//	@Autowired
//	private RolCompanySecurity rolCompanySecurity;
//	@Autowired
//	private CompanyDao companyDao;
//	@Autowired
//	private SequenceIdDao daoSequence;

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
	public void AAreadCSVFile() throws IOException{
		File fileUsers = new File(Thread.currentThread().getContextClassLoader().getResource("PERSONAS.csv").getFile());
		
		List<UserAlterQ> userToImport=new ArrayList<UserAlterQ>();

		List<String> lines=FileUtils.readLines(fileUsers);
		for (String allLine : lines) {
			UserAlterQ user=new UserAlterQ();
			System.out.println(allLine);
			StringTokenizer st=new StringTokenizer(allLine, ";");
			user.setNick((String)st.nextElement());
			user.setPwd((String)st.nextElement());
			user.setBalance((String)st.nextElement());
			user.setName((String)st.nextElement());
			user.setSurnames((String)st.nextElement()+" "+(String)st.nextElement());
			user.setId((String)st.nextElement());
//			while (st.hasMoreElements()) {
//				
//				String object = (String) st.nextElement();
//				System.out.println(object);
//			}
			ArrayList<RolCompany> rcL = new ArrayList<RolCompany>();

			RolCompany rc = new RolCompany();
			rc.setCompany(AlterQConstants.DEFECT_COMPANY);
			rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
			rcL.add(rc);

			RolCompany rc1 = new RolCompany();
			rc1.setCompany(34);
			rc1.setRol(RolNameEnum.ROL_USER.getValue());
			rcL.add(rc1);

			user.setRols(rcL);

			System.out.println(ToStringBuilder.reflectionToString(user));
			userToImport.add(user);
		}
	}
	
	@Test
	public void AA_createCompany() {
//		SequenceId beanSeq = daoSequence.findById(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
//		int seq = daoSequence.getNextSequenceId(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
//		
//		bean=new Company();
//		bean.setCompany(seq);
//		bean.setDescription("QuiniGold2");
//		bean.setNick("QuiniGold2");
//		bean.setType(CompanyTypeEnum.COMPANY_NON_COLLABORATIVE.getValue());
//		bean.setVisibility(Boolean.TRUE);
//		companyDao.add(bean);
//		Assert.assertNotNull(bean.getDescription());
//		log.debug("Create:" + bean.getCompany() + "-" + bean.getDescription());

	}
	
}
