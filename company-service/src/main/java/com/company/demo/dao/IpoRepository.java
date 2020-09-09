package com.company.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.demo.model.Ipo;

@Repository
public interface IpoRepository extends JpaRepository<Ipo, Integer>{

}
