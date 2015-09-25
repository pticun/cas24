package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.dto.AlterQConstants;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.enumeration.BetTypeEnum;
import org.alterq.util.enumeration.RolNameEnum;
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
public class SpecialBetsUserAlterQDaoTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAlterQDao dao;
	@Autowired
	private RolCompanySecurity rolCompanySecurity;

	int numberUsers = 2;
	int company = 10;

	@Test
	public void AA_testCreate() throws Exception {

		for (int i = 0; i < numberUsers; i++) {
			createUsersWithSpecialBets(i);
		}
	}

	@Test
	public void AB_findUserwithAutomaticBets() {
		List<UserAlterQ> userAlterQ = dao.findUserWithTypeSpecialBets(company, BetTypeEnum.BET_AUTOMATIC);
		for (UserAlterQ userAlterQ2 : userAlterQ) {
			log.debug("useralterQ:" + userAlterQ2.getId()+" Automatic:"+userAlterQ2.getSpecialBets().get(0).getNumBets());
		}

		return;
	}
	@Test
	public void AB_findUserwithFixedBets() {
		List<UserAlterQ> userAlterQ = dao.findUserWithTypeSpecialBets(company, BetTypeEnum.BET_FIXED);
		for (UserAlterQ userAlterQ2 : userAlterQ) {
			log.debug("useralterQ:" + userAlterQ2.getId());
			List<Bet> specialBets=userAlterQ2.getSpecialBets();
			for (Bet bet : specialBets) {
				log.debug("bet:"+bet.getBet());
			}
			log.debug("======");
		}
		
		return;
	}

	public void createUsersWithSpecialBets(int numberUser) throws Exception {
		UserAlterQ userAlterQ = new UserAlterQ();
		userAlterQ.setName("Primera");
		userAlterQ.setPhoneNumber("2125552121");
		userAlterQ.setPwd("password");
		userAlterQ.setId("specialBets" + numberUser + "@arroba.es");
		userAlterQ.setBalance("10");
		userAlterQ.setActive(true);
		userAlterQ.setDateCreated(new Date());

		RolCompany rc = new RolCompany();
		rc.setCompany(AlterQConstants.DEFECT_COMPANY);
		rc.setRol(RolNameEnum.ROL_ADMIN.getValue());
		RolCompany rc2=new RolCompany();
		rc2.setCompany(company);
		rc2.setRol(RolNameEnum.ROL_USER.getValue());

		ArrayList<RolCompany> rcL = new ArrayList<RolCompany>();
		rcL.add(rc);

		userAlterQ.setRols(rcL);

		double numBetAutomatic = 5;
		Bet betAutomatic = new Bet();
		betAutomatic.setType(BetTypeEnum.BET_AUTOMATIC.getValue());
		betAutomatic.setNumBets(numBetAutomatic);
		betAutomatic.setCompany(company);
		Bet betFixed = new Bet();
		betFixed.setType(BetTypeEnum.BET_FIXED.getValue());
		betFixed.setBet("11111111111111100");
		betFixed.setCompany(company);

		ArrayList<Bet> specialBets = new ArrayList<Bet>();
		specialBets.add(betAutomatic);
		specialBets.add(betFixed);
		specialBets.add(betFixed);

		userAlterQ.setSpecialBets(specialBets);

		dao.create(userAlterQ);

	}

	public void deleteUserWithSpecialBets(int numberUser) throws Exception {
		UserAlterQ userAlterQ = dao.findById("specialBets" + numberUser + "@arroba.es");
		dao.remove(userAlterQ);
	}

	@Test
	public void AZ_testRemoveUser() throws Exception {
		for (int i = 0; i < numberUsers; i++) {
			deleteUserWithSpecialBets(i);
		}
	}

}
