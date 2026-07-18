package com.hospitalmangement.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentRequestDto {

	@NotNull(message = "Id is Required")
	private Integer id;
	@NotBlank(message = "Required")
	private String name;
}
