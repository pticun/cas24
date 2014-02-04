package org.alterq.mvc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml","classpath:/spring/app-servlet.xml" })
public class BetControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void getLastRound() throws Exception {
//       System.out.println(this.mockMvc.perform(get("/bet")).andReturn().getResponse().getContentAsString());
      this.mockMvc.perform(get("/bet")).andDo(MockMvcResultHandlers.print());
    }

    
    @Test
    public void addBet() throws Exception {
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/login").param("id", "idmail@arroba.es").param("pwd", "password"));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		auth.andDo(MockMvcResultHandlers.print());
		Cookie c = result.getResponse().getCookie("session");
		this.mockMvc.perform(MockMvcRequestBuilders.post("/bet").param("1_1", "on").param("season", "2013").param("round", "1").cookie(c)).andDo(MockMvcResultHandlers.print());
		
    }
    @Test
    public void findAllUserBetsParams() throws Exception {
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/bet/season/2013/round/1/user/idmail@arroba.es/")).andDo(MockMvcResultHandlers.print());
    	
    }
    
    
}
