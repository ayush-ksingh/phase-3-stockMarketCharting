package com.exchange.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.exchange.demo.ExchangeServiceApplication;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExchangeServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class StockExchangeControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void viewAllStockExchanges_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/exchange-ws/getAllExchanges").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$", hasSize(2)))
		.andReturn();
	}
	
	@Test
	public void addStockExchange_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/exchange-ws/addExchange").contentType(MediaType.APPLICATION_JSON)
				.content("{\"stockExchangeId\": 3, \"stockExchangeName\": \"NASDAQ\", \"brief\": \"Nil\", \"address\": \"Times Square, New York\", \"remarks\": \"Nil\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$").value("Stock Exchange added successfully"))
		.andReturn();
	}

}
