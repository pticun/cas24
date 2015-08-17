package org.alterq.domain.test;

import java.util.Iterator;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.repo.RoundBetDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoundBetDaoByUserTest {
	@Autowired
	private RoundBetDao betDao;

	@Test
	public void test03findAllUserBets() {
		String idUser = "scabellog@gmail.com";

		RoundBets roundBets = betDao.findAllUserBets(2015, 1, idUser, 1);
		
		List<Bet> bets=roundBets.getBets();
		for (Iterator iterator = bets.iterator(); iterator.hasNext();) {
			Bet bet = (Bet) iterator.next();
			System.out.println(bet.getUser()+"-"+bet.getBet());
		}

		System.out.println("ROUND:" + roundBets.getRound());
		System.out.println("SEASON:" + roundBets.getSeason());
		System.out.println("USER 0:" + roundBets.getBets().get(0).getUser());
		System.out.println("BET  0:" + roundBets.getBets().get(0).getBet());

		return;
	}

}
