package com.sector.app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompareCompaniesInSector {
	
	private String sectorName;
	private String companyName;
	private Float averageStockPrice;
	private LocalDate from;
	private LocalDate to;
	public CompareCompaniesInSector(String sectorName, LocalDate from, LocalDate to) {
		super();
		this.sectorName = sectorName;
		this.from = from;
		this.to = to;
	}
	
	
}
