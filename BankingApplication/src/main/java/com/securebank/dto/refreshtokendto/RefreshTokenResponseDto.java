package com.securebank.dto.refreshtokendto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RefreshTokenResponseDto {

	private Integer id;
	private String refreshToken;
	private LocalDate expiryDate;
}
