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

   /* @Test
    public void testAddBet()
    {
        Bet bet = new Bet();
        bet.setBet("111111111111111");
        bet.setUser("john.smith@mailinator.com");

        boolean rdo = betDao.addBet(2013, 9, bet);
        
        Bet bet2 = new Bet();
        bet.setBet("222222222222222");
        bet.setUser("pepe.lopez@mailinator.com");

        boolean rdo2 = betDao.addBet(2013, 9, bet2);

        System.out.println("addBet:"+rdo2);
        
        RoundBets roundBets = betDao.findAllBets(2013, 9);
        Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(0).getUser());
        Assert.assertEquals("111111111111111", roundBets.getBets().get(0).getBet());

        return;
    }
*/
    @Test
    public void testfindAllBets()
    {
    	
        RoundBets roundBets = betDao.findAllBets(2013, 9);

        Assert.assertEquals(9, roundBets.getRound());
        Assert.assertEquals(2013, roundBets.getSeason());
        Assert.assertEquals("john.smith@mailinator.com", roundBets.getBets().get(0).getUser());
        Assert.assertEquals("111111111111111", roundBets.getBets().get(0).getBet());
        
        return;
    }

}
