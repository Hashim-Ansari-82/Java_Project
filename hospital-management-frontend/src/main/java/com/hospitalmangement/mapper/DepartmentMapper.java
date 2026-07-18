package com.hospitalmangement.mapper;

import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.DepartmentRequestDto;
import com.hospitalmangement.dto.responsedto.DepartmentResponseDto;
import com.hospitalmangement.entity.Department;

@Component
public class DepartmentMapper {

	public Department dtoToEntity(DepartmentRequestDto dto) {
		
		Department department = new Department();
		department.setId(dto.getId());
		department.setName(dto.getName());
		
		return department;
	}
	public DepartmentResponseDto entityToDto(Department dto) {
		
		DepartmentResponseDto responseDto = new DepartmentResponseDto();
		responseDto.setId(dto.getId());
		responseDto.setName(dto.getName());
		
		return responseDto;
	}
}
