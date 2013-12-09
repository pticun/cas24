package org.alterq.domain.test;

import java.util.List;

import junit.framework.Assert;

import org.alterq.domain.Bet;
import org.alterq.domain.RoundBets;
import org.alterq.repo.BetDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class BetDaoTest
{
    @Autowired
    private BetDao betDao;

    @Test
    public void testAddBet()
    {
    	
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
    public void testfindAllBets()
    {
    	
        RoundBets roundBets = betDao.findAllBets(2013, 9);

        System.out.println("ROUND:"+roundBets.getRound());
        System.out.println("SEASON:"+ roundBets.getSeason());
        System.out.println("USER 0:"+roundBets.getBets().get(0).getUser());
        System.out.println("BET  0:"+ roundBets.getBets().get(0).getBet());

        Assert.assertEquals(9, roundBets.getRound());
        Assert.assertEquals(2013, roundBets.getSeason());
        Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(0).getUser());
        Assert.assertEquals("111111111111111", roundBets.getBets().get(0).getBet());
        
        return;
    }

    @Test
    public void testDeleteUserBets()
    {
    	
    	betDao.deleteAllBets(2013, 9);
    	
        Bet bet = new Bet();
        bet.setBet("111111111111111");
        bet.setUser("john.smith@mailinator.com");

        betDao.addBet(2013, 9, bet);
        
        Bet bet2 = new Bet();
        bet2.setBet("222222222222222");
        bet2.setUser("pepe.lopez@mailinator.com");

        betDao.addBet(2013, 9, bet2);

      
        betDao.deleteAllUserBets(2013, 9, "john.smith@mailinator.com");

        RoundBets roundBets = betDao.findAllBets(2013, 9);

        Assert.assertEquals("pepe.lopez@mailinator.com", roundBets.getBets().get(0).getUser());
        Assert.assertEquals("222222222222222", roundBets.getBets().get(0).getBet());

        return;
    }
    
}
