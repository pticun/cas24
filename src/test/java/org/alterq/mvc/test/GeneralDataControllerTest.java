package org.alterq.mvc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.alterq.domain.GeneralData;
import org.codehaus.jackson.map.ObjectMapper;
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
public class GeneralDataControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void AA_getGeneralData() throws Exception {
		ResultActions ra = this.mockMvc.perform(get("/generalData/1"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		ra.andExpect(jsonPath("company").value(1));
	}

	@Test
	public void AB_createGeneralData() throws Exception {
		GeneralData bean = new GeneralData();
		bean.setCompany(2);
		bean.setActive(true);
		bean.setRound(1);
		bean.setSeason(5);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Elmapper:" + mapper.writeValueAsString(bean));
		ResultActions ra = this.mockMvc.perform(post("/generalData").characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());

		bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		System.out.println("new GeneralData.id:" + bean.getId());
	}

	@Test
	public void AC_updateGeneralData() throws Exception {
		ResultActions ra = this.mockMvc.perform(get("/generalData/2"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		ObjectMapper mapper = new ObjectMapper();
		GeneralData bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		bean.setActive(false);
		ra = this.mockMvc.perform(put("/generalData").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(bean)));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
	}

	@Test
	public void AD_deleteGeneralData() throws Exception {
		ResultActions ra = this.mockMvc.perform(get("/generalData/2"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		ObjectMapper mapper = new ObjectMapper();
		GeneralData bean = mapper.readValue(ra.andReturn().getResponse().getContentAsString(), GeneralData.class);
		bean.setActive(false);
		ra = this.mockMvc.perform(delete("/generalData/" + bean.getCompany()).contentType(MediaType.APPLICATION_JSON));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
	}

	@Test
	public void AE_getAllGeneralData() throws Exception {
		ResultActions ra = this.mockMvc.perform(get("/generalData"));
		ra.andDo(MockMvcResultHandlers.print());
		ra.andExpect(status().isOk());
		// ra.andExpect(jsonPath("$.company").value("1"));
	}

}
