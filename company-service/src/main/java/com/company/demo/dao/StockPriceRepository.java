package com.company.demo.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.demo.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Integer>{
	
//	@Query(nativeQuery = true, value = "SELECT * FROM stock_price AS s WHERE s.company_code = :companyCode AND s.date BETWEEN :from AND :to")
	List<StockPrice> findByCompanyCodeAndDateBetween(String companyCode, LocalDate from, LocalDate to);
	
//	List<StockPrice> findByCompanyCode(String companyCode);
}
