package org.alterq.domain.test;

import org.alterq.domain.Company;
import org.alterq.repo.CompanyDao;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.impl.CompanyDaoImpl;
import org.alterq.util.enumeration.CompanyTypeEnum;
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
public class CompanyDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyDao dao;
	@Autowired
	private SequenceIdDao daoSequence;

	@Test
	public void test00CreateCollection() throws Exception {
		((CompanyDaoImpl) dao).createCollection();
		log.debug("CreateCollection:");
		return;
	}

	@Test
	public void test01Create() throws Exception {
		Company bean = new Company();
		bean.setCompany(Integer.MAX_VALUE);
		bean.setDescription("description");
		bean.setNick("Nich");
		bean.setType(CompanyTypeEnum.COMPANY_COLLABORATIVE_PUBLIC.getValue());
		bean.setVisibility(Boolean.TRUE);
		dao.add(bean);
		Assert.assertNotNull(bean.getDescription());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getDescription());
		return;
	}

	@Test
	public void test02ReadUpdate() throws Exception {
		Company bean = dao.findByCompany(Integer.MAX_VALUE);
		Assert.assertNotNull(bean.getDescription());
		bean.setDescription("description");
		dao.update(bean);
		log.debug("description:" + bean.getCompany() + "-" + bean.getDescription());
		log.debug("ReadUpdate:id:" + bean.getId());
		return;
	}

	@Test
	public void test03Read() throws Exception {
		Company bean = dao.findByCompany(Integer.MAX_VALUE);
		Assert.assertNotNull(bean.getDescription());
		String id = bean.getId();
		Company otherBean = dao.findById(id);
		Assert.assertEquals(bean.getId(), otherBean.getId());
		return;
	}

	@Test
	public void test04Delete() throws Exception {
		Company bean = dao.findByCompany(Integer.MAX_VALUE);
		Assert.assertNotNull(bean.getDescription());
		bean.setDescription(bean.getDescription() + "---");
		dao.delete(bean);
		return;
	}
}
