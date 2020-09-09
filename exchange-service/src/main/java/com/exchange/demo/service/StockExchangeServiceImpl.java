package com.exchange.demo.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exchange.demo.dao.StockExchangeRepository;
import com.exchange.demo.dto.StockExchangeDto;
import com.exchange.demo.model.StockExchange;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{
	
	@Autowired
	private StockExchangeRepository exchangeRepository;
	
// Admin use cases
	
	@Transactional
	public void addNewStockExchange(StockExchangeDto exchange) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		exchangeRepository.save(mapper.map(exchange, StockExchange.class));
	}
	
	@Transactional
	public List<StockExchangeDto> getAllStockExchanges(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockExchangeDto>>(){}.getType();
		return mapper.map(exchangeRepository.findAll(), listType);
	}
	
}
