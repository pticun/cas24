package org.alterq.mvc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.alterq.domain.GeneralData;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

}
