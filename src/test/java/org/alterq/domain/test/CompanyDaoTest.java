package org.alterq.domain.test;

import org.alterq.domain.Company;
import org.alterq.domain.GeneralData;
import org.alterq.repo.CompanyDao;
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

	@Test
	public void test01Create() throws Exception {
		Company bean = new Company();
		bean.setDescription("description");
		bean.setNick("Nich");
		bean.setType(1);
		bean.setVisibility(Boolean.TRUE);
		dao.add(bean);
		Assert.assertNotNull(bean.getDescription());
		log.debug("Create:" + bean.getDescription());
		return;
	}

	@Test
	public void test02ReadUpdate() throws Exception {
		Company bean = dao.findByCompany(1);
		Assert.assertNotNull(bean.getDescription());
		bean.setRound(bean.getRound() + 1);
		dao.update(bean);
		log.debug("ReadUpdate:round:" + bean.getRound());
		log.debug("ReadUpdate:id:" + bean.getId());
		return;
	}

	@Test
	public void test03Read() throws Exception {
		Company bean = dao.findByCompany(1);
		Assert.assertNotNull(bean.getRound());
		String id = bean.getId();
		Company otherBean = dao.findById(id);
		Assert.assertEquals(bean.getId(), otherBean.getId());
		return;
	}

	@Test
	public void test04Delete() throws Exception {
		Company bean = dao.findByCompany(1);
		Assert.assertNotNull(bean.getRound());
		bean.setRound(bean.getRound() + 1);
		dao.delete(bean);
		return;
	}
}
