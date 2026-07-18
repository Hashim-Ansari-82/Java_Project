package com.hospitalmangement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospitalmangement.dto.requestdto.DepartmentRequestDto;
import com.hospitalmangement.dto.responsedto.DepartmentResponseDto;
import com.hospitalmangement.entity.Department;
import com.hospitalmangement.exception.ResourceNotFoundException;
import com.hospitalmangement.mapper.DepartmentMapper;
import com.hospitalmangement.repository.DepartmentRepository;
import com.hospitalmangement.service.dao.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceimpl implements DepartmentService{

	private final DepartmentRepository repository;
	private final DepartmentMapper mapper;
	
	@Override
	public DepartmentResponseDto save(DepartmentRequestDto dto) {
		
		Department dtoToEntity = mapper.dtoToEntity(dto);
		Department save = repository.save(dtoToEntity);
		DepartmentResponseDto entityToDto = mapper.entityToDto(save);
		
		return entityToDto;
	}
	@Override
	public List<DepartmentResponseDto> getAll() {
		
		List<Department> all = repository.findAll();
		List<DepartmentResponseDto> list = all.stream().map(mapper::entityToDto).toList();
		
		return list;
	}
	@Override
	public DepartmentResponseDto getById(Integer id) {
	
		Department orElseThrow = repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Don't yet Any department this id "+id));
		DepartmentResponseDto entityToDto = mapper.entityToDto(orElseThrow);
		
		return entityToDto; 
	}
	@Override
	public void delete(Integer id) {
		
		repository.deleteById(id);
		
	}
	@Override
	public DepartmentResponseDto update(Integer id, DepartmentRequestDto dto) {
		Department exDepartment = repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Don't Yet Any Department this id "+id));
		
        exDepartment.setName(dto.getName());
        
        Department save = repository.save(exDepartment);
		    
        
		return  mapper.entityToDto(save);
	}
}
