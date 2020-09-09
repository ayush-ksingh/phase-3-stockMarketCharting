package com.upload.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockPrice {
	
	private String companyCode;
	private String exchangeName;
	private float currentPrice;
	private LocalDate date;
	private LocalTime time;

}
