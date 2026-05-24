package com.searchapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=NullPointerException.class)
	public String nullPointerException(Model model) {
		model.addAttribute("msg","Null Pointer Exception has been occured !!");
		return "nullPage";
	}
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=NumberFormatException.class)
	public String numberFormatException(Model model) {
		model.addAttribute("msg","Number Format Exception has been occured !!");
		return "nullPage";
	}
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandlerGeneric(Model model) {
		model.addAttribute("msg","Exception has been occured !!");
		return "nullPage";
	}
}
