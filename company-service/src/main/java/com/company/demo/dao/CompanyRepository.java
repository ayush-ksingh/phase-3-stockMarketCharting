package com.company.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.company.demo.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	@Query(value = "SELECT c FROM Company c WHERE c.companyName LIKE CONCAT('%',:companyName,'%')")
	List<Company> findMatchingCompany(String companyName);
	
	List<Company> findByCompanyNameAndStockExchangeName(String companyName, String stockExchangeName);
}
