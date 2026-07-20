package com.hospitalmangement.dto.requestdto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatientRequestDto {

	    @NotBlank(message = "Name is required")
	    private String name;

	    @NotNull(message = "Age is required")
	    @Min(value = 1, message = "Age must be greater than 0")
	    @Max(value = 120, message = "Age cannot exceed 120")
	    private Integer age;

	    @NotBlank(message = "Gender is required")
	    @Pattern(
	        regexp = "^(Male|Female|Other)$",
	        message = "Gender must be Male, Female or Other"
	    )
	    private String gender;

	    @NotBlank(message = "Mobile number is required")
	    @Pattern(
	        regexp = "^[6-9]\\d{9}$",
	        message = "Please enter proper formate of number"
	    )
	    private String mobile;

}
