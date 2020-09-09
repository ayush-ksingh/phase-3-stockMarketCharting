package com.exchange.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.demo.dto.StockExchangeDto;
import com.exchange.demo.service.StockExchangeServiceImpl;

@RestController
@RequestMapping("/exchange-ws")
@CrossOrigin(origins = "*")
public class StockExchangeController {
	
	@Autowired
	private StockExchangeServiceImpl exchangeService;
	
	@PostMapping("/addExchange")
	public ResponseEntity<String> addStockExchange(@RequestBody StockExchangeDto exchange){
		exchangeService.addNewStockExchange(exchange);
		return new ResponseEntity<>("Stock Exchange added successfully", HttpStatus.OK);
	}
	
	@GetMapping("/getAllExchanges")
	public ResponseEntity<List<StockExchangeDto>> viewAllStockExchanges(){
		return new ResponseEntity<>(exchangeService.getAllStockExchanges(), HttpStatus.OK);
	}
}
