package com.hospitalmangement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospitalmangement.dto.requestdto.DoctorRequestDto;
import com.hospitalmangement.dto.responsedto.DoctorResponseDto;
import com.hospitalmangement.entity.Department;
import com.hospitalmangement.entity.Doctor;
import com.hospitalmangement.exception.ResourceNotFoundException;
import com.hospitalmangement.mapper.DoctorMapper;
import com.hospitalmangement.repository.DepartmentRepository;
import com.hospitalmangement.repository.DoctorRepository;
import com.hospitalmangement.service.dao.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

	private final DoctorRepository doctorRepository;
	private final DoctorMapper doctorMapper;
	private final DepartmentRepository departmentRepository;
	
	@Override
	public DoctorResponseDto save(DoctorRequestDto dto) {
		
		Department department = departmentRepository.findById(dto.getDepartmentId())
		.orElseThrow(() -> new ResourceNotFoundException("Department not found"));
		
		Doctor doctor = doctorMapper.dtoToEntity(dto);
		doctor.setDepartment(department);
		Doctor save = doctorRepository.save(doctor);
		 
		return doctorMapper.entityToDto(save);
	}

	@Override
	public List<DoctorResponseDto> getAll() {
		
		List<Doctor> doctor = doctorRepository.findAll();
		List<DoctorResponseDto> list = doctor.stream().map(doctorMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public DoctorResponseDto getId(Integer id) {
		
		Doctor doctor = doctorRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Doctor not Found by given  id "+id)); 
		 
		return doctorMapper.entityToDto(doctor);
	}

	@Override
	public void delete(Integer id) {
		
		doctorRepository.deleteById(id);
		
	}

	@Override
	public DoctorResponseDto update(Integer id, DoctorRequestDto dto) {
		
		Doctor exDoctor = doctorRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Doctor not found by this id "+id));
		
		exDoctor.setId(dto.getId());
		exDoctor.setName(dto.getName());
		exDoctor.setSpecialization(dto.getSpecialization());
		
		Doctor save = doctorRepository.save(exDoctor);
		 
		return doctorMapper.entityToDto(save);
	}

}
