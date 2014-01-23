package org.alterq.domain.test;

import junit.framework.Assert;

import org.alterq.domain.GeneralData;
import org.alterq.repo.GeneralDataDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class GeneralDataDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GeneralDataDao dao;

	@Test
	public void testCreate() throws Exception {
		GeneralData bean = new GeneralData();
		bean.setActive(true);
		bean.setDobles(2);
		bean.setRound(1);
		bean.setSeason(5);
		bean.setTriples(3);
		dao.add(bean);
		Assert.assertNotNull(bean.getRound());

		log.debug(""+bean.getRound());
		return;
	}
}
