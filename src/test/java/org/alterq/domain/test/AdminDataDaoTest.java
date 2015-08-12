package org.alterq.domain.test;

import org.alterq.domain.AdminData;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AdminDataDao;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.impl.AdminDataDaoImpl;
import org.alterq.repo.impl.CompanyDaoImpl;
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
public class AdminDataDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdminDataDao adminDataDao;
	@Autowired
	private SequenceIdDao daoSequence;

	@Test
	public void test00CreateCollection() throws Exception {
		((AdminDataDaoImpl) adminDataDao).createCollection();
		log.debug("CreateCollection:");
		return;
	}

	@Test
	public void test01Create() throws Exception {
		AdminData bean = new AdminData();
		bean.setCompany(AlterQConstants.DEFECT_ADMINDATA);
		bean.setRound(1);
		bean.setSeason(2015);
		bean.setIdDelegacion("12345");
		bean.setIdReceptor("12");
		adminDataDao.add(bean);
		Assert.assertNotNull(bean.getIdDelegacion());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getIdDelegacion());
		return;
	}

	@Test
	public void test02ReadUpdate() throws Exception {
		AdminData bean = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Assert.assertNotNull(bean.getCompany());
		bean.setIdDelegacion("54321");
		adminDataDao.update(bean);
		log.debug("description:" + bean.getCompany() + "-" + bean.getIdDelegacion());
		log.debug("ReadUpdate:id:" + bean.getId());
		return;
	}

	@Test
	public void test03Read() throws Exception {
		AdminData bean = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Assert.assertNotNull(bean.getCompany());
		AdminData otherBean = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Assert.assertEquals(bean.getId(), otherBean.getId());
		return;
	}

	@Test
	public void test04Delete() throws Exception {
		AdminData bean = adminDataDao.findById(AlterQConstants.DEFECT_ADMINDATA);
		Assert.assertNotNull(bean.getIdDelegacion());
		adminDataDao.delete(bean);
		return;
	}
}
