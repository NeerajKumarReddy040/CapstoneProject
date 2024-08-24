package com.exceptions;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(ConstraintViolationException.class)    // @notempty @size 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
			
	List<String> details = new ArrayList<>();
	ex.getConstraintViolations().forEach(violation -> {
		String propertyPath = violation.getPropertyPath().toString();
		String propertyName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
		String errorMessage = violation.getMessage();
				
		details.add(propertyName + " | " + errorMessage);

	});
	ErrorResponse error = new ErrorResponse("Validation Failed", details);
			
	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleCustomException(CustomException ex){
		List<String> details = new ArrayList<>();
		details.add("cause: "+ex.getMessage());
		ErrorResponse error = new ErrorResponse("Data Not Available", details);
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);		
	
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
			List<String> details = new ArrayList<>();
			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				details.add(fieldName + "|" + errorMessage);
			});
			ErrorResponse error = new ErrorResponse("Validation Failed", details);
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

}
