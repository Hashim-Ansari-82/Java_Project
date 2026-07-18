package com.hospitalmangement.mapper;

import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.PatientRequestDto;
import com.hospitalmangement.dto.responsedto.PatientResponseDto;
import com.hospitalmangement.entity.Patient;

@Component
public class PatientMapper {
	
	public Patient dtoToEntity(PatientRequestDto dto) {
		
		Patient patient = new Patient();
		patient.setId(dto.getId());
		patient.setName(dto.getName());
		patient.setAge(dto.getAge());
		patient.setGender(dto.getGender());
		patient.setMobile(dto.getMobile());
		
		return patient;
	}
	public PatientResponseDto entityToDto(Patient dto) {
		
		PatientResponseDto responseDto = new PatientResponseDto();
		responseDto.setId(dto.getId());
		responseDto.setName(dto.getName());
		responseDto.setAge(dto.getAge());
		responseDto.setGender(dto.getGender());
		responseDto.setMobile(dto.getMobile());
		
		return responseDto;
	}
}
