package com.sector.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.sector.app.dto.CompareCompaniesInSector;
import com.sector.app.dto.CompareTwoSector;
import com.sector.app.dto.SectorDto;
import com.sector.app.entity.Company;
import com.sector.app.entity.Sector;
import com.sector.app.entity.StockPrice;
import com.sector.app.repository.CompanyRepository;
import com.sector.app.repository.SectorRepository;
import com.sector.app.repository.StockPriceRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SectorServiceImplTest {
	
	@InjectMocks
	private SectorServiceImpl sectorService;
	
	@Mock
	private SectorRepository sectorRepository;
	
	@Mock
	private StockPriceRepository stockRepository;
	
	@Mock
	private CompanyRepository companyRepository;
	
	private Sector s1 = new Sector(1,"Banking","Nil");
	private Sector s2 = new Sector(2, "IT", "Nil");
	private List<Company> c1 = new ArrayList<>(Arrays.asList(new Company(1,"State Bank of India","1234","A","A,B","BSE","Banking","Nil","500112"), 
			new Company(2,"NBI","2314","B","A,B","BSE","Banking","Nil","222")));
	private List<Company> c2 = new ArrayList<>(Arrays.asList(new Company(3,"Wipro","1234","A","A,B","BSE","IT","Nil","222333"), 
			new Company(4,"Infosys","2314","B","A,B","BSE","IT","Nil","333222")));
	
	private LocalDate from = LocalDate.of(2020, 8, 1);
	private LocalDate to = LocalDate.of(2020, 8, 4);
	private LocalTime ex = LocalTime.of(9, 30, 0);
	private CompareCompaniesInSector compare = new CompareCompaniesInSector("Banking", from, to);
	private CompareTwoSector compare2 = new CompareTwoSector("Banking", "IT", from, to);
	
	private List<StockPrice> sp1 = new ArrayList<>(Arrays.asList(new StockPrice(1,"500112","BSE",310,from, ex), new StockPrice(2,"500112","BSE",313,from, ex),
    		new StockPrice(3,"500112","BSE",315,from, ex), new StockPrice(4,"500112","BSE",314,from, ex)));
	private List<StockPrice> sp2 = new ArrayList<>(Arrays.asList(new StockPrice(5,"222","BSE",307,from, ex), new StockPrice(6,"222","BSE",313,from, ex),
    		new StockPrice(7,"222","BSE",310,from, ex), new StockPrice(8,"222","BSE",315,from, ex)));
	private List<StockPrice> sp3 = new ArrayList<>(Arrays.asList(new StockPrice(9,"222333","BSE",307,from, ex), new StockPrice(10,"222333","BSE",313,from, ex),
    		new StockPrice(11,"222333","BSE",310,from, ex), new StockPrice(12,"222333","BSE",315,from, ex)));
	private List<StockPrice> sp4 = new ArrayList<>(Arrays.asList(new StockPrice(13,"333222","BSE",307,from, ex), new StockPrice(14,"333222","BSE",313,from, ex),
    		new StockPrice(14,"333222","BSE",310,from, ex), new StockPrice(15,"333222","BSE",315,from, ex)));
	
	@Test
    public void testGetAllMembers() {
        given(sectorRepository.findAll()).willReturn(Arrays.asList(s1));
        List<SectorDto> result = sectorService.getAllSectors();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSectorName()).isEqualTo("Banking");
        verify(sectorRepository, times(1)).findAll();
    }
	
	@Test
	public void getSectorDetailByPeriod_test() {
		given(companyRepository.findBySector("Banking")).willReturn(c1);
		given(stockRepository.findByCompanyCodeAndDateBetween("500112", from, to)).willReturn(sp1);
		given(stockRepository.findByCompanyCodeAndDateBetween("222", from, to)).willReturn(sp2);
		List<CompareCompaniesInSector> result = sectorService.getSectorDetailByPeriod(compare);
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getCompanyName()).isEqualTo("State Bank of India");
		assertThat(result.get(0).getAverageStockPrice()).isEqualTo(313);
	}
	
	@Test
	public void compareTwoSectors_test() {
		given(companyRepository.findBySector("Banking")).willReturn(c1);
		given(companyRepository.findBySector("IT")).willReturn(c2);
		given(stockRepository.findByCompanyCodeAndDateBetween("500112", from, to)).willReturn(sp1);
		given(stockRepository.findByCompanyCodeAndDateBetween("222", from, to)).willReturn(sp2);
		given(stockRepository.findByCompanyCodeAndDateBetween("222333", from, to)).willReturn(sp3);
		given(stockRepository.findByCompanyCodeAndDateBetween("333222", from, to)).willReturn(sp4);
		List<CompareCompaniesInSector> result = sectorService.compareTwoSectors(compare2);
		assertThat(result).hasSize(4);
		assertThat(result.get(0).getAverageStockPrice()).isEqualTo(313);
	}
}
