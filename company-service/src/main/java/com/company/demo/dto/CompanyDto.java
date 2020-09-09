package com.company.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
	
	private Integer companyId;
	private String companyName;
	private String turnover;
	private String ceo;
	private String boardOfDirectors;
	private String stockExchangeName;
	private String sector;
	private String brief;
	private String stockCode;
	
}

