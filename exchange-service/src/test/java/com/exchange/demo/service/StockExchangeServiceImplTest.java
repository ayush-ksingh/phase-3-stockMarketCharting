package com.exchange.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.exchange.demo.dao.StockExchangeRepository;
import com.exchange.demo.dto.StockExchangeDto;
import com.exchange.demo.model.StockExchange;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StockExchangeServiceImplTest {
	
	@InjectMocks
	private StockExchangeServiceImpl stockService;
	
	@Mock
	private StockExchangeRepository exchangeRepository;
	
	private StockExchange ex1 = new StockExchange(1, "BSE", "Nil", "Bombay", "None");
	private StockExchange ex2 = new StockExchange(2, "NSE", "Nil", "Mumbai", "None");
	
	@Test
	public void getAllExchanges_test() {
		given(exchangeRepository.findAll()).willReturn(Arrays.asList(ex1, ex2));
		List<StockExchangeDto> result = stockService.getAllStockExchanges();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getAddress()).isEqualTo("Bombay");
		verify(exchangeRepository, times(1)).findAll();
	}

}
