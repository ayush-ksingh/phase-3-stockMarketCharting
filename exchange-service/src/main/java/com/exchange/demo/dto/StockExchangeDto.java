package com.exchange.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockExchangeDto {
	
	private Integer stockExchangeId;
	private String stockExchangeName;
	private String brief;
	private String address;
	private String remarks;
}
