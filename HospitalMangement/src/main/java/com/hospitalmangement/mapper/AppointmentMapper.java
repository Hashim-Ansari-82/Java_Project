package com.hospitalmangement.mapper;

import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.AppointmentRequestDto;
import com.hospitalmangement.dto.responsedto.AppointmentResponseDto;
import com.hospitalmangement.entity.Appointment;

@Component
public class AppointmentMapper {

	public Appointment dtoToEntity(AppointmentRequestDto dto) {
		
		Appointment appointment = new Appointment();
		appointment.setId(dto.getId());
		appointment.setStatus(dto.getStatus());
		appointment.setDate(dto.getDate());
		appointment.setTime(dto.getTime());
		
		return appointment;
	}
	public AppointmentResponseDto entityToDto(Appointment dto) {
		
		AppointmentResponseDto responseDto = new AppointmentResponseDto();
		responseDto.setId(dto.getId());
		responseDto.setStatus(dto.getStatus());
		responseDto.setDate(dto.getDate());
		responseDto.setTime(dto.getTime());
		
		return responseDto;
	}
}
