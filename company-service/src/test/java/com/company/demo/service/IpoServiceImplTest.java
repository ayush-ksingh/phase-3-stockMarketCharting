package com.company.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.company.demo.dao.IpoRepository;
import com.company.demo.dto.IpoDto;
import com.company.demo.model.Ipo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class IpoServiceImplTest {
	
	@InjectMocks
	private IpoServiceImpl ipoService;
	
	@Mock
	private IpoRepository ipoRepository;
	
	private LocalDateTime l1 = LocalDateTime.of(2020, 8, 1, 9, 30, 0);
	private LocalDateTime l2 = LocalDateTime.of(2020, 8, 2, 9, 30, 0);
	private LocalDateTime l3 = LocalDateTime.of(2020, 8, 3, 9, 30, 0);
	Ipo i = new Ipo(3, "NBI", "BSE", Float.parseFloat("229.9"), 2987, l3, "Nil");
	
	private List<Ipo> i1 = new ArrayList<>(Arrays.asList(new Ipo(1, "NBI", "BSE", Float.parseFloat("231.4"), 3231, l1, "Nil"), new Ipo(2, "NBI", "BSE", Float.parseFloat("230.5"), 3115, l2, "Nil")));
	
	@Test
	public void viewIposInOrder_test() {
		given(ipoRepository.findAll()).willReturn(i1);
		List<IpoDto> result = ipoService.viewIposInChronoOrder();
		assertThat(result).hasSize(2);
		verify(ipoRepository, times(1)).findAll();
	}
	
	@Test
	public void getIpoById_test() {
		given(ipoRepository.findById(3)).willReturn(Optional.of(i));
		IpoDto result = ipoService.getIpoById(3);
		assertIpoFields(result);
		verify(ipoRepository, times(1)).findById(3);
	}

	private void assertIpoFields(IpoDto result) {
		assertThat(result.getCompanyName()).isEqualTo("NBI");
		assertThat(result.getNumberOfShares()).isEqualTo(2987);
		assertThat(result.getPricePerShare()).isEqualTo(229.9f);
	}

}
