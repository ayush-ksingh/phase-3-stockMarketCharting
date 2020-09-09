package com.company.demo.model;

import java.time.LocalDateTime;

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
@Table(name = "ipo")
public class Ipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ipo_id")
	private Integer ipoId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "exchange_name")
	private String exchangeName;
	
	@Column(name = "price_per_share")
	private float pricePerShare;
	
	@Column(name = "number_of_shares")
	private int numberOfShares;
	
	@Column(name = "open_date_time")
	private LocalDateTime openDateTime;
	
	private String remarks;

}
