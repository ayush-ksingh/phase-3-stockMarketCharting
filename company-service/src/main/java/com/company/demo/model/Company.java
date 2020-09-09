package com.company.demo.model;

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
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Integer companyId;
	
	@Column(name = "company_name")
	private String companyName;
	
	private String turnover;
	private String ceo;
	
	@Column(name = "board_of_directors")
	private String boardOfDirectors;
	
	@Column(name = "stock_exchange_name")
	private String stockExchangeName;
	
	private String sector;
	private String brief;
	
	@Column(name = "stock_code")
	private String stockCode;
	
}
