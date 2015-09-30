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
import org.bson.types.ObjectId;
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
	int company = 1;

	public void AA_testCreate() throws Exception {

		for (int i = 0; i < numberUsers; i++) {
			createUsersWithSpecialBets(i);
		}
	}

	public void AB_testAddSpecialBet() throws Exception {

		UserAlterQ user = dao.findById("specialBets0@arroba.es");
		List<Bet> specialBet = user.getSpecialBets();
		for (Bet bet : specialBet) {
			if (BetTypeEnum.BET_AUTOMATIC.getValue() == bet.getType()) {
				bet.setNumBets(bet.getNumBets() + 1);
			}
		}
		if (specialBet != null) {
			Bet betFixed = new Bet();
			betFixed.setType(BetTypeEnum.BET_FIXED.getValue());
			betFixed.setBet("222222222222200");
			betFixed.setCompany(company);
			specialBet.add(betFixed);
		}
		dao.save(user);
	}

	@Test
	public void AM_findUserwithAutomaticBets() {
		List<UserAlterQ> userAlterQ = dao.findUserWithTypeSpecialBets(company, BetTypeEnum.BET_AUTOMATIC);
		for (UserAlterQ userAlterQ2 : userAlterQ) {
			log.debug("useralterQ:" + userAlterQ2.getId() + " Automatic:" + userAlterQ2.getSpecialBets().get(0).getNumBets());
			List<Bet> specialBet = userAlterQ2.getSpecialBets();
			for (Bet bet : specialBet) {
				if (bet.getType() == BetTypeEnum.BET_AUTOMATIC.getValue()) {
					int numApu = bet.getNumBets();
					log.debug("useralterQnumApu:" + numApu);
				}
			}
			float balance = new Float(userAlterQ2.getBalance()).floatValue();
			log.debug("useralterQbalance:" + balance);
			userAlterQ2.setBalance("123");
			// dao.updateBalance(userAlterQ2);
			dao.save(userAlterQ2);
		}

		return;
	}

	@Test
	public void AN_findUserwithFixedBets() {
		List<UserAlterQ> userAlterQ = dao.findUserWithTypeSpecialBets(company, BetTypeEnum.BET_FIXED);
		for (UserAlterQ userAlterQ2 : userAlterQ) {
			float balance = new Float(userAlterQ2.getBalance()).floatValue();
			log.debug("useralterQ:" + userAlterQ2.getId() + " useralterQbalance:" + userAlterQ2.getBalance());
			List<Bet> specialBets = userAlterQ2.getSpecialBets();
			for (Bet bet : specialBets) {
				if (bet.getType() == BetTypeEnum.BET_FIXED.getValue()) {
					log.debug("bet:" + bet.getBet());
				}
			}
			log.debug("======");
		}

		return;
	}

	 @Test
	public void AN_racsor() {
		UserAlterQ userAlterQ = dao.findById("racsor@gmail.com");
		ArrayList<Bet> specialBets = new ArrayList<Bet>();
		Bet betFixed = new Bet();
		betFixed.setId(new ObjectId().toHexString());
		betFixed.setType(BetTypeEnum.BET_FIXED.getValue());
		betFixed.setBet("4444444444444400");
		betFixed.setId(new ObjectId().toHexString());
		betFixed.setCompany(1);
		Bet betFixed2 = new Bet();
		betFixed2.setId(new ObjectId().toHexString());
		betFixed2.setType(BetTypeEnum.BET_FIXED.getValue());
		betFixed2.setBet("4444444444444400");
		betFixed2.setId(new ObjectId().toHexString());
		betFixed2.setCompany(4);
		Bet betAutomatic = new Bet();
		betAutomatic.setId(new ObjectId().toHexString());
		betAutomatic.setType(BetTypeEnum.BET_AUTOMATIC.getValue());
		betAutomatic.setNumBets(5);
		betAutomatic.setCompany(10);
		Bet betAutomatic2 = new Bet();
		betAutomatic2.setId(new ObjectId().toHexString());
		betAutomatic2.setType(BetTypeEnum.BET_AUTOMATIC.getValue());
		betAutomatic2.setNumBets(5);
		betAutomatic2.setCompany(1);
		specialBets.add(betAutomatic);
		specialBets.add(betAutomatic2);
		specialBets.add(betFixed);
		specialBets.add(betFixed2);
		userAlterQ.setSpecialBets(specialBets);
		userAlterQ.setBalance("100");
		dao.save(userAlterQ);
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
		RolCompany rc2 = new RolCompany();
		rc2.setCompany(company);
		rc2.setRol(RolNameEnum.ROL_USER.getValue());

		ArrayList<RolCompany> rcL = new ArrayList<RolCompany>();
		rcL.add(rc);

		userAlterQ.setRols(rcL);

		int numBetAutomatic = 5;
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

	public void AZ_testRemoveUser() throws Exception {
		for (int i = 0; i < numberUsers; i++) {
			deleteUserWithSpecialBets(i);
		}
	}

}
