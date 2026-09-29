package com.securebank.dto.refreshtokendto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccessTokenResponseDto {

	private Integer id;
	private String accessToken;
	private LocalDate expiryDate;
}
