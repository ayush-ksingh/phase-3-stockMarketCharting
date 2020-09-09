package com.upload.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upload.demo.dto.StockPriceDTO;

public interface StockPriceRepository extends JpaRepository<StockPriceDTO, Integer>{

}
