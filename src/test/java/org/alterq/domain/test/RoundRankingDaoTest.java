package org.alterq.domain.test;


import java.awt.List;
import java.util.ArrayList;

import org.alterq.domain.Ranking;
import org.alterq.domain.RoundRanking;
import org.alterq.repo.RoundRankingDao;
import org.alterq.repo.UserAlterQDao;
import org.junit.Assert;
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
public class RoundRankingDaoTest {
	@Autowired
	private RoundRankingDao rankingDao;

	@Autowired
	private UserAlterQDao userDao;

	@Test
	public void test01DeleteRanking() {
		rankingDao.deleteRanking(1, 2014, 12);

		RoundRanking roundRanking = rankingDao.findRanking(1, 2014, 12);
		
		Assert.assertNull(roundRanking);
	}
	
	@Test
	public void test02AddRoundRanking() {
		rankingDao.deleteRanking(1, 2014, 12);

		RoundRanking bean = new RoundRanking();
		bean.setCompany(1);
		bean.setSeason(2014);
		bean.setRound(12);
		bean.setRankings(null);
		
		rankingDao.add(bean);
		
		RoundRanking roundRanking = rankingDao.findRanking(1, 2014, 12);
		
		Assert.assertEquals(2014, roundRanking.getSeason());
		Assert.assertEquals(12, roundRanking.getRound());
		Assert.assertEquals(1, roundRanking.getCompany());		
	}
	
	@Test
	public void test03AddRanking() {
		rankingDao.deleteRanking(1, 2014, 12);

		RoundRanking bean1 = new RoundRanking();
		bean1.setCompany(1);
		bean1.setSeason(2014);
		bean1.setRound(12);

		Ranking bean = new Ranking();
		bean.setCompany(1);
		bean.setPoints(10);
		bean.setOnes(5);
		bean.setEqus(2);
		bean.setTwos(3);
		bean.setUser(userDao.findById("santi"));

		ArrayList<Ranking> rankings = new ArrayList<Ranking>();
		rankings.add(bean);
		
		bean1.setRankings(rankings);
		
		rankingDao.add(bean1);
		
//		rankingDao.addRanking(1, 2014, 12, bean);
		
		RoundRanking roundRanking = rankingDao.findRanking(1, 2014, 12);
		
		Assert.assertEquals(2014, roundRanking.getSeason());
		Assert.assertEquals(12, roundRanking.getRound());
		Assert.assertEquals(1, roundRanking.getCompany());
		Assert.assertEquals(10, roundRanking.getRankings().get(0).getPoints());
	}
	
	@Test
	public void test04Add2Ranking() {
		rankingDao.deleteRanking(1, 2014, 12);

		RoundRanking bean1 = new RoundRanking();
		bean1.setCompany(1);
		bean1.setSeason(2014);
		bean1.setRound(12);
		
		rankingDao.add(bean1);
		
		Ranking bean2 = new Ranking();
		bean2.setCompany(1);
		bean2.setPoints(10);
		bean2.setOnes(5);
		bean2.setEqus(2);
		bean2.setTwos(3);
		bean2.setUser(userDao.findById("santi"));
		
		rankingDao.addRanking(1, 2014, 12, bean2);
		
		Ranking bean3 = new Ranking();
		bean3.setCompany(1);
		bean3.setPoints(12);
		bean3.setOnes(6);
		bean3.setEqus(1);
		bean3.setTwos(5);
		bean3.setUser(userDao.findById("nuevo@nuevo.es"));
		
		rankingDao.addRanking(1, 2014, 12, bean3);

		RoundRanking roundRanking = rankingDao.findRanking(1, 2014, 12);
		
		Assert.assertEquals(2014, roundRanking.getSeason());
		Assert.assertEquals(12, roundRanking.getRound());
		Assert.assertEquals(1, roundRanking.getCompany());
		Assert.assertEquals(10, roundRanking.getRankings().get(0).getPoints());
		Assert.assertEquals(12, roundRanking.getRankings().get(1).getPoints());
	}
}
