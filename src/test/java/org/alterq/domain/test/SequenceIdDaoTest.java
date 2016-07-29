package org.alterq.domain.test;

import org.alterq.domain.SequenceId;
import org.alterq.repo.SequenceIdDao;
import org.alterq.repo.impl.SequenceIdDaoImpl;
import org.alterq.util.enumeration.SequenceNameEnum;
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
	public void test00CreateCollection() throws Exception {
		((SequenceIdDaoImpl) dao).createCollection();
		log.debug("CreateCollection:");
		return;
	}

	@Test
	public void test01Create() throws Exception {
		SequenceId bean = dao.findById(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
		if (null == bean) {
			bean = new SequenceId();
			bean.setId(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
			bean.setSequence("0");
			dao.add(bean);
			log.debug("Create:" + bean.getId());
		}
		Assert.assertNotNull(bean.getId());
		return;
	}

	@Test
	public void test02IncreaseSequenceCompany() throws Exception {
		SequenceId bean = dao.findById(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
		Assert.assertNotNull(bean.getId());
		int seq = dao.getNextSequenceId(SequenceNameEnum.SEQUENCE_COMPANY.getValue());
		log.debug("ReadUpdate:id:" + bean.getId());
		log.debug("ReadUpdate:sequence:" + bean.getSequence());
		log.debug("ReadUpdate:sequence:" + seq);
		return;
	}

}
