package com.hospitalmangement.service.dao;

import java.util.List;

import com.hospitalmangement.dto.requestdto.PrescriptionRequestDto;
import com.hospitalmangement.dto.responsedto.PrescriptionResponseDto;

public interface PrescriptionService {

	public PrescriptionResponseDto save(PrescriptionRequestDto dto);
	public List<PrescriptionResponseDto> getAll();
	public PrescriptionResponseDto getById(Integer id);
	public void delete(Integer id);
	public PrescriptionResponseDto update(Integer id,PrescriptionRequestDto dto);
}
