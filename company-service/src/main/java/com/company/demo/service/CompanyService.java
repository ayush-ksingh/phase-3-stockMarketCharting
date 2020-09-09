package com.company.demo.service;

import java.util.List;

import com.company.demo.dto.CompanyDto;
import com.company.demo.dto.CompareCompany;
import com.company.demo.dto.CompareStockPrices;
import com.company.demo.dto.StockPriceDto;

public interface CompanyService {
	
	List<CompanyDto> getAllCompanies();
	void addOrUpdateCompany(CompanyDto companyDto);
	CompanyDto getCompanyDetailById(Integer id);
	void deactivateCompany(Integer id);
	List<CompanyDto> getMatchingCompanies(String companyName);
	List<StockPriceDto> getStockPriceByCompanies(CompareCompany compare);

}
