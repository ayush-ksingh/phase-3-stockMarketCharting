package com.sector.app.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sector.app.SectorServiceApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SectorServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class SectorControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void getAllSector_test() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sector-ws/getAllSector").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(2)))
		.andReturn();
	}
	
	@Test
	public void getInSectorByPeriod_test() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sector-ws/compare/sector").contentType(MediaType.APPLICATION_JSON)
				.content("{\"sectorName\": \"Banking\", \"from\": \"2020-08-01\", \"to\": \"2020-08-04\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].*", hasSize(5)))
		.andReturn();
	}
	
	@Test
	public void getTwoSectorDetails_test() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/sector-ws/compare/2sector").contentType(MediaType.APPLICATION_JSON)
				.content("{\"sectorName1\": \"Banking\", \"sectorName2\": \"IT\", \"from\": \"2020-08-01\", \"to\": \"2020-08-04\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("$[0].companyName").value("State Bank of India"))
		.andExpect(jsonPath("$[3].companyName").value("Infosys"))
		.andExpect(jsonPath("$[0].*", hasSize(5)))
		.andReturn();
	}
}
