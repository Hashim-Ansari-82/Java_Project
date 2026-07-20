package com.hospitalmangement.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionRequestDto {

	@NotBlank(message = "Bring The Medicine")
	private String medicine;
	@NotBlank(message = "Two does Daily")
	private String instruction;
	@NotNull
	private Integer appointmentId;
	
}
