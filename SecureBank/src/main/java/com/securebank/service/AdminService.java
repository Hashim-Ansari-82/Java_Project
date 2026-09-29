package com.securebank.service;

import com.securebank.dto.admindto.AdminUpdateRequestDto;
import com.securebank.dto.admindto.AdminUpdateResponseDto;

public interface AdminService {

	AdminUpdateResponseDto getMyProfile(String email);
	AdminUpdateResponseDto updateMyProfile(String email,AdminUpdateRequestDto dto);

}