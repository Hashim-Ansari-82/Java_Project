package com.securebank.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorPage> resourceNotFoundException(ResourceNotFoundException ex,HttpServletRequest servlet){
		
		ErrorPage errorPage = new ErrorPage();
		errorPage.setStatus(HttpStatus.NO_CONTENT.value());
		errorPage.setMessage(ex.getMessage());
		errorPage.setPath(servlet.getRequestURI());
		errorPage.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		errorPage.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorPage,HttpStatus.NOT_FOUND);	
	}
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorPage> duplicateResourceException(DuplicateResourceException ex,HttpServletRequest servlet){
		
		ErrorPage errorPage = new ErrorPage();
		errorPage.setStatus(HttpStatus.CONFLICT.value());
		errorPage.setMessage(ex.getMessage());
		errorPage.setPath(servlet.getRequestURI());
		errorPage.setError(HttpStatus.CONFLICT.getReasonPhrase());
		errorPage.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorPage,HttpStatus.CONFLICT);	
	}
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorPage> unauthorizedException(UnauthorizedException ex,HttpServletRequest servlet){
		
		ErrorPage errorPage = new ErrorPage();
		errorPage.setStatus(HttpStatus.UNAUTHORIZED.value());
		errorPage.setMessage(ex.getMessage());
		errorPage.setPath(servlet.getRequestURI());
		errorPage.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		errorPage.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorPage,HttpStatus.UNAUTHORIZED);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionRespDto> methodArgumentNotValidException(
			MethodArgumentNotValidException ex,HttpServletRequest servlet){
		
		Map<String, String> errorFields=new HashMap<>();
		ex.getBindingResult().getFieldErrors().
		forEach(error -> errorFields.put(error.getField(),error.getDefaultMessage()));
		
		ValidationExceptionRespDto errorResp = new ValidationExceptionRespDto();
		
		errorResp.setErrorFields(errorFields);
		errorResp.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResp.setMessage("Argument not Valid exception");
		errorResp.setPath(servlet.getRequestURI());
		errorResp.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorResp.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorResp,HttpStatus.BAD_REQUEST);	
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorPage> defaultExceptionHandler(Exception ex,HttpServletRequest servlet){
		
		ErrorPage errorPage = new ErrorPage();
		errorPage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorPage.setMessage(ex.getMessage());
		errorPage.setPath(servlet.getRequestURI());
		errorPage.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorPage.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorPage,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
