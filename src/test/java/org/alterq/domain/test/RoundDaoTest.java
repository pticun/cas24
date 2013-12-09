package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.Game;
import org.alterq.domain.Round;
import org.alterq.repo.RoundDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
public class RoundDaoTest {
	
	private String[] games1 = {"GETAFE","VALENCIA","AT. MADRID","ATHLETIC CLUB","LEVANTE", "GRANADA","SEVILLA","CELTA","RAYO VALLECANO","R. MADRID","R. SOCIEDAD","OSASUNA","ALMERÍA","VALLADOLID","MALLORCA"};
	private String[] games2 = {"LUGO","PONFERRADINA","RECREATIVO","EIBAR","ZARAGOZA","JAEN","MIRANDÉS","GIRONA","HÉRCULES","LAS PALMAS","ALCORCÓN","CÓRDOBA","SPORTING","MÁLAGA","BETIS"};

	@Autowired
	private RoundDao roundDao;

	@Test
	public void testFindLastJornada() {
		Round bean = roundDao.findLastJornada();
		
		System.out.println(bean.getRound()+"-"+bean.getSeason());


		return;
	}
	@Test
	public void testFindByTemporadaJornada() {
		Round bean = roundDao.findByTemporadaJornada(2013, 9);
		
		Assert.assertEquals(2013, bean.getSeason());
		
		return;
	}

	@Test
	public void testCreateRound() {
		Round bean =new Round();
		bean.setRound(9);
		bean.setSeason(2013);
		
		List<Game> matchs=new ArrayList<Game>();
		for (int i = 0; i < 15; i++) {
			Game game=new Game();
			game.setPlayer1(games1[i]);
			game.setPlayer2(games2[i]);
			game.setId(i+1);
			matchs.add(game);
		}
		
		bean.setGames(matchs);
		
		roundDao.addRound(bean);
		
		return;
	}

}
