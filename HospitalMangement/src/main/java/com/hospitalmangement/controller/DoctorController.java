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

import com.hospitalmangement.dto.requestdto.DoctorRequestDto;
import com.hospitalmangement.dto.responsedto.DoctorResponseDto;
import com.hospitalmangement.service.dao.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

	private final DoctorService doctorService;
	
	@PostMapping
	public DoctorResponseDto save(@Valid @RequestBody DoctorRequestDto dto) {
		
		return doctorService.save(dto);
	}
	
	@GetMapping
	public List<DoctorResponseDto> getAll(){
		return doctorService.getAll();
	}
	@GetMapping("/{id}")
	public DoctorResponseDto getById(@PathVariable Integer id) {
		
		return doctorService.getId(id);
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		
		doctorService.delete(id);
		
		return "Doctor deleted Successfully";
	}
	@PutMapping("/{id}")
	public DoctorResponseDto update(@PathVariable Integer id,@RequestBody DoctorRequestDto dto) {
		return doctorService.update(id, dto);
	}
}
