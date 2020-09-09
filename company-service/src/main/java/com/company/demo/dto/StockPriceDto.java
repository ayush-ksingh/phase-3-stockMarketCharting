package com.company.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceDto {
	
	private Integer stockid;
	private String companyCode;
	private String exchangeName;
	private float currentPrice;
	private LocalDate date;
	private LocalTime time;
}
