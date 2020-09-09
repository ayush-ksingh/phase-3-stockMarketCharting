package com.sector.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SectorDto {
	
	private Integer sectorId;
	private String sectorName;
	private String brief;

}
