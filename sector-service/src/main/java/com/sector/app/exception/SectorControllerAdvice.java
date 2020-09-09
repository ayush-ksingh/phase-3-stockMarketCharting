package com.sector.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SectorControllerAdvice {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomResponse> handleComparisonNotPossibleException(CustomException ex){
		CustomResponse error = new CustomResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

}
