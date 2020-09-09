package com.company.demo.dto;

import java.util.List;

import com.company.demo.model.StockPrice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareStockPrices {
	
	private List<StockPrice> stockPriceCompany1;
	private List<StockPrice> stockPriceCompany2;
}
