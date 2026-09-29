package com.securebank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.kycdto.KYCRejectionRequestDto;
import com.securebank.dto.kycdto.KYCRejectionResponseDto;
import com.securebank.dto.kycdto.KYCRequestDto;
import com.securebank.dto.kycdto.KYCResponseDto;
import com.securebank.service.KYCService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kyc")
public class KycController {

	private final KYCService kycService;

	@PostMapping("/submitkycform")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<KYCResponseDto> submitKycForm(@Valid @RequestBody KYCRequestDto dto,
			Authentication authentication) {

		KYCResponseDto submitKYCForm = kycService.submitKYCForm(dto, authentication);

		return ResponseEntity.ok().body(submitKYCForm);
	}
	
	@GetMapping("/checkMyKycStatus")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<KYCRejectionResponseDto> checkMyKycStatus(Authentication authentication) {
		
		String email = authentication.getName();
		
		 KYCRejectionResponseDto checkMyKyc = kycService.checkMyKyc(email);
		
		return ResponseEntity.ok().body(checkMyKyc);
	}
	
	

	@PatchMapping("/reject/{kycId}/kyc")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<String> rejectKYC(
	        @PathVariable Integer kycId,
	        @Valid @RequestBody KYCRejectionRequestDto dto,
	        Authentication authentication) {

		String email = authentication.getName();
		
	    String result = kycService.rejectKYC(kycId, dto,email);
	    return ResponseEntity.ok(result);
	}

	@PatchMapping("/approved/{kycId}/kyc")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<KYCResponseDto> approveKYC(@PathVariable Integer kycId,Authentication authentication) {

		KYCResponseDto approveKYC = kycService.approveKYC(kycId,authentication);

		return ResponseEntity.ok().body(approveKYC);
	}

	@GetMapping("/getAllPendingKyc")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<List<KYCResponseDto>> getAllPendingKyc() {

		List<KYCResponseDto> allPendingKYC = kycService.getAllPendingKYC();

		return ResponseEntity.ok().body(allPendingKYC);
	}

	@GetMapping("/getAllRejectedKyc")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<List<KYCResponseDto>> getAllRejectedKyc() {

		List<KYCResponseDto> allPendingKYC = kycService.getAllPendingKYC();

		return ResponseEntity.ok().body(allPendingKYC);
	}

}
