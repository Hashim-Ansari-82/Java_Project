package com.securebank.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersUpdateRequestDto {

	@NotBlank(message = "Name is required")
	private String username;

	@NotBlank(message = "Mobile is required")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
	private String mobile;
	
	@NotBlank(message = "Address must be required")
	private String address;
}
