package org.alterq.domain.test;

import java.util.List;

import org.alterq.domain.UserAlterQ;
import org.alterq.repo.UserAlterQDao;
import org.alterq.security.RolCompanySecurity;
import org.alterq.util.BetTools;
import org.alterq.util.NumericUtil;
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
public class UserAlterQDaoSearchTest {
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
	public void AA_testSearch() throws Exception {
		log.debug("*************************testSearch");
		List<UserAlterQ> allUser = dao.findAllOrderedByName();
		for (UserAlterQ userAlterQ : allUser) {
			log.debug(userAlterQ.getId());
		}
	}
	@Test
	public void AA_testSearchActive() throws Exception {
		log.debug("*************************testSearchActive");
		List<UserAlterQ> allUser = dao.findAllUserActive();
		float prizeBet=0.75f;
		for (UserAlterQ userAlterQ : allUser) {
			//you have one bet minim
			int numBets=(userAlterQ.getSpecialBets()==null)?0:userAlterQ.getSpecialBets().size();
			log.debug(userAlterQ.getId()+":bets:"+numBets+":balance:"+userAlterQ.getBalance());
			float totalAmount=prizeBet*numBets;
			Double result=Double.parseDouble(userAlterQ.getBalance())-Float.valueOf(totalAmount);
			if (result.doubleValue()<0 || (Double.parseDouble(userAlterQ.getBalance())-prizeBet<0) ){
				log.debug("you don't have enough money");
			}
		}
	}

}
