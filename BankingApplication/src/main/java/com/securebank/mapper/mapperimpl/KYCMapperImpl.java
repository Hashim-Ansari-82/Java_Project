package com.securebank.mapper.mapperimpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.securebank.dto.kycdto.KYCRejectionResponseDto;
import com.securebank.dto.kycdto.KYCRequestDto;
import com.securebank.dto.kycdto.KYCResponseDto;
import com.securebank.entity.KYC;
import com.securebank.entity.User;
import com.securebank.mapper.KYCMapper;

@Component
public class KYCMapperImpl implements KYCMapper {

	@Override
	public KYCResponseDto entityToDto(KYC kyc,User user) {

		KYCResponseDto responseDto = new KYCResponseDto();

		responseDto.setId(kyc.getId());
		responseDto.setUserId(user.getId());
		responseDto.setUsername(user.getUsername());
		responseDto.setEmail(user.getEmail());
		responseDto.setAge(kyc.getAge());
		responseDto.setAddress(kyc.getAddress());
		responseDto.setAadharNumber(maskedAadhar(kyc.getAadharNumber()));
		responseDto.setPanCardNumber(maskPan(kyc.getPanCardNumber()));
		responseDto.setFatherName(kyc.getFatherName());
		responseDto.setCity(kyc.getCity());
		responseDto.setPincode(kyc.getPincode());
		responseDto.setDateOfBirth(kyc.getDateOfBirth());
		responseDto.setGender(kyc.getGender());
		responseDto.setState(kyc.getState());
		responseDto.setStatus(kyc.getStatus());
		responseDto.setSubmittedAt(LocalDateTime.now());
		

		return responseDto;
	}
	

	@Override
	public KYCRejectionResponseDto rejectionDto(KYC kyc,User user) {
		
		KYCRejectionResponseDto responseDto = new KYCRejectionResponseDto();
		
		responseDto.setStatus(kyc.getStatus());
		responseDto.setRejectionReason(kyc.getRejectionReason());
		responseDto.setRejectionRemarks(kyc.getRejectionRemarks());
		responseDto.setKycId(kyc.getId());
		responseDto.setUserId(user.getId());
		responseDto.setUsername(user.getUsername());
		responseDto.setUser_Email(user.getEmail());
		
		return responseDto;
	}

	@Override
	public KYC dtoToEntity(KYCRequestDto dto) {

		System.out.println(dto.panCardNumber());

		KYC kyc = new KYC();
		kyc.setAadharNumber(dto.aadharNumber());
		kyc.setPanCardNumber(dto.panCardNumber().toUpperCase());
		kyc.setDateOfBirth(dto.dateOfBirth());
		kyc.setCity(dto.city());
		kyc.setGender(dto.gender());
		kyc.setAddress(dto.address());
		kyc.setCity(dto.city());
		kyc.setPincode(dto.pincode());
		kyc.setState(dto.state());
		kyc.setFatherName(dto.fatherName());

		return kyc;
	}

	private String maskedAadhar(String aadhar) {

		if (aadhar == null || aadhar.length() != 12) {
			return null;
		}

		return "XXXX XXXX " + aadhar.substring(8);
	}

	private String maskPan(String pan) {

		if (pan == null || pan.length() != 10) {
			return null;
		}

		return pan.substring(0, 5) + "****" + pan.substring(9);
	}


}
