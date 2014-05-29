package org.alterq.domain.test;


import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.repo.RoundBetDao;
import org.bson.types.ObjectId;
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
public class RoundBetDaoTest {
	@Autowired
	private RoundBetDao betDao;

	@Test
	public void test01UserBets() {
		betDao.deleteAllBets(2013, 9);

		Bet bet = new Bet();
		bet.setBet("111111111111111");
		bet.setUser("john.smith@mailinator.com");
		betDao.addBet(2013, 9, bet);

		Bet bet2 = new Bet();
		bet2.setBet("222222222222222");
		bet2.setUser("pepe.lopez@mailinator.com");
		betDao.addBet(2013, 9, bet2);

		Bet bet3 = new Bet();
		bet3.setBet("222222222222222");
		bet3.setUser("john.smith@mailinator.com");
		betDao.addBet(2013, 9, bet3);

		RoundBets roundBets = betDao.findAllUserBets(2013, 9,"john.smith@mailinator.com");
		
		Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(0).getUser());
		Assert.assertEquals("111111111111111", roundBets.getBets().get(0).getBet());
		Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(1).getUser());
		Assert.assertEquals("222222222222222", roundBets.getBets().get(1).getBet());
		
	}
	@Test
	public void test02AddBet() {

		betDao.deleteAllBets(2013, 9);

		Bet bet = new Bet();
		bet.setBet("111111111111111");
		bet.setUser("john.smith@mailinator.com");

		betDao.addBet(2013, 9, bet);

		Bet bet2 = new Bet();
		bet2.setBet("222222222222222");
		bet2.setUser("pepe.lopez@mailinator.com");

		betDao.addBet(2013, 9, bet2);

		RoundBets roundBets = betDao.findAllBets(2013, 9);

		Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(0).getUser());
		Assert.assertEquals("111111111111111", roundBets.getBets().get(0).getBet());
		Assert.assertEquals("pepe.lopez@mailinator.com", roundBets.getBets().get(1).getUser());
		Assert.assertEquals("222222222222222", roundBets.getBets().get(1).getBet());

		return;
	}

	@Test
	public void test03findAllBets() {

		RoundBets roundBets = betDao.findAllBets(2013, 9);

		System.out.println("ROUND:" + roundBets.getRound());
		System.out.println("SEASON:" + roundBets.getSeason());
		System.out.println("USER 0:" + roundBets.getBets().get(0).getUser());
		System.out.println("BET  0:" + roundBets.getBets().get(0).getBet());

		Assert.assertEquals(9, roundBets.getRound());
		Assert.assertEquals(2013, roundBets.getSeason());
		Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(0).getUser());
		Assert.assertEquals("111111111111111", roundBets.getBets().get(0).getBet());

		return;
	}

	@Test
	public void test04DeleteUserBets() {

		betDao.deleteAllBets(2013, 9);

		Bet bet = new Bet();
		bet.setBet("111111111111111");
		bet.setUser("john.smith@mailinator.com");

		betDao.addBet(2013, 9, bet);

		Bet bet2 = new Bet();
		bet2.setBet("222222222222222");
		bet2.setUser("pepe.lopez@mailinator.com");

		betDao.addBet(2013, 9, bet2);

//		betDao.deleteAllUserBets(2013, 9, "john.smith@mailinator.com");

		RoundBets roundBets = betDao.findAllBets(2013, 9);

		Assert.assertEquals("pepe.lopez@mailinator.com", roundBets.getBets().get(0).getUser());
		Assert.assertEquals("222222222222222", roundBets.getBets().get(0).getBet());

		return;
	}

	@Test
	public void test05DeleteUserBet() {

		betDao.deleteAllBets(2013, 9);

		Bet bet = new Bet();
		bet.setBet("111111111111111");
		bet.setUser("john.smith@mailinator.com");

		betDao.addBet(2013, 9, bet);

		Bet bet2 = new Bet();
		bet2.setBet("222222222222222");
		bet2.setUser("pepe.lopez@mailinator.com");

		betDao.addBet(2013, 9, bet2);

		betDao.deleteUserBet(2013, 9, bet);

		RoundBets roundBets = betDao.findAllBets(2013, 9);

		Assert.assertEquals("pepe.lopez@mailinator.com", roundBets.getBets().get(0).getUser());
		Assert.assertEquals("222222222222222", roundBets.getBets().get(0).getBet());

		return;
	}
	
	@Test
	public void test06DeleteAminBets() {

		Bet bet = new Bet();
		bet.setId(new ObjectId().toStringMongod());
		bet.setBet("111111111111111");
		bet.setUser("dadsh@madddator.com");

		betDao.addBet(2014, 12, bet);
		betDao.deleteAllUserBets(2014, 12, "dadsh@madddator.com");

		return;
	}

}
