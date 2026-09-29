package com.securebank.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationExceptionRespDto{

	private Integer status;
	private String message;
	private String error;
	private String path;
	private LocalDateTime timeStamp;
	private Map<String,String> errorFields;

}
