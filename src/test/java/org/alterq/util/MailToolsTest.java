package org.alterq.util;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.alterq.util.MailTools;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailToolsTest {
	
	@Autowired
	MailTools mailTools;
	
	@Test
	public void test01getCCOFinalBet() {
		ArrayList<String> result = new ArrayList<String>();
		result=mailTools.getCCOFinalBet(1,2015,8);

		Assert.assertNotNull(result);
	}

	@Test
	public void test02getCCOUsersWithoutBet() {
		ArrayList<String> result = new ArrayList<String>();
		result=mailTools.getCCOUsersWithoutBet(1,2015,8);

		Assert.assertNotNull(result);
	}

	@Test
	public void test03getCCOUsersWithoutMoney() {
		ArrayList<String> result = new ArrayList<String>();
		result=mailTools.getCCOUsersWithoutMoney();

		Assert.assertNotNull(result);
	}
	
	@Test
	public void test04sendWithoutMoneyMail() {
		mailTools.sendMailUsersWithoutMoney();
		Assert.assertNotNull(true);
	}
	
}
