package com.securebank.service;

import java.util.List;

import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.managerdto.ManagerResponseDto;
import com.securebank.dto.managerdto.ManagerUpdateRequestDto;
import com.securebank.dto.managerdto.ManagerUpdateResponseDto;
import com.securebank.enums.RoleName;

public interface ManagerService {

	ManagerResponseDto getMyProfile(String email);
	ManagerResponseDto updateMyProfile(String email,ManagerRequestDto dto);
	ManagerResponseDto getManagerById(Integer id);
	ManagerUpdateResponseDto updateMyProfileByAdmin(Integer id, ManagerUpdateRequestDto dto);
	List<ManagerResponseDto> getPendingManager();
	List<ManagerResponseDto> getRejectedManager();
	List<ManagerResponseDto> getBlockedManager();
	List<ManagerResponseDto> getUnblockedManager();
	List<ManagerResponseDto> getInactiveManager();
	List<ManagerResponseDto> getAllManager();
	ManagerResponseDto changeManagerRole(Integer id, RoleName role);
}
