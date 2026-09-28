package com.securebank.dto.kycdto;

import java.time.LocalDate;

import com.securebank.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record KYCRequestDto(

		@NotBlank(message = "Enter your Aadhar number")
		@Pattern(
		    regexp = "^[0-9]{12}$",
		    message = "Aadhar number must contain exactly 12 digits"
		)
		String aadharNumber,

		@NotBlank(message = "Enter your Pan Card number")
		@Pattern(
			    regexp = "^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$",
			    message = "Enter valid PAN number"
			)
		String panCardNumber,

		@Past(message = "Please Choose Past date")
		LocalDate dateOfBirth,

		@NotBlank(message = "Enter Your Father name")
		String fatherName,

		@NotNull(message = "Please Select Gender")
		Gender gender,

		@NotBlank(message = "Please Enter Your Address")
		String address,

		@NotBlank(message = "Please Enter Your City")
		String city,

		@NotBlank(message = "Please Enter Your State")
		String state,

		@NotNull(message = "Enter your pincode")
		Integer pincode){

}
