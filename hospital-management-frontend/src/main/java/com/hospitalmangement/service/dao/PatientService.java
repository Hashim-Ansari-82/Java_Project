package com.hospitalmangement.service.dao;

import java.util.List;

import com.hospitalmangement.dto.requestdto.PatientRequestDto;
import com.hospitalmangement.dto.responsedto.PatientResponseDto;

public interface PatientService {
  
	public PatientResponseDto save(PatientRequestDto dto);
	public List<PatientResponseDto> getAll();
	public PatientResponseDto getId(Integer id);
	public void delete(Integer id);  
	public PatientResponseDto update(Integer id,PatientRequestDto dto);
}
