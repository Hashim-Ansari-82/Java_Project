package com.hospitalmangement.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillingRequestDto {

	@NotNull(message = "Id is required")	
	private Integer id;
	@NotBlank(message = "Pay Bill")
	private Double amount;
	
    @NotBlank(message = "Appointment Id must")
    private Integer appointmentId;

}
