package org.alterq.domain.test;

import org.alterq.domain.GeneralData;
import org.alterq.repo.GeneralDataDao;
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
public class GeneralDataDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GeneralDataDao dao;

	@Test
	public void test01Create() throws Exception {
		GeneralData bean = new GeneralData();
		bean.setCompany(1);
		bean.setActive(true);
		bean.setType(1);
		bean.setNick("Nich");
		bean.setDescription("description");
		dao.add(bean);
//		Assert.assertNotNull(bean.getRound());
//		log.debug("Create:" + bean.getRound());
		return;
	}

	@Test
	public void test02ReadUpdate() throws Exception {
		GeneralData bean = dao.findByCompany(1);
//		Assert.assertNotNull(bean.getRound());
//		bean.setRound(bean.getRound() + 1);
		dao.update(bean);
//		log.debug("ReadUpdate:round:" + bean.getRound());
		log.debug("ReadUpdate:id:" + bean.getId());
		return;
	}

	@Test
	public void test03Read() throws Exception {
		GeneralData bean = dao.findByCompany(1);
//		Assert.assertNotNull(bean.getRound());
		String id = bean.getId();
		GeneralData otherBean = dao.findById(id);
		Assert.assertEquals(bean.getId(), otherBean.getId());
		return;
	}

	@Test
	public void test04Delete() throws Exception {
		GeneralData bean = dao.findByCompany(1);
//		Assert.assertNotNull(bean.getRound());
//		bean.setRound(bean.getRound() + 1);
		dao.delete(bean);
		return;
	}
}
