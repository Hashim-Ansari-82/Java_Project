package com.hospitalmangement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospitalmangement.dto.requestdto.BillingRequestDto;
import com.hospitalmangement.dto.responsedto.BillingResponseDto;
import com.hospitalmangement.entity.Appointment;
import com.hospitalmangement.entity.Billing;
import com.hospitalmangement.exception.ResourceNotFoundException;
import com.hospitalmangement.mapper.BillingMapper;
import com.hospitalmangement.repository.AppointmentRepository;
import com.hospitalmangement.repository.BillingRepository;
import com.hospitalmangement.service.dao.BillingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService{

	private final BillingRepository billingRepository;
	private final AppointmentRepository appoiRepository;
	private final BillingMapper billingMapper;
	
	@Override
	public BillingResponseDto save(BillingRequestDto dto) {
		
		Appointment appointment = appoiRepository.findById(dto.getAppointmentId())
				.orElseThrow(() ->new ResourceNotFoundException("No Appointment this id "+dto.getAppointmentId()));
		
		Billing billing = billingMapper.dtoToEntity(dto);
		billing.setAppointment(appointment);
		
		Billing save = billingRepository.save(billing);
		
		return billingMapper.entityToDto(save);
	}

	@Override
	public List<BillingResponseDto> getAll() {
		
		List<Billing> billing = billingRepository.findAll();
		List<BillingResponseDto> list = billing.stream().map(billingMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public BillingResponseDto getById(Integer id) {
		
     Billing billing = billingRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Bill not found this id "+id));
	BillingResponseDto entityToDto = billingMapper.entityToDto(billing);	
     
		return entityToDto;
	}

	@Override
	public void delete(Integer id) {
		
		billingRepository.deleteById(id);
		
	}

	@Override
	public BillingResponseDto update(Integer id, BillingRequestDto dto) {
		
		Billing exBill = billingRepository
		.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bill not Found this id "+id));
		
		exBill.setId(dto.getId());
		exBill.setAmount(dto.getAmount());
		
		Billing save = billingRepository.save(exBill);
		BillingResponseDto entityToDto = billingMapper.entityToDto(save);
		
		return entityToDto;
	} 
}
