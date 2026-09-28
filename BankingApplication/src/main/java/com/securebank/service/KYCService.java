package com.securebank.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.securebank.dto.kycdto.KYCRejectionRequestDto;
import com.securebank.dto.kycdto.KYCRejectionResponseDto;
import com.securebank.dto.kycdto.KYCRequestDto;
import com.securebank.dto.kycdto.KYCResponseDto;

public interface KYCService {

	KYCResponseDto submitKYCForm(KYCRequestDto dto,Authentication authentication);
	KYCRejectionResponseDto checkMyKyc(String email);
	String rejectKYC(Integer kycId,KYCRejectionRequestDto dto,String managerEmail);
	KYCResponseDto approveKYC(Integer kycId,Authentication authentication);
	List<KYCResponseDto> getAllPendingKYC();
	List<KYCResponseDto> getAllRejectedKYC();
}
