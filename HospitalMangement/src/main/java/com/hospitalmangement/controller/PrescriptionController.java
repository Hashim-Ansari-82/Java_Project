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

import com.hospitalmangement.dto.requestdto.PrescriptionRequestDto;
import com.hospitalmangement.dto.responsedto.PrescriptionResponseDto;
import com.hospitalmangement.service.dao.PrescriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

	private final PrescriptionService service;
	
	@PostMapping
	public PrescriptionResponseDto save(@Valid @RequestBody PrescriptionRequestDto dto) {
		return service.save(dto);
	}
	@GetMapping
	public List<PrescriptionResponseDto> getAll(){
		return service.getAll();
	}
	@GetMapping("/{id}")
	public PrescriptionResponseDto getById(@PathVariable Integer id) {
		return service.getById(id);
	}
	@DeleteMapping("/{id}")
	public String delete(Integer id) {
		service.delete(id);
		return "Deleted Successfully";
	}
	@PutMapping("/{id}")
	public PrescriptionResponseDto update(@PathVariable Integer id,@RequestBody PrescriptionRequestDto dto) {
		return service.update(id, dto);
	}
}
