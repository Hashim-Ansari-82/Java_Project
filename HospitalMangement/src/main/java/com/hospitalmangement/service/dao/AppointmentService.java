package com.hospitalmangement.service.dao;

import java.util.List;

import com.hospitalmangement.dto.requestdto.AppointmentRequestDto;
import com.hospitalmangement.dto.responsedto.AppointmentResponseDto;

public interface AppointmentService {

	public AppointmentResponseDto save(AppointmentRequestDto dto);
	public List<AppointmentResponseDto> getAll();
	public AppointmentResponseDto getById(Integer id);
	public void delete(Integer id);
	public AppointmentResponseDto update(Integer id,AppointmentRequestDto dto);
}
