package com.exchange.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.demo.model.StockExchange;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer>{

}
