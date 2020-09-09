package com.sector.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sector.app.dto.CompareCompaniesInSector;
import com.sector.app.dto.CompareTwoSector;
import com.sector.app.dto.SectorDto;
import com.sector.app.entity.Sector;
import com.sector.app.exception.CustomException;
import com.sector.app.service.SectorServiceImpl;

@RestController
@RequestMapping("/sector-ws")
@CrossOrigin(origins = "*")
public class SectorController {
	
	@Autowired
	private SectorServiceImpl sectorService;
	
	@PostMapping("/addSector")
	public void addSector(@RequestBody Sector sector) {
		sectorService.addSector(sector);
	}
	
	@GetMapping("/getAllSector")
	public ResponseEntity<List<SectorDto>> getAllSector(){
		return new ResponseEntity<>(sectorService.getAllSectors(), HttpStatus.FOUND);
	}
	
	@PostMapping("/compare/sector")
	public ResponseEntity<List<CompareCompaniesInSector>> getInSectorByPeriod(@RequestBody CompareCompaniesInSector compare) throws CustomException{
		if (sectorService.getSectorDetailByPeriod(compare)==null) {
			throw new CustomException("Details provided are invalid");
		}
		return new ResponseEntity<>(sectorService.getSectorDetailByPeriod(compare), HttpStatus.OK);
	}
	
	@PostMapping("/compare/2sector")
	public ResponseEntity<List<CompareCompaniesInSector>> getTwoSectorDetails(@RequestBody CompareTwoSector compare) throws CustomException{
		if (sectorService.compareTwoSectors(compare)==null) {
			throw new CustomException("Details provided are invalid");
		}
		return new ResponseEntity<>(sectorService.compareTwoSectors(compare), HttpStatus.OK);
	}
	
	
}
