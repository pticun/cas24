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

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/app-servlet.xml" })
public class AccountControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void logout() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("idmail@arroba.es");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount/forgotPwd").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		MvcResult result = auth.andReturn();
		auth.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void createUserAlterQ() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@arroba.es");
		bean.setPwd("password");
		bean.setName("nombre");
		bean.setPhoneNumber("1234567890");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(bean)));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		auth.andDo(MockMvcResultHandlers.print());
		Cookie c = result.getResponse().getCookie("session");
		System.out.println("cookieSession:" + c.getValue());
	}

	
	
	
}
