package org.alterq.mvc.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.alterq.domain.RolCompany;
import org.alterq.domain.UserAlterQ;
import org.alterq.util.enumeration.RolNameEnum;
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
	public static Cookie cookies;

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
		System.out.println("==="+mapper.writeValueAsString(bean));
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		auth.andDo(MockMvcResultHandlers.print());
		auth.andExpect(jsonPath("$.userAlterQ.id", is("prueba@prueba.es")));
		MvcResult result = auth.andReturn();
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		cookies = result.getResponse().getCookie("session");
		System.out.println("cookieSession:" + cookies.getValue());

	}

	@Test
	public void AB_addCompanyRol1() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		List<RolCompany> rcList = new ArrayList<RolCompany>();
		RolCompany rc1 = new RolCompany();
		rc1.setCompany(10);
		rc1.setRol(RolNameEnum.ROL_USERADVANCED.getValue());
		RolCompany rc2 = new RolCompany();
		rc2.setCompany(10);
		rc2.setRol(RolNameEnum.ROL_USER.getValue());
		RolCompany rc3 = new RolCompany();
		rc3.setCompany(5);
		rc3.setRol(RolNameEnum.ROL_USER.getValue());
		rcList.add(rc1);
		rcList.add(rc2);
		rcList.add(rc3);
		bean.setRols(rcList);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("==="+mapper.writeValueAsString(bean));
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount/" + bean.getId() + "/rolcompany").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)).cookie(cookies));
		auth.andDo(MockMvcResultHandlers.print());
		auth.andExpect(jsonPath("$.userAlterQ.id", is("prueba@prueba.es")));
		auth.andExpect(jsonPath("$.userAlterQ.rols[0].company", is(10)));
	}

	@Test
	public void AB_addCompanyRol2() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		List<RolCompany> rcList = new ArrayList<RolCompany>();
		RolCompany rc1 = new RolCompany();
		rc1.setCompany(15);
		rc1.setRol(RolNameEnum.ROL_USER.getValue());
		rcList.add(rc1);
		bean.setRols(rcList);
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/myaccount/" + bean.getId() + "/rolcompany").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)).cookie(cookies));
		auth.andDo(MockMvcResultHandlers.print());
		auth.andExpect(jsonPath("$.userAlterQ.id", is("prueba@prueba.es")));
		auth.andExpect(jsonPath("$.userAlterQ.rols[0].company", is(15)));
	}
	@Test
	public void AC_getAllCompanyRolForUser() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.get("/myaccount/" + bean.getId() + "/rolcompany").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		auth.andDo(MockMvcResultHandlers.print());
		auth.andExpect(jsonPath("$.userAlterQ.id", is("prueba@prueba.es")));
		auth.andExpect(jsonPath("$.userAlterQ.rols[0].company", is(15)));
	}
	@Test
	public void AD_deleteCompanyRolForUser() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		List<RolCompany> rcList = new ArrayList<RolCompany>();
		RolCompany rc1 = new RolCompany();
		rc1.setCompany(15);
		rc1.setRol(RolNameEnum.ROL_USER.getValue());
		rcList.add(rc1);
		bean.setRols(rcList);
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.delete("/myaccount/" + bean.getId() + "/rolcompany").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)).cookie(cookies));
		auth.andDo(MockMvcResultHandlers.print());
		auth.andExpect(jsonPath("$.userAlterQ.id", is("prueba@prueba.es")));
		auth.andExpect(jsonPath("$.userAlterQ.rols[0].company", is(10)));
	}

	@Test
	public void ZZ_deleteUser() throws Exception {
		UserAlterQ bean = new UserAlterQ();
		bean.setId("prueba@prueba.es");
		bean.setPwd("password");
		ObjectMapper mapper = new ObjectMapper();
		ResultActions auth = this.mockMvc.perform(MockMvcRequestBuilders.post("/test/deleteUser").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		// auth.andExpect(jsonPath("$.userAlterQ", is()));
		auth.andExpect(status().isOk());
		auth.andDo(MockMvcResultHandlers.print());
	}

}