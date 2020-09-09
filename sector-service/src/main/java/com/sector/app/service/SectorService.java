package com.sector.app.service;

import java.util.List;

import com.sector.app.dto.CompareCompaniesInSector;
import com.sector.app.dto.CompareTwoSector;
import com.sector.app.dto.SectorDto;
import com.sector.app.entity.Sector;

public interface SectorService {
	void addSector(Sector sector);
	List<SectorDto> getAllSectors();
	List<CompareCompaniesInSector> getSectorDetailByPeriod(CompareCompaniesInSector compare);
	List<CompareCompaniesInSector> compareTwoSectors(CompareTwoSector compare);
}
