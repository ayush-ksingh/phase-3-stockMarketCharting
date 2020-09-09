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
public class CompareTwoSector {
	
	private String sectorName1;
	private String sectorName2;
	private LocalDate from;
	private LocalDate to;

}
