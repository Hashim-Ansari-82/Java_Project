package com.securebank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.admindto.AdminUpdateRequestDto;
import com.securebank.dto.admindto.AdminUpdateResponseDto;
import com.securebank.service.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	private final AdminService adminService;

	@GetMapping("/getMyProfile")
	public ResponseEntity<AdminUpdateResponseDto> getMyProfile(Authentication authentication) {

		String email = authentication.getName();

		AdminUpdateResponseDto myProfile = adminService.getMyProfile(email);
		return ResponseEntity.ok().body(myProfile);
	}

	@PutMapping("/updateMyProfile")
	public ResponseEntity<AdminUpdateResponseDto> updateMyProfile(Authentication authentication,
			@Valid @RequestBody AdminUpdateRequestDto dto) {

		String email = authentication.getName();

		AdminUpdateResponseDto updateMyProfile = adminService.updateMyProfile(email, dto);

		return ResponseEntity.ok().body(updateMyProfile);
	}

}
