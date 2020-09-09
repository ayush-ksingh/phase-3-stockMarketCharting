package com.upload.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upload.demo.dto.CompanyDTO;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDTO, Integer>{
	
//	@Query(value = "select * from company as c where c.stock_code = 500112", nativeQuery = true)
	List<CompanyDTO> findByStockCode(String stockCode);
}
