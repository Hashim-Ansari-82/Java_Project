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

import com.hospitalmangement.dto.requestdto.DepartmentRequestDto;
import com.hospitalmangement.dto.responsedto.DepartmentResponseDto;
import com.hospitalmangement.service.dao.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService service;
	
	@PostMapping
	public DepartmentResponseDto save(@Valid @RequestBody DepartmentRequestDto dto) {

		return service.save(dto);
	}
	@GetMapping
	public List<DepartmentResponseDto> getAll(){
		 
		return service.getAll();
		
	}
	@GetMapping("/{id}")
	public DepartmentResponseDto getById(@PathVariable Integer id) {
		
		return service.getById(id);
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
	
		service.delete(id);
		
		return "Entry deleted successfully";
	}
	@PutMapping("/{id}")
	public DepartmentResponseDto update(@PathVariable Integer id, @RequestBody DepartmentRequestDto dto) {
		
		return service.update(id, dto);
	}
}
