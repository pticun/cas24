package org.alterq.domain.test;

import org.alterq.domain.SequenceId;
import org.alterq.repo.SequenceIdDao;
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
public class SequenceIdDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SequenceIdDao dao;

	@Test
	public void test01Create() throws Exception {
		SequenceId bean = new SequenceId();
		bean.setId("sequenceCompany");
		dao.add(bean);
		Assert.assertNotNull(bean.getId());
		log.debug("Create:" + bean.getId());
		return;
	}

	public void test02IncreaseSequenceCompany() throws Exception {
		SequenceId bean = dao.findById("sequenceCompany");
		Assert.assertNotNull(bean.getId());
		dao.getNextSequenceId("sequenceCompany");
		log.debug("ReadUpdate:id:" + bean.getId());
		return;
	}

}
