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
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found : " + dto.getDoctorId()));

		Patient patient = patientRepository.findById(dto.getPatientId())
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found : " + dto.getPatientId()));

		Appointment appointment = mapper.dtoToEntity(dto);

		appointment.setDoctor(doctor);
		appointment.setPatient(patient);

		Appointment saved = repository.save(appointment);

		return mapper.entityToDto(saved);

	}

	@Override
	public List<AppointmentResponseDto> getAll() {

		return repository.findAll().stream().map(mapper::entityToDto).toList();

	}

	@Override
	public AppointmentResponseDto getById(Integer id) {

		Appointment appointment = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found : " + id));

		return mapper.entityToDto(appointment);

	}

	@Override
	public AppointmentResponseDto update(Integer id, AppointmentRequestDto dto) {

		Appointment appointment = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment not found : " + id));

		Doctor doctor = doctorRepository.findById(dto.getDoctorId())
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

		Patient patient = patientRepository.findById(dto.getPatientId())
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

		appointment.setDate(dto.getDate());

		appointment.setTime(dto.getTime());

		appointment.setStatus(dto.getStatus());

		appointment.setDoctor(doctor);

		appointment.setPatient(patient);

		Appointment updated = repository.save(appointment);

		return mapper.entityToDto(updated);

	}

	@Override
	public void delete(Integer id) {

		repository.deleteById(id);

	}

}