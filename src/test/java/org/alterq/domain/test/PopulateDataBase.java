package org.alterq.domain.test;

import java.util.ArrayList;
import java.util.List;

import org.alterq.domain.Bet;
import org.alterq.domain.Game;
import org.alterq.domain.GeneralData;
import org.alterq.domain.Round;
import org.alterq.domain.RoundBets;
import org.alterq.repo.GeneralDataDao;
import org.alterq.repo.RoundBetDao;
import org.alterq.repo.RoundDao;
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
public class PopulateDataBase {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	int season = 2014;
	int round = 12;
	int company=1;
	float price=0.5f;
	
	private String[] games1 = { "GETAFE", "VALENCIA", "AT. MADRID", "ATHLETIC CLUB", "LEVANTE", "GRANADA", "SEVILLA", "CELTA", "RAYO VALLECANO", "R. MADRID",
			"R. SOCIEDAD", "OSASUNA", "ALMERÍA", "VALLADOLID", "MALLORCA" };
	private String[] games2 = { "LUGO", "PONFERRADINA", "RECREATIVO", "EIBAR", "ZARAGOZA", "JAEN", "MIRANDÉS", "GIRONA", "HÉRCULES", "LAS PALMAS", "ALCORCÓN",
			"CÓRDOBA", "SPORTING", "MÁLAGA", "BETIS" };

	@Autowired
	private GeneralDataDao generalDao;
	@Autowired
	private RoundDao roundDao;
	@Autowired
	private RoundBetDao rondBetDao;

	@Test
	public void AA_Create_GeneralData() throws Exception {
		GeneralData bean = new GeneralData();
		bean.setCompany(company);
		bean.setActive(true);
		bean.setDobles(2);
		bean.setRound(round);
		bean.setSeason(season);
		bean.setTriples(3);
		generalDao.add(bean);
		Assert.assertNotNull(bean.getRound());
		log.debug("Create:" + bean.getRound());
		return;
	}

	@Test
	public void AB_Create_Round() {
		Round bean = new Round();
		bean.setRound(round);
		bean.setSeason(season);

		List<Game> matchs = new ArrayList<Game>();
		for (int i = 0; i < 15; i++) {
			Game game = new Game();
			game.setPlayer1(games1[i]);
			game.setPlayer2(games2[i]);
			game.setId(i + 1);
			matchs.add(game);
		}
		bean.setGames(matchs);
		roundDao.addRound(bean);
		return;
	}

	@Test
	public void AC_Create_RoundBet() throws Exception {
		RoundBets roundBeat=new RoundBets();
		roundBeat.setCompany(company);
		roundBeat.setPrice(price);
		roundBeat.setRound(round);
		roundBeat.setSeason(season);
		rondBetDao.add(roundBeat);
	}

}
