package com.sector.app.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sector.app.dto.CompareCompaniesInSector;
import com.sector.app.dto.CompareTwoSector;
import com.sector.app.dto.SectorDto;
import com.sector.app.entity.Company;
import com.sector.app.entity.Sector;
import com.sector.app.entity.StockPrice;
import com.sector.app.repository.CompanyRepository;
import com.sector.app.repository.SectorRepository;
import com.sector.app.repository.StockPriceRepository;

@Service
public class SectorServiceImpl implements SectorService{
	
	@Autowired
	private SectorRepository sectorRepository;
	
	@Autowired
	private StockPriceRepository stockRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
// to verify
	@Transactional
	public void addSector(Sector sector) {
		sectorRepository.save(sector);
	}

// to verify	
	@Transactional
	public List<SectorDto> getAllSectors(){
		List<SectorDto> sectorList = new ArrayList<>();
		List<Sector> list = sectorRepository.findAll();
		if (list.size()> 0) {
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Type listType = new TypeToken<List<SectorDto>>(){}.getType();
			return mapper.map(list, listType);
		}
		else
			return sectorList;
		
	}
	
	@Transactional
	public List<CompareCompaniesInSector> getSectorDetailByPeriod(CompareCompaniesInSector compare){
		List<CompareCompaniesInSector> compareCompaniesInSector = new ArrayList<>();
		List<Company> companydtos = companyRepository.findBySector(compare.getSectorName());
		for (Company companydto: companydtos) {
			List<StockPrice> stocks = stockRepository.findByCompanyCodeAndDateBetween(companydto.getStockCode(), compare.getFrom(), compare.getTo());
			List<Float> sCurrentPrices = new ArrayList<>();
			for (StockPrice stock: stocks) {
				sCurrentPrices.add(stock.getCurrentPrice());
			}
			Float sumPrice = new Float(0);
			for (Float f: sCurrentPrices) {
				sumPrice+=f;
			}
			Float avgCurrentPrice = (sumPrice/(sCurrentPrices.size()));
			compareCompaniesInSector.add(new CompareCompaniesInSector(compare.getSectorName(), companydto.getCompanyName(), avgCurrentPrice, compare.getFrom(), compare.getTo()));
		}
		return compareCompaniesInSector;
	}
	
	// method to compare two sectors 
	// output format sectorname1 companyname avgstockprice from to --> list
	// output format sectorname2 companyname avgstockprice from to --> list
	@Transactional
	public List<CompareCompaniesInSector> compareTwoSectors(CompareTwoSector compare){
		List<CompareCompaniesInSector> compareCompaniesInSector = new ArrayList<>();
		List<Company> companydtos1 = companyRepository.findBySector(compare.getSectorName1());
		List<Company> companydtos2 = companyRepository.findBySector(compare.getSectorName2());
		for (Company companydto: companydtos1) {
			List<StockPrice> stocks = stockRepository.findByCompanyCodeAndDateBetween(companydto.getStockCode(), compare.getFrom(), compare.getTo());
			List<Float> sCurrentPrices = new ArrayList<>();
			for (StockPrice stock: stocks) {
				sCurrentPrices.add(stock.getCurrentPrice());
			}
			Float sumPrice = new Float(0);
			for (Float f: sCurrentPrices) {
				sumPrice+=f;
			}
			Float avgCurrentPrice = (sumPrice/(sCurrentPrices.size()));
			compareCompaniesInSector.add(new CompareCompaniesInSector(compare.getSectorName1(), companydto.getCompanyName(), avgCurrentPrice, compare.getFrom(), compare.getTo()));
		}
		for (Company companydto: companydtos2) {
			List<StockPrice> stocks1 = stockRepository.findByCompanyCodeAndDateBetween(companydto.getStockCode(), compare.getFrom(), compare.getTo());
			List<Float> sCurrentPrices = new ArrayList<>();
			for (StockPrice stock: stocks1) {
				sCurrentPrices.add(stock.getCurrentPrice());
			}
			Float sumPrice = new Float(0);
			for (Float f: sCurrentPrices) {
				sumPrice+=f;
			}
			Float avgCurrentPrice = (sumPrice/(sCurrentPrices.size()));
			compareCompaniesInSector.add(new CompareCompaniesInSector(compare.getSectorName2(), companydto.getCompanyName(), avgCurrentPrice, compare.getFrom(), compare.getTo()));
		}
		return compareCompaniesInSector;
	}
}
