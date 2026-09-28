package com.securebank.dto.managerdto;

import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ManagerUpdateRequestDto(
		
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
		@NotNull(message = "Can you update role of Manager enter existing or new ")
		RoleName roles,
		
		@NotNull(message = "Enter Manager status")
		UsersStatus status,
		
		 @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters")
		String password)
{

}
