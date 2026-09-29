package com.securebank.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDto(
		
		@NotBlank(message = "Name is required")
	    String username,

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email")
	    String email,

	    @NotBlank(message = "Password is required")
	    @Size(min = 8, max = 20,
	          message = "Password must be between 8 and 20 characters")
	    @Pattern(
	        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$",
	        message = "Password must contain at least one uppercase, one lowercase, one digit and one special character"
	    )
	    String password,

	    @NotBlank(message = "Address Must be Enter")
	    String address,
	    
	    @NotBlank(message = "Mobile is required")
	    @Pattern(
	        regexp = "^[6-9]\\d{9}$",
	        message = "Invalid mobile number"
	    )
	    String mobile
		){

}
