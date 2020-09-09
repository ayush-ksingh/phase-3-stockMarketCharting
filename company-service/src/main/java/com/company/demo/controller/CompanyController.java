package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.dto.CompanyDto;
import com.company.demo.dto.CompareCompany;
import com.company.demo.dto.CompareStockPrices;
import com.company.demo.dto.StockPriceDto;
import com.company.demo.exception.CustomException;
import com.company.demo.service.CompanyServiceImpl;

@RestController
@RequestMapping("/company-ws/company")
@CrossOrigin(origins = "*")
public class CompanyController {
	
	@Autowired
	private CompanyServiceImpl companyService;
	
// Admin rest endpoints
	
	@GetMapping(value = "/getAllCompany")
	public List<CompanyDto> retrieveAllCompanies(){
		return (companyService.getAllCompanies());
	}
	
	@PostMapping("/addCompany")
	public void addNewCompany(@RequestBody CompanyDto companyDto){
		companyService.addOrUpdateCompany(companyDto);
	}
	
	@PutMapping("/updateCompany")
	public void updateCompany(@RequestBody CompanyDto companyDto){
		companyService.addOrUpdateCompany(companyDto);
//      exception handling not needed input id is linked to the company in the Frontend	
	}
	
	@GetMapping("/getCompany/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Integer id) throws CustomException{
		CompanyDto companyDto = companyService.getCompanyDetailById(id);
		if (companyDto==null) {
			throw new CustomException("Company with id: "+ id + " not found");
		}
		return new ResponseEntity<>(companyService.getCompanyDetailById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCompany/{id}")
	public void deleteCompany(@PathVariable Integer id){
		companyService.deactivateCompany(id);
//		return new ResponseEntity<>("CompanyDto Deleted Successfully", HttpStatus.OK);
//      exception handling not needed input id is linked to the company in the Frontend		
	}
	
	
	
// User rest endpoints
	
	@GetMapping("/compare/company/{companyName}")
	public ResponseEntity<List<CompanyDto>> companiesMatchedByPattern(@PathVariable String companyName) throws CustomException{
		if (companyService.getMatchingCompanies(companyName).size()==0) {
			throw new CustomException("Company with pattern : " + companyName + " not found");
		}
		return new ResponseEntity<>(companyService.getMatchingCompanies(companyName), HttpStatus.OK);
	}
	
	@PostMapping("/compare/stockPrices")
	public ResponseEntity<List<StockPriceDto>> getStockPriceListByCompanies(@RequestBody CompareCompany compare) throws CustomException{
		if (companyService.getStockPriceByCompanies(compare).size()==0) {
			throw new CustomException("Comparison cannot occur as invalid details are provided");
		}
		return new ResponseEntity<>(companyService.getStockPriceByCompanies(compare), HttpStatus.OK);
	}
	
}
