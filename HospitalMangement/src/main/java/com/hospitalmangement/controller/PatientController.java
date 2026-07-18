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

import com.hospitalmangement.dto.requestdto.PatientRequestDto;
import com.hospitalmangement.dto.responsedto.PatientResponseDto;
import com.hospitalmangement.service.dao.PatientService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

	private final PatientService patientService;

	@PostMapping
	public PatientResponseDto save(@Valid @RequestBody PatientRequestDto dto) {
		return patientService.save(dto);

	}
	@GetMapping()
	public List<PatientResponseDto> getAll(){
		return patientService.getAll(); 
	} 
	
	@GetMapping("/{id}")
	public PatientResponseDto getById(@PathVariable Integer id) {
		
		return patientService.getId(id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		
		patientService.delete(id);
		return "Data Deleted Successfully";
	}
	@PutMapping("/{id}")
	public PatientResponseDto update(@PathVariable Integer id,@RequestBody PatientRequestDto dto) {
		return patientService.update(id, dto);
	}
}
