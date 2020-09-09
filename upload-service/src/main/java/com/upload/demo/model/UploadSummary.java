package com.upload.demo.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadSummary {
	
	private String companyName;
	private String exchangeName;
	private Integer numberOfRecords;
	private LocalDate from;
	private LocalDate to;

}
