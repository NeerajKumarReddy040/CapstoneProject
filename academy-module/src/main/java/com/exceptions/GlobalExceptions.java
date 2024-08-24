package com.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptions {
	

	@ExceptionHandler(CustomException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleCustomException(CustomException ex){
		List<String> details = new ArrayList<>();
		details.add("cause: "+ex.getMessage());
		ErrorResponse error = new ErrorResponse("Data Not Available", details);
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);		
	
	}
	


}
