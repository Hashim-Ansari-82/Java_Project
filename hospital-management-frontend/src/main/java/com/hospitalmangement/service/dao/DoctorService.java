package com.hospitalmangement.service.dao;

import java.util.List;

import com.hospitalmangement.dto.requestdto.DoctorRequestDto;
import com.hospitalmangement.dto.responsedto.DoctorResponseDto;

public interface DoctorService {

	public DoctorResponseDto save(DoctorRequestDto dto);
	public List<DoctorResponseDto> getAll();
	public DoctorResponseDto getId(Integer id);
	public void delete(Integer id);
	public DoctorResponseDto update(Integer id, DoctorRequestDto dto);
}
 