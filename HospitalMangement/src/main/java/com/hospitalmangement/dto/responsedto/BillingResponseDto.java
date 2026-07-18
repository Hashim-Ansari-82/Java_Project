package com.hospitalmangement.dto.responsedto;

import lombok.Data;

@Data
public class BillingResponseDto {

	private Integer id;
	private Double amount;
	
    private Integer appointmentId;

	}
