package com.company.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.dto.IpoDto;
import com.company.demo.service.IpoService;

@RestController
@RequestMapping("/company-ws/ipo")
@CrossOrigin(origins = "*")
public class IpoController {
	
	@Autowired
	private IpoService ipoService;
	
	@PostMapping("/addIpo")
	public ResponseEntity<String> addNewIpo(@RequestBody IpoDto ipoDto){
		ipoService.addOrUpdateIpo(ipoDto);
		return new ResponseEntity<>("IpoDto Added Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/updateIpo")
	public ResponseEntity<String> updateIpo(@RequestBody IpoDto ipoDto){
		ipoService.addOrUpdateIpo(ipoDto);
		return new ResponseEntity<>("IpoDto Updated Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/viewIpo")
	public ResponseEntity<List<IpoDto>> viewIposInOrder(){
		return new ResponseEntity<>(ipoService.viewIposInChronoOrder(), HttpStatus.OK);
	}
	
	@GetMapping("/getIpoById/{id}")
	public ResponseEntity<IpoDto> getIpoById(@PathVariable Integer id){
		return new ResponseEntity<>(ipoService.getIpoById(id), HttpStatus.OK);
	}

}
