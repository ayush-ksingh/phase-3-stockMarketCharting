package com.sector.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
