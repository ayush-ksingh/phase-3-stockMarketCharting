package com.company.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyControllerAdvice {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomResponse> handleNotFoundException(CustomException ex){
		CustomResponse error = new CustomResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}

}
