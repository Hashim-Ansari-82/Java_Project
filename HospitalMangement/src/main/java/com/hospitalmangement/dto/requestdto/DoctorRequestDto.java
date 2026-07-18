package com.hospitalmangement.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorRequestDto {

	@NotNull(message = "Required Id")
	private Integer id;
	@NotBlank(message = "Please Enter Name")
	private String name ;
	@NotBlank(message = "Required")
	private String specialization;
	@NotNull(message = "Doctor id required")
	private Integer departmentId;
}
