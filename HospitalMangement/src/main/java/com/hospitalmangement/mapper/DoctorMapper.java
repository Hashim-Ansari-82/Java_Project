package com.hospitalmangement.mapper;

import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.DoctorRequestDto;
import com.hospitalmangement.dto.responsedto.DoctorResponseDto;
import com.hospitalmangement.entity.Doctor;

@Component
public class DoctorMapper {

	public Doctor dtoToEntity(DoctorRequestDto dto) {
		
		Doctor doctor = new Doctor();
		doctor.setName(dto.getName());
		doctor.setSpecialization(dto.getSpecialization());
		return doctor;
	}
	public DoctorResponseDto entityToDto(Doctor dto) {
		
		DoctorResponseDto responseDto = new DoctorResponseDto();
		responseDto.setId(dto.getId());
		responseDto.setName(dto.getName());
		responseDto.setSpecialization(dto.getSpecialization());
		responseDto.setDepartmentId(dto.getDepartment().getId());
		responseDto.setDepartmentName(dto.getDepartment().getName());
		
		return responseDto;
	}
}
