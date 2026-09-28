package com.securebank.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorPage {

	private Integer status;
	private String message;
	private String error;
	private String path;
	private LocalDateTime timeStamp;
}
