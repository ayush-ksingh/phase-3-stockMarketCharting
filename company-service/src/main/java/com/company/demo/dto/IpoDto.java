package com.company.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpoDto {
	
	private Integer ipoId;
	private String companyName;
	private String exchangeName;
	private float pricePerShare;
	private int numberOfShares;
	private LocalDateTime openDateTime;
	private String remarks;

}
