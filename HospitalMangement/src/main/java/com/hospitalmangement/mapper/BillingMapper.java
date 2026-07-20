package com.hospitalmangement.mapper;

import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.BillingRequestDto;
import com.hospitalmangement.dto.responsedto.BillingResponseDto;
import com.hospitalmangement.entity.Billing;

@Component
public class BillingMapper {

	public Billing dtoToEntity(BillingRequestDto dto) {
		
		Billing billing = new Billing();
		billing.setAmount(dto.getAmount());
		
		return billing;
	}
	public BillingResponseDto entityToDto(Billing dto) {
		
		BillingResponseDto responseDto = new BillingResponseDto();
		
		responseDto.setId(dto.getId());
		responseDto.setAmount(dto.getAmount());
		
		 if (dto.getAppointment() != null) {
	            responseDto.setAppointmentId(
	                dto.getAppointment().getId()
	            );
	        }
		
		return responseDto;
	}
}
