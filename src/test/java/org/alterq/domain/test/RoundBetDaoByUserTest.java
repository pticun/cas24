package org.alterq.domain.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.domain.UserAlterQ;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.UserAlterQDao;
import org.alterq.repo.impl.RoundBetDaoImpl;
import org.alterq.util.BetTools;
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
public class RoundBetDaoByUserTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoundBetDao betDao;
	@Autowired
	private BetTools betTools;
	@Autowired
	private UserAlterQDao userAlterQDao;

//	@Test
	public void test01FindRoundBet() {
		RoundBets roundBets = betDao.findRoundBet(2015, 1, 1);
		List<Bet> lBets= roundBets.getBets();
//		Assert.assertNull(lBets.size());
	}
	
//	@Test
	public void test02FindRoundBetWithBets() {
		RoundBets roundBets = betDao.findRoundBetWithBets(2015, 1, 1);
		
		List<Bet> lBets = roundBets.getBets();

		Collections.sort(lBets, new Comparator<Bet>() {
			@Override
			public int compare(Bet p1, Bet p2) {
				// Aqui esta el truco, ahora comparamos p2 con p1 y no al
				// reves como antes
				return p2.getUser().compareTo(p1.getUser());
			}
		});

		// Translate result from "1X2" to "421"
		String resultBet = betTools.translateResult1x2("444444444444444");

		for (Bet bet : lBets) {
			String apu = bet.getBet();
			String user = bet.getUser();

			System.out.println("======");
			System.out.println("ROUND:" + roundBets.getRound());
			System.out.println("SEASON:" + roundBets.getSeason());
			System.out.println("USER:" + user);
			System.out.println("BET:" + apu);
			System.out.println("======");
		}
	}
	@Test
	public void test03findAllUserBets() {
		System.out.println("********test03findAllUserBets");
		String idUser = "racsor@gmail.com";
		
		RoundBets roundBets = betDao.findAllUserBets(2015, 1, idUser, -1);
		
		if (roundBets!=null){
			List<Bet> bets=roundBets.getBets();
			System.out.println("ROUND:" + roundBets.getRound());
			System.out.println("SEASON:" + roundBets.getSeason());
			System.out.println("bets size():" + roundBets.getBets().size());
			for (Iterator iterator = bets.iterator(); iterator.hasNext();) {
				Bet bet = (Bet) iterator.next();
				System.out.println(bet.getUser()+"-"+bet.getBet()+"-"+bet.getCompany());
			}
		}
		
		
//		System.out.println("USER 0:" + roundBets.getBets().get(0).getUser());
//		System.out.println("BET  0:" + roundBets.getBets().get(0).getBet());
		
		return;
	}

}
