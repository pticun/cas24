package org.alterq.domain.test;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import org.alterq.domain.Account;
import org.alterq.domain.Bet;
import org.alterq.domain.Company;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.AccountingDao;
import org.alterq.repo.impl.AccountingDaoImpl;
import org.alterq.util.enumeration.AccountTypeEnum;
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
public class AccountingDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountingDao dao;

	@Test
	public void test00CreateCollection() throws Exception {
		((AccountingDaoImpl) dao).createCollection();
		log.debug("CreateCollection:");
		return;
	}

	@Test
	public void test01Create() throws Exception {
		Account bean = new Account();
		bean.setCompany(AlterQConstants.DEFECT_COMPANY);
		bean.setRound(1);
		bean.setSeason(2016);
		bean.setUser("quinigold@gmail.com");
		bean.setAmount("0.75");
		bean.setDescription("Apuesta");
		bean.setType(AccountTypeEnum.ACCOUNT_BET.getValue());
		bean.setDate(new Date());
		
		dao.add(bean);
		Assert.assertNotNull(bean.getDescription());
		log.debug("Create:" + bean.getCompany() + "-" + bean.getRound() + "-" + bean.getSeason() + "-" + bean.getUser() + "-" + bean.getDescription() + "-" + bean.getType() + "-" + bean.getDate() );
		return;
	}

	@Test
	public void test02Read() throws Exception {
		java.util.List<Account> bean = dao.findAccounts(2016);
		Assert.assertEquals(bean.get(0).getSeason(), 2016);
		return;
	}
	
	@Test
	public void test03Delete() throws Exception {
		boolean isOk = dao.deleteAccounts(2016);
		Assert.assertTrue(isOk);
		return;
	}
}
