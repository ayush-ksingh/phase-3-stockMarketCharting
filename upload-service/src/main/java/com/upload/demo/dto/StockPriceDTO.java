package com.upload.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_price")
public class StockPriceDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Integer stockid;
	
	@Column(name = "company_code")
	private String companyCode;
	
	@Column(name = "exchange_name")
	private String exchangeName;
	
	@Column(name = "current_price")
	private Float currentPrice;
	
	private LocalDate date;
	
	private LocalTime time;
}
