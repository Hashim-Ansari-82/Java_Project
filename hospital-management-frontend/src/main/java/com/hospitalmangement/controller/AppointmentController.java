package com.hospitalmangement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmangement.dto.requestdto.AppointmentRequestDto;
import com.hospitalmangement.dto.responsedto.AppointmentResponseDto;
import com.hospitalmangement.service.dao.AppointmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentService service;
	
	@PostMapping
	public AppointmentResponseDto save(@Valid @RequestBody AppointmentRequestDto dto) {
		return service.save(dto);
	}
	@GetMapping
	public List<AppointmentResponseDto> getAll(){
		
		return service.getAll();
	}
	@GetMapping("/{id}")
	public AppointmentResponseDto getById(@PathVariable Integer id) {
		return service.getById(id);
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "Deleted Successfully";
	}
	@PutMapping("/{id}")
	public AppointmentResponseDto update(@PathVariable Integer id,@Valid @RequestBody AppointmentRequestDto dto) {
		return service.update(id, dto);
	}
}
