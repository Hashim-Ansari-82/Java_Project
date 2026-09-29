package com.securebank.mapper;


import com.securebank.dto.kycdto.KYCRejectionResponseDto;
import com.securebank.dto.kycdto.KYCRequestDto;
import com.securebank.dto.kycdto.KYCResponseDto;
import com.securebank.entity.KYC;
import com.securebank.entity.User;

public interface KYCMapper {

	KYCResponseDto entityToDto(KYC kyc,User user);
    KYCRejectionResponseDto rejectionDto(KYC kyc,User user);
	KYC dtoToEntity(KYCRequestDto dto);

}
