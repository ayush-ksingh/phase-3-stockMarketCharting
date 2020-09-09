package com.exchange.demo.model;

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
@Table(name = "stock_exchange")
public class StockExchange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_exchange_id")
	private Integer stockExchangeId;
	
	@Column(name = "stock_exchange_name")
	private String stockExchangeName;
	
	private String brief;
	private String address;
	private String remarks;

}
