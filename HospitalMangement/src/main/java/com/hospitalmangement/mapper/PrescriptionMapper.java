package com.hospitalmangement.mapper;

import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.PrescriptionRequestDto;
import com.hospitalmangement.dto.responsedto.PrescriptionResponseDto;
import com.hospitalmangement.entity.Prescription;

@Component
public class PrescriptionMapper {

	public Prescription dtoToEntity(PrescriptionRequestDto dto) {
		
		Prescription prescription = new Prescription();
		prescription.setId(dto.getId());
		prescription.setInstruction(dto.getInstruction());
		prescription.setMedicine(dto.getMedicine());
		
		return prescription;
	}
	public PrescriptionResponseDto entityToDto(Prescription dto) {
		
		PrescriptionResponseDto responseDto = new PrescriptionResponseDto();
		responseDto.setId(dto.getId());
		responseDto.setInstruction(dto.getInstruction());
		responseDto.setMedicine(dto.getMedicine());
		
		return responseDto;
	}
}
