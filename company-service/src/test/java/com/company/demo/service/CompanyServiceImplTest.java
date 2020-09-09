package com.company.demo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.company.demo.dao.CompanyRepository;
import com.company.demo.dao.StockPriceRepository;
import com.company.demo.dto.CompanyDto;
import com.company.demo.dto.CompareCompany;
import com.company.demo.dto.StockPriceDto;
import com.company.demo.model.Company;
import com.company.demo.model.StockPrice;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CompanyServiceImplTest {
	
	@InjectMocks
	private CompanyServiceImpl companyService;
	
	@Mock
	private CompanyRepository companyRepository;
	
	@Mock
	private StockPriceRepository stockRepository;
	
	private LocalDate from = LocalDate.of(2020, 8, 1);
	private LocalDate to = LocalDate.of(2020, 8, 4);
	private LocalTime ex = LocalTime.of(9, 30, 0);
	
	private Company c1 = new Company(1,"State Bank of India","1234","A","A,B","BSE","Banking","Nil","500112");
    private Company c2 = new Company(2,"NBI","2314","B","A,B","BSE","Banking","Nil","222");
    private CompareCompany c = new CompareCompany("State Bank of India", "BSE", "NBI", "BSE", from, to);
    private List<StockPrice> s1 = new ArrayList<>(Arrays.asList(new StockPrice(1,"500112","BSE",310,from, ex), new StockPrice(2,"500112","BSE",313,from, ex),
    		new StockPrice(3,"500112","BSE",315,from, ex), new StockPrice(4,"500112","BSE",314,from, ex)));
    private List<StockPrice> s2 = new ArrayList<>(Arrays.asList(new StockPrice(5,"222","BSE",307,from, ex), new StockPrice(6,"222","BSE",313,from, ex),
    		new StockPrice(7,"222","BSE",310,from, ex), new StockPrice(8,"222","BSE",315,from, ex)));
    
    
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);      not needed when @ExtendWith(MockitoExtension.class) is used
//    }
	
	@Test
    public void testGetAllMembers() {
        given(companyRepository.findAll()).willReturn(Arrays.asList(c1, c2));
        List<CompanyDto> result = companyService.getAllCompanies();
        assertThat(result).hasSize(2);
        assertCompanyFields(result.get(0));
        verify(companyRepository, times(1)).findAll();
    }
	
	@Test
	public void testGetCompanyById() {
		given(companyRepository.findById(1)).willReturn(Optional.of(c1));
		CompanyDto result = companyService.getCompanyDetailById(1);
		assertNotNull(result);
		assertCompanyFields(result);
		verify(companyRepository, times(1)).findById(1);
		
	}
	
	@Test
	public void testMatchingCompanies() {
		given(companyRepository.findMatchingCompany("Ba")).willReturn(Arrays.asList(c1));
		List<CompanyDto> result = companyService.getMatchingCompanies("Ba");
		assertThat(result).hasSize(1);
		assertCompanyFields(result.get(0));
		verify(companyRepository, times(1)).findMatchingCompany("Ba");
	}
	
	@Test
	public void testGetStockPriceByCompanies() {
		given(companyRepository.findByCompanyNameAndStockExchangeName("NBI", "BSE")).willReturn(Arrays.asList(c2));
		given(companyRepository.findByCompanyNameAndStockExchangeName("State Bank of India", "BSE")).willReturn(Arrays.asList(c1));
		given(stockRepository.findByCompanyCodeAndDateBetween("500112", LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 4))).willReturn(s1);
		given(stockRepository.findByCompanyCodeAndDateBetween("222", LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 4))).willReturn(s2);
		List<StockPriceDto> stonks = companyService.getStockPriceByCompanies(c);
		assertThat(stonks).hasSize(8);
		
	}
	
	private void assertCompanyFields(CompanyDto companyDto) {
        assertThat(companyDto.getCompanyId()).isInstanceOf(Integer.class);
        assertThat(companyDto.getCompanyId()).isEqualTo(1);
        assertThat(companyDto.getCompanyName()).isEqualTo("State Bank of India");
        assertThat(companyDto.getStockCode()).isEqualTo("500112");
    }

}
