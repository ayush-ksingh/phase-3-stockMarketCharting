package com.company.demo.service;

import java.util.List;

import com.company.demo.dto.IpoDto;

public interface IpoService {
	
	void addOrUpdateIpo(IpoDto ipoDto);
	List<IpoDto> viewIposInChronoOrder();
	IpoDto getIpoById(Integer id);
}
