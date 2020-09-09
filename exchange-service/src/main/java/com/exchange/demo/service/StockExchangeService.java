package com.exchange.demo.service;

import java.util.List;

import com.exchange.demo.dto.StockExchangeDto;

public interface StockExchangeService {
	
	void addNewStockExchange(StockExchangeDto exchange);
	List<StockExchangeDto> getAllStockExchanges();

}
