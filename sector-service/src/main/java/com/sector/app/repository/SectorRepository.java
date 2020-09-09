package com.sector.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sector.app.entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer>{

}
