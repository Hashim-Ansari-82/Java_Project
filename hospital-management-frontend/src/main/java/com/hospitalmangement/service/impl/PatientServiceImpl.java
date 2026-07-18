package com.hospitalmangement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospitalmangement.dto.requestdto.PatientRequestDto;
import com.hospitalmangement.dto.responsedto.PatientResponseDto;
import com.hospitalmangement.entity.Patient;
import com.hospitalmangement.exception.ResourceNotFoundException;
import com.hospitalmangement.mapper.PatientMapper;
import com.hospitalmangement.repository.PatientRepository;
import com.hospitalmangement.service.dao.PatientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;

	@Override
	public PatientResponseDto save(PatientRequestDto dto) {

		Patient dtoToEntity = patientMapper.dtoToEntity(dto);
		Patient save = patientRepository.save(dtoToEntity);
		PatientResponseDto entityToDto = patientMapper.entityToDto(save);

		return entityToDto;

	}

	@Override
	public List<PatientResponseDto> getAll() {

		List<Patient> patient = patientRepository.findAll();
		List<PatientResponseDto> list = patient.stream().map(patientMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public PatientResponseDto getId(Integer id) {

		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id : " + id));
		PatientResponseDto entityToDto = patientMapper.entityToDto(patient);

		return entityToDto;
	}

	@Override
	public void delete(Integer id) {

		patientRepository.deleteById(id);
	}

	@Override
	public PatientResponseDto update(Integer id, PatientRequestDto dto) {

		Patient existingPatient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id : " + id));

		existingPatient.setName(dto.getName());
		existingPatient.setAge(dto.getAge());
		existingPatient.setGender(dto.getGender());
		existingPatient.setMobile(dto.getMobile());

		Patient update = patientRepository.save(existingPatient);
		PatientResponseDto responseDto = patientMapper.entityToDto(update);

		return responseDto;
	}

}
