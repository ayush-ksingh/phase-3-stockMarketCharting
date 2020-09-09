package com.company.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.FixMethodOrder;


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
import org.junit.Test;
import com.company.demo.CompanyServiceApplication;

//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//@WebMvcTest(CompanyController.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CompanyServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class CompanyControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void retrieveAllCompanies_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/company-ws/company/getAllCompany").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(4)))
		.andReturn();
	}
	
	@Test
	public void viewIposInOrder_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/company-ws/ipo/viewIpo").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(2)))
		.andReturn();
	}
	
	@Test
	public void getCompanyById_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/company-ws/company/getCompany/2").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$.companyId").exists())
		.andExpect(jsonPath("$.companyName").value("NBI"))
		.andExpect(jsonPath("$.*", hasSize(9)))
		.andReturn();
	}
	
	@Test
	public void getIpoById_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/company-ws/ipo/getIpoById/2").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$.ipoId").exists())
		.andExpect(jsonPath("$.companyName").value("NBI"))
		.andExpect(jsonPath("$.*", hasSize(7)))
		.andReturn();
	}
	
	@Test
	public void addCompany_test() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/company-ws/company/addCompany").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"companyName\": \"Tata\", \"turnover\": \"2371\", \"ceo\": \"Mr.D\", \"boardOfDirectors\": \"Mr.D, Mr.E\", \"stockExchangeName\": \"BSE\", \"sector\": \"Consultancy\", \"brief\": \"Nil\", \"stockCode\": \"333222\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andReturn();
	}
	
	@Test
	public void updateIpo_test() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.put("/company-ws/ipo/updateIpo").contentType(MediaType.APPLICATION_JSON)
				.content("{\"ipoId\": 2, \"companyName\": \"Tata\", \"exchangeName\": \"BSE\", \"pricePerShare\": 320.5, \"numberOfShares\": 2765, \"openDateTime\": \"2020-08-01T09:30:00\", \"remarks\": \"Nil\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$").value("IpoDto Updated Successfully"))
		.andExpect(jsonPath("$").isString())
		.andReturn();
	}
	
	@Test
	public void getMatchingCompanies_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/company-ws/company/compare/company/B").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].companyName").value("State Bank of India"))
		.andExpect(jsonPath("$[0].*", hasSize(9)))
		.andReturn();
	}
	
	@Test
	public void getStockPriceListByCompanies_test() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/company-ws/company/compare/stockPrices")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"companyName1\": \"State Bank of India\",\"stockExchangeName1\": \"BSE\",\"companyName2\": \"NBI\", \"stockExchangeName2\": \"BSE\", \"from\": \"2020-08-01\", \"to\":\"2020-08-04\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(8)))
				.andReturn();
		}

}
