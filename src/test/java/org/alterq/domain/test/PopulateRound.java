package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.alterq.domain.Game;
import org.alterq.domain.Round;
import org.alterq.repo.RoundDao;
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
public class PopulateRound {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	int season = 2015;
	int round = 1;

	private String[] games1 = { "GETAFE", "VALENCIA", "AT. MADRID", "ATHLETIC CLUB", "LEVANTE", "GRANADA", "SEVILLA", "CELTA", "RAYO VALLECANO", "R. MADRID",
			"R. SOCIEDAD", "OSASUNA", "ALMERÍA", "VALLADOLID", "MALLORCA" };
	private String[] games2 = { "LUGO", "PONFERRADINA", "RECREATIVO", "EIBAR", "ZARAGOZA", "JAEN", "MIRANDÉS", "GIRONA", "HÉRCULES", "LAS PALMAS", "ALCORCÓN",
			"CÓRDOBA", "SPORTING", "MÁLAGA", "BETIS" };

	@Autowired
	private RoundDao roundDao;

	@Test
	public void testCreateRound() throws Exception {
		Round bean = new Round();
		bean.setDateRound(new Date());
		bean.setRound(round);
		bean.setSeason(season);
		List<Game> games = new ArrayList<Game>();

		for (int i = 0, pos = 1; i < games1.length; i++, pos++) {
			Game gameTemp = new Game();
			gameTemp.setId(pos);
			gameTemp.setPlayer1(games1[i]);
			gameTemp.setPlayer2(games2[i]);
			games.add(gameTemp);
		}

		bean.setGames(games);
		
		roundDao.addRound(bean);

		return;
	}
}
