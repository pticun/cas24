package org.alterq.mvc.test;

import org.alterq.domain.UserAlterQ;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RolCompanyControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void AA_CreateUser() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		bean.setPwd("password");
		bean.setName("nameisjohn");
		bean.setPhoneNumber("1234567890");
		bean.setAccept(Boolean.TRUE.toString());
		bean.setIdCard("11111111H");
		bean.setSurnames("surnames");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		auth.andDo(MockMvcResultHandlers.print());
		// Cookie c = result.getResponse().getCookie("session");
		// System.out.println("cookieSession:" + c.getValue());
	}

	@Test
	public void ZZ_deleteUser() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		bean.setPwd("password");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/test/deleteUser").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		auth.andDo(MockMvcResultHandlers.print());
		// Cookie c = result.getResponse().getCookie("session");
		// System.out.println("cookieSession:" + c.getValue());
		// bean.setBalance("100");
		// this.mockMvc.perform(MockMvcRequestBuilders.put("/myaccount/prueba@arroba.es/update").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)).cookie(c)).andDo(MockMvcResultHandlers.print());
	}

}