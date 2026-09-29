package com.securebank.dto.managerdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ManagerRequestDto(

		@NotBlank(message = "Username Must be Required")
		String username,
		@NotBlank(message = "Address Must be Required")
		String address, 
		@NotBlank(message = "Address Must be Required")
		@Email
		String email,
		@NotBlank(message = "Mobile is required")
	    @Pattern(
	        regexp = "^[6-9]\\d{9}$",
	        message = "Invalid mobile number"
	    )
		String mobile,
		 @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters")
		String password) {

}
