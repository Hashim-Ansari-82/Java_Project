package com.hospitalmangement.service.dao;

import java.util.List;

import com.hospitalmangement.dto.requestdto.BillingRequestDto;
import com.hospitalmangement.dto.responsedto.BillingResponseDto;

public interface BillingService {

	public BillingResponseDto save(BillingRequestDto dto);
	public List<BillingResponseDto> getAll();
	public BillingResponseDto getById(Integer id);
	public void delete(Integer id);
	public BillingResponseDto update(Integer id, BillingRequestDto dto);
}
