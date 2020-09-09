package com.company.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.demo.dao.CompanyRepository;
import com.company.demo.dao.StockPriceRepository;
import com.company.demo.dto.CompanyDto;
import com.company.demo.dto.CompareCompany;
import com.company.demo.dto.StockPriceDto;
import com.company.demo.model.Company;
import com.company.demo.model.StockPrice;

import java.lang.reflect.Type;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private StockPriceRepository stockPriceRepository; 

// Admin Use cases
	
	@Transactional
	public List<CompanyDto> getAllCompanies() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
		return mapper.map(companyRepository.findAll(), listType);
	}
	
	@Transactional
	public void addOrUpdateCompany(CompanyDto companyDto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		companyRepository.save(mapper.map(companyDto, Company.class));
	}
	
	@Transactional
	public CompanyDto getCompanyDetailById(Integer id) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<Company> company = companyRepository.findById(id);
		if (!company.isPresent()) {
			return null;
		}
		return mapper.map(company.get(), CompanyDto.class);
//		return mapper.map(companyRepository.findById(id).get(), CompanyDto.class);
	}
	
	@Transactional
	public void deactivateCompany(Integer id) {
		companyRepository.deleteById(id);
	}

// User use cases
	@Transactional
	public List<CompanyDto> getMatchingCompanies(String companyName){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
		return mapper.map(companyRepository.findMatchingCompany(companyName), listType);
	}
	
	@Transactional
	public List<StockPriceDto> getStockPriceByCompanies(CompareCompany compare) {
		List<StockPriceDto> newList = new ArrayList<>();
		Company company1dto = companyRepository.findByCompanyNameAndStockExchangeName(compare.getCompanyName1(), compare.getStockExchangeName1())
				.iterator().next();
		Company company2dto = companyRepository.findByCompanyNameAndStockExchangeName(compare.getCompanyName2(), compare.getStockExchangeName2())
				.iterator().next();
		List<StockPrice> stock1 = stockPriceRepository.findByCompanyCodeAndDateBetween(company1dto.getStockCode(), compare.getFrom(), compare.getTo());
		List<StockPrice> stock2 = stockPriceRepository.findByCompanyCodeAndDateBetween(company2dto.getStockCode(), compare.getFrom(), compare.getTo());
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<StockPriceDto>>(){}.getType();
		newList.addAll(mapper.map(stock1, listType));
		newList.addAll(mapper.map(stock2, listType));
		return newList;	
	}
	
	
}
