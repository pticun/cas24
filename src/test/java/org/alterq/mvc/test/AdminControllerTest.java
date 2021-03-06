package org.alterq.mvc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/app-servlet.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
/*	
	@Test
	public void AA_openRound() throws Exception {
		GeneralData bean = new GeneralData();
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions ra = this.mockMvc.perform(post("/admin/company/1/season/2014/round/12/open"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		System.out.println("new GeneralData.active:" + bean.isActive());
		Assert.assertTrue(bean.isActive());
	}
*/	

/*	
	@Test
	public void AA_closeRound() throws Exception {
		//GeneralData bean = new GeneralData();
		//ObjectMapper mapper = new ObjectMapper();
		
		ResultActions ra = this.mockMvc.perform(post("/admin/company/1/season/2014/round/12/close"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		//bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		//System.out.println("new GeneralData.active:" + bean.isActive());
		//Assert.assertTrue(bean.isActive());
	}
*/
/*	
	@Test
	public void AA_finalBetRound() throws Exception {
		//GeneralData bean = new GeneralData();
		//ObjectMapper mapper = new ObjectMapper();
		
		ResultActions ra = this.mockMvc.perform(post("/admin/company/1/season/2014/round/12/finalBet/0"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		//bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		//System.out.println("new GeneralData.active:" + bean.isActive());
		//Assert.assertTrue(bean.isActive());
		ResultActions ra2 = this.mockMvc.perform(post("/admin/company/1/season/2014/round/12/finalBet/1"));
		ra2.andDo(MockMvcResultHandlers.print());
		ra2.andExpect(status().isOk());
		
	}
*/	
	
	@Test
	public void AA_resultBetRound() throws Exception {
		//GeneralData bean = new GeneralData();
		//ObjectMapper mapper = new ObjectMapper();
		//Round02
//		ResultActions ra = this.mockMvc.perform(post("/admin/2015/2/1112XX2X111X22M0/resultBet"));
		//Round03
//		ResultActions ra = this.mockMvc.perform(post("/admin/2015/3/22121XX212X1X112/resultBet"));
		//Round04
		ResultActions ra = this.mockMvc.perform(post("/admin/2015/4/1X2222122222X1M1/resultBet"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		//bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		//System.out.println("new GeneralData.active:" + bean.isActive());
		//Assert.assertTrue(bean.isActive());
	}

/*	
	@Test
	public void AA_prizeRound() throws Exception {
		//GeneralData bean = new GeneralData();
		//ObjectMapper mapper = new ObjectMapper();
		
		ResultActions ra = this.mockMvc.perform(post("/admin/company/1/season/2014/round/12/prize15/0/258136.45/prize14/0/38720.47/prize13/0/610.97/prize12/0/48.26/prize11/0/6.08/prize10/0/1.39"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		//bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		//System.out.println("new GeneralData.active:" + bean.isActive());
		//Assert.assertTrue(bean.isActive());
	}

	@Test
	public void AA_prizeRound() throws Exception {
		PrizesRound bean = new PrizesRound();
		bean.setCompany(1);
		bean.setRound(12);
		bean.setSeason(2014);
		
		
		List<Prize> lPrizes = new ArrayList<Prize>();
		
		Prize prize10 = new Prize();
		prize10.setId(10);
		prize10.setAmount((float) 1.39);
		prize10.setCount(0);
		lPrizes.add(prize10);
		
		Prize prize11 = new Prize();
		prize11.setId(11);
		prize11.setAmount((float) 6.08);
		prize11.setCount(0);
		lPrizes.add(prize11);

		Prize prize12 = new Prize();
		prize12.setId(12);
		prize12.setAmount((float) 48.26);
		prize12.setCount(0);
		lPrizes.add(prize12);

		Prize prize13 = new Prize();
		prize13.setId(13);
		prize13.setAmount((float) 610.97);
		prize13.setCount(0);
		lPrizes.add(prize13);

		Prize prize14 = new Prize();
		prize14.setId(14);
		prize14.setAmount((float) 38720.47);
		prize14.setCount(0);
		lPrizes.add(prize14);
		
		Prize prize15 = new Prize();
		prize15.setId(15);
		prize15.setAmount((float) 258136.45);
		prize15.setCount(0);
		lPrizes.add(prize15);

		bean.setPrizes(lPrizes);
		
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Elmapper:" + mapper.writeValueAsString(bean));
		ResultActions ra = this.mockMvc.perform(post("/admin").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());

		ResponseDto rdo = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), ResponseDto.class);
		//System.out.println("new GeneralData.id:" + rdo.getRound());
		
	}
	@Test
	public void AA_newRoundGames() throws Exception {
		Round bean = new Round();
		bean.setCompany(1);
		bean.setRound(43);
		bean.setSeason(2014);
		
		
		List<Game> lGames = new ArrayList<Game>();
		
		Game game01 = new Game();
		game01.setId(1);
		game01.setPlayer1("Getafe");
		game01.setPlayer2("Villarreal");
		lGames.add(game01);
		
		Game game02 = new Game();
		game02.setId(2);
		game02.setPlayer1("At. Madrid");
		game02.setPlayer2("Granada");
		lGames.add(game02);

		Game game03 = new Game();
		game03.setId(3);
		game03.setPlayer1("Levante");
		game03.setPlayer2("Betis");
		lGames.add(game03);

		Game game04 = new Game();
		game04.setId(4);
		game04.setPlayer1("Málaga");
		game04.setPlayer2("Espanyol");
		lGames.add(game04);

		Game game05 = new Game();
		game05.setId(5);
		game05.setPlayer1("Barcelona");
		game05.setPlayer2("Celta");
		lGames.add(game05);

		Game game06 = new Game();
		game06.setId(6);
		game06.setPlayer1("Sevilla");
		game06.setPlayer2("R. Madrid");
		lGames.add(game06);

		Game game07 = new Game();
		game07.setId(7);
		game07.setPlayer1("Rayo Vallecano");
		game07.setPlayer2("Osasuna");
		lGames.add(game07);

		Game game08 = new Game();
		game08.setId(8);
		game08.setPlayer1("R. Sociedad");
		game08.setPlayer2("Valladolid");
		lGames.add(game08);

		Game game09 = new Game();
		game09.setId(9);
		game09.setPlayer1("Almería");
		game09.setPlayer2("Valencia");
		lGames.add(game09);

		Game game10 = new Game();
		game10.setId(10);
		game10.setPlayer1("Catania");
		game10.setPlayer2("Napoli");
		lGames.add(game10);

		Game game11 = new Game();
		game11.setId(11);
		game11.setPlayer1("Fiorentina");
		game11.setPlayer2("Milan");
		lGames.add(game11);

		Game game12 = new Game();
		game12.setId(12);
		game12.setPlayer1("Chievo");
		game12.setPlayer2("Bologna");
		lGames.add(game12);

		Game game13 = new Game();
		game13.setId(2);
		game13.setPlayer1("Juventus");
		game13.setPlayer2("Parma");
		lGames.add(game13);

		Game game14 = new Game();
		game14.setId(14);
		game14.setPlayer1("Genoa");
		game14.setPlayer2("Lazio");
		lGames.add(game14);

		Game game15 = new Game();
		game15.setId(15);
		game15.setPlayer1("Elche");
		game15.setPlayer2("Athletic Club");
		lGames.add(game15);

		bean.setGames(lGames);
		
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Elmapper:" + mapper.writeValueAsString(bean));
		ResultActions ra = this.mockMvc.perform(post("/admin").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());

		ResponseDto rdo = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), ResponseDto.class);
		//System.out.println("new GeneralData.id:" + rdo.getRound());
		
	}
*/

/*	@Test
	public void AA_fileGenation() throws Exception {
		//GeneralData bean = new GeneralData();
		//ObjectMapper mapper = new ObjectMapper();
		
//		ResultActions ra = this.mockMvc.perform(post("/admin/company/1/season/2014/round/34/getFile"));
		ResultActions ra = this.mockMvc.perform(post("/admin/company/1/season/2014/round/53/getFile"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		//bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		//System.out.println("new GeneralData.active:" + bean.isActive());
		//Assert.assertTrue(bean.isActive());
	}
*/	
	
}
