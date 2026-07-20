package com.hospitalmangement.dto.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillingRequestDto {

	@NotNull(message = "Pay Bill")
	private Double amount;
	private Integer appointmentId;
}
