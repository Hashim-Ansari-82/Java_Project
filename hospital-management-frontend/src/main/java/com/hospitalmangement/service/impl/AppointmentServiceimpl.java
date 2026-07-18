package com.hospitalmangement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospitalmangement.dto.requestdto.AppointmentRequestDto;
import com.hospitalmangement.dto.responsedto.AppointmentResponseDto;
import com.hospitalmangement.entity.Appointment;
import com.hospitalmangement.entity.Doctor;
import com.hospitalmangement.entity.Patient;
import com.hospitalmangement.exception.ResourceNotFoundException;
import com.hospitalmangement.mapper.AppointmentMapper;
import com.hospitalmangement.repository.AppointmentRepository;
import com.hospitalmangement.repository.DoctorRepository;
import com.hospitalmangement.repository.PatientRepository;
import com.hospitalmangement.service.dao.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceimpl implements AppointmentService {

	private final AppointmentRepository repository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final AppointmentMapper mapper;

	@Override
	public AppointmentResponseDto save(AppointmentRequestDto dto) {
		
		Doctor doctor = doctorRepository.findById(dto.getDoctorId())
		.orElseThrow(() -> new ResourceNotFoundException("Doctor not found this id "+dto.getDoctorId()));

		Patient patient = patientRepository.findById(dto.getPatientId())
		.orElseThrow(() -> new ResourceNotFoundException("No patient found this id "+dto.getPatientId()));
		
		Appointment appointment = mapper.dtoToEntity(dto);
		
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		
		Appointment save = repository.save(appointment);

		return mapper.entityToDto(save);
	}

	@Override
	public List<AppointmentResponseDto> getAll() {

		List<Appointment> all = repository.findAll();
		List<AppointmentResponseDto> list = all.stream().map(mapper::entityToDto).toList();

		return list;
	}

	@Override
	public AppointmentResponseDto getById(Integer id) {

		Appointment orElseThrow = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Appointment on this id " + id));
		AppointmentResponseDto entityToDto = mapper.entityToDto(orElseThrow);
		return entityToDto;
	}

	@Override
	public void delete(Integer id) {

		repository.deleteById(id);

	}

	@Override
	public AppointmentResponseDto update(Integer id, AppointmentRequestDto dto) {

		Appointment exAppointment = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Appointment on this Id " + id));

		exAppointment.setId(dto.getId());
		exAppointment.setStatus(dto.getStatus());
		exAppointment.setDate(dto.getDate());
		exAppointment.setTime(dto.getTime());

		Appointment save = repository.save(exAppointment);
		AppointmentResponseDto entityToDto = mapper.entityToDto(save);

		return entityToDto;
	}

}
