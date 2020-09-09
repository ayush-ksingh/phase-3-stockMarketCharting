package com.company.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareCompany {
	
	private String companyName1;
	private String stockExchangeName1;
	private String companyName2;
	private String stockExchangeName2;
	private LocalDate from;
	private LocalDate to;
	
}
