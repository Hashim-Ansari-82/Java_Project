package com.hospitalmangement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospitalmangement.dto.requestdto.PrescriptionRequestDto;
import com.hospitalmangement.dto.responsedto.PrescriptionResponseDto;
import com.hospitalmangement.entity.Appointment;
import com.hospitalmangement.entity.Prescription;
import com.hospitalmangement.exception.ResourceNotFoundException;
import com.hospitalmangement.mapper.PrescriptionMapper;
import com.hospitalmangement.repository.AppointmentRepository;
import com.hospitalmangement.repository.PrescriptionRepository;
import com.hospitalmangement.service.dao.PrescriptionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescriptonServiceImpl implements PrescriptionService {

	private final PrescriptionRepository repository;
	private final AppointmentRepository appoiRepository;
	private final PrescriptionMapper mapper;
	
	@Override
	public PrescriptionResponseDto save(PrescriptionRequestDto dto) {
		
		Appointment appointment = appoiRepository.findById(dto.getAppointmentId())
		.orElseThrow(() ->new ResourceNotFoundException("No Appointment this id "+dto.getAppointmentId()));

        Prescription prescription = mapper.dtoToEntity(dto);
         
        prescription.setAppointment(appointment);  
        
		Prescription save = repository.save(prescription);
        
		return mapper.entityToDto(save);
	}
	@Override
	public List<PrescriptionResponseDto> getAll() {
		
		List<Prescription> all = repository.findAll();
		List<PrescriptionResponseDto> list = all.stream().map(mapper::entityToDto).toList();
		
		return list;
	}
	@Override
	public PrescriptionResponseDto getById(Integer id) {
		
		Prescription orElseThrow = repository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("No Prescription this id "+id));
		PrescriptionResponseDto entityToDto = mapper.entityToDto(orElseThrow);
		
		return entityToDto;
	}
	@Override
	public void delete(Integer id) {
		
		repository.deleteById(id);
		
	}
	@Override
	public PrescriptionResponseDto update(Integer id, PrescriptionRequestDto dto) {
		
		Prescription exPrescription = repository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("No Prescription this id "+id));
		
		exPrescription.setInstruction(dto.getInstruction());
		exPrescription.setMedicine(dto.getMedicine());
		
		Prescription save = repository.save(exPrescription);
		PrescriptionResponseDto entityToDto = mapper.entityToDto(save);
		
		return entityToDto;
	}
}
