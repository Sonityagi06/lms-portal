package com.hospital.lms_portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ApiError> handleStudentNotFound(StudentNotFoundException ex){
		
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage()
				);
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiError> handleInvalidCredentials(InvalidCredentialsException ex){
		
		ApiError error = new ApiError(
				HttpStatus.UNAUTHORIZED.value(),
				ex.getMessage()
				);
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex){
		
		String errorMsg = ex.getBindingResult()
				.getFieldError()
				.getDefaultMessage();
		
		ApiError error = new ApiError(
				HttpStatus.BAD_REQUEST.value(),
				errorMsg);
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGenericException(Exception ex){
		
		ApiError error = new ApiError(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Something went wrong");
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
