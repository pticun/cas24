package org.alterq.domain.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.alterq.domain.Bet;
import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.BetTools;
import org.alterq.util.NumericUtil;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.RolNameEnum;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.types.ObjectId;
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
public class UserAlterQDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;
	@Autowired
	private BetTools betTools;
	@Autowired
	private NumericUtil numericUtil;

	@Test
	public void AA_testCreate() throws Exception {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setName("Primera");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setPwd("password");
		userAlterQ.setId("idmail@arroba.es");
		userAlterQ.setBalance(Double.toString(Double.parseDouble("1.4563356")));
		userAlterQ.setActive(true);
		userAlterQ.setDateCreated(new Date());

		RolCompany rc = new RolCompany();
		rc.setCompany(AlterQConstants.DEFECT_COMPANY);
		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());

		ArrayList<RolCompany> rcL = new ArrayList<RolCompany>();
		rcL.add(rc);

		userAlterQ.setRols(rcL);
		
		Bet bet = new Bet();
		bet.setId(new ObjectId().toHexString());
		bet.setCompany(1);
		bet.setType(BetTypeEnum.BET_AUTOMATIC.getValue());
		bet.setPrice(0);
		bet.setNumBets(0);
		bet.setDateCreated(new Date());
		bet.setDateUpdated(new Date());
		bet.setTypeReduction(0);
		
		ArrayList<Bet> betL = new ArrayList<Bet>();
		betL.add(bet);
		
		userAlterQ.setSpecialBets(betL);
		
		
		dao.create(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);
		
		

		log.debug(userAlterQ.getPwd());
		return;
	}

	@Test
	public void AB_testUpdate() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		userAlterQ.setName("Primera-");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setId("idmail@arroba.es");
		double price = new Double(betTools.getPriceBet() * 3.4).doubleValue();
		
		BigDecimal bgPriceBet=new BigDecimal(betTools.getPriceBet() * 3.4);
		BigDecimal balance=new BigDecimal(userAlterQ.getBalance());
		balance=balance.subtract(bgPriceBet);
		
		
		
		

//		double balance = new Double(userAlterQ.getBalance()).doubleValue();
//		balance -= price;
		userAlterQ.setBalance(balance.toPlainString());
		
		String balanceIncrease="3.2532141";
		balance=balance.add(new BigDecimal(balanceIncrease));
		
		userAlterQ.setBalance(balance.toPlainString());
		
		System.out.println("userAlterqBalanceGetBalance:"+userAlterQ.getBalance());
		System.out.println("userAlterqBalanceFormat:"+numericUtil.getTwoDecimalFormat(userAlterQ.getBalance()));

		userAlterQ.setDateCreated(new Date());

		dao.save(userAlterQ);
		String id = userAlterQ.getId();
		Assert.assertNotNull(id);

		log.debug(userAlterQ.getPwd());
		return;
	}

	@Test
	public void AC_testFindById() {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");

		Assert.assertEquals("idmail@arroba.es", userAlterQ.getId());
		Assert.assertEquals("2125552121", userAlterQ.getPhoneNumber());
		log.debug("DATECREATED===============" + DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(userAlterQ.getDateCreated()));
		return;
	}

	@Test
	public void AC_testFindByIdIgnoreCase() {
		UserAlterQ userAlterQ = dao.findByIdIgnoreCase("IdMail@arroba.es");
		
		Assert.assertEquals("2125552121", userAlterQ.getPhoneNumber());
		log.debug("AC_testFindByIdIgnoreCase");
		return;
	}
	
	@Test
	public void AD_testUserAdmin() {
		UserAlterQ userAlterQ = dao.findAdminByCompany(AlterQConstants.DEFECT_COMPANY);
		log.debug("adminbycompany:"+userAlterQ.getId());
		userAlterQ = dao.findSuperAdmin();
		log.debug("Superadminbycompany:"+userAlterQ.getId());
	}

	@Test
	public void AD_testValidateLogin() {
		UserAlterQ userAlterQ = dao.validateLogin("idmail@arroba.es", "password");
		Assert.assertEquals("idmail@arroba.es", userAlterQ.getId());
	}
	
	@Test
	public void AU_testUpdateUserCompanyAutomaticBets() throws Exception {
		dao.updateCompanyAutomaticBet("idmail@arroba.es", 1, 3);
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		Assert.assertEquals(((Bet)userAlterQ.getSpecialBets().get(0)).getNumBets(), 3);
	}

	@Test
	public void AZ_testRemoveUser() throws Exception {
		UserAlterQ userAlterQ = dao.findById("idmail@arroba.es");
		dao.remove(userAlterQ);
	}

}
