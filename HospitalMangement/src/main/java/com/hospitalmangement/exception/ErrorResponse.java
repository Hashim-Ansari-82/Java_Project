package com.hospitalmangement.exception;

import java.time.LocalDateTime;

import lombok.Setter;

@Setter
public class ErrorResponse {

	public String name;
	public String status;
	public LocalDateTime localDateTime;

	public static void main(String[] args) {
		ErrorResponse errorResponse = new ErrorResponse();
	}
}
