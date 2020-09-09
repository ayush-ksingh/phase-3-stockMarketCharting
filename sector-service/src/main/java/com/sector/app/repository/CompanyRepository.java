package com.sector.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sector.app.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	List<Company> findBySector(String sector);
}
