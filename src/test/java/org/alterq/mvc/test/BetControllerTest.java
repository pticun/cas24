package org.alterq.mvc.test;

import javax.servlet.http.Cookie;

import org.alterq.domain.UserAlterQ;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public void addBet() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@arroba.es");
		bean.setPwd("password");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/login").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(bean)));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		auth.andDo(MockMvcResultHandlers.print());
		Cookie c = result.getResponse().getCookie("session");
		System.out.println("cookieSession:" + c.getValue());
		this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount/prueba@arroba.es/season/2014/round/14/bet").param("1_1", "on").param("season", "2014").param("round", "14").cookie(c)).andDo(MockMvcResultHandlers.print());
		
    }
    @Test
    public void findAllUserBetsParams() throws Exception {
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/myaccount/prueba@arroba.es/season/2014/round/14/bet")).andDo(MockMvcResultHandlers.print());
    	
    }
    @Test
    public void calculatePrice() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@arroba.es");
		bean.setPwd("password");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/login").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(bean)));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		auth.andDo(MockMvcResultHandlers.print());
		Cookie c = result.getResponse().getCookie("session");
		System.out.println("cookieSession:" + c.getValue());
		this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount/prueba@arroba.es/season/2014/round/14/bet/price").param("1_1", "on").param("season", "2014").param("round", "14").cookie(c)).andDo(MockMvcResultHandlers.print());
    }
    
    
}
