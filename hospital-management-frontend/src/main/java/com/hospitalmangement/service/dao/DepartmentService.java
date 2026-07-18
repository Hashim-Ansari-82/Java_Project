package com.hospitalmangement.service.dao;

import java.util.List;

import com.hospitalmangement.dto.requestdto.DepartmentRequestDto;
import com.hospitalmangement.dto.responsedto.DepartmentResponseDto;

public interface DepartmentService {

	public DepartmentResponseDto save(DepartmentRequestDto dto);
	public List<DepartmentResponseDto> getAll();
	public DepartmentResponseDto getById(Integer id);
	public void delete(Integer id);
	public DepartmentResponseDto update(Integer id,DepartmentRequestDto dto);
}
