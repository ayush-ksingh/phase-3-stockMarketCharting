package com.company.demo.service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.demo.dao.IpoRepository;
import com.company.demo.dto.CompanyDto;
import com.company.demo.dto.IpoDto;
import com.company.demo.model.Ipo;

@Service
public class IpoServiceImpl implements IpoService{
	
	private IpoRepository ipoRepository;

	public IpoServiceImpl(IpoRepository ipoRepository) {
		super();
		this.ipoRepository = ipoRepository;
	}
	
// Admin use cases
	
	@Transactional
	public void addOrUpdateIpo(IpoDto ipoDto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		mapper.map(ipoDto, Ipo.class);
	}
	
	@Transactional
	public IpoDto getIpoById(Integer id) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<Ipo> ipo = ipoRepository.findById(id);
		if (!ipo.isPresent()) {
			return null;
		}
		return mapper.map(ipo.get(), IpoDto.class);
	}
	
// User use cases
	
	@Transactional
	public List<IpoDto> viewIposInChronoOrder(){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Type listType = new TypeToken<List<IpoDto>>(){}.getType();
		List<Ipo> list = ipoRepository.findAll();
		Collections.sort(list, (Ipo arg0, Ipo arg1) -> arg0.getOpenDateTime().compareTo(arg1.getOpenDateTime()));
		return mapper.map(list, listType);
	}
	
}
