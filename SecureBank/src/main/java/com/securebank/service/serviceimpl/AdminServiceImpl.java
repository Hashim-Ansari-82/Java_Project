package com.securebank.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.securebank.dto.admindto.AdminUpdateRequestDto;
import com.securebank.dto.admindto.AdminUpdateResponseDto;
import com.securebank.entity.User;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.mapper.AdminMapper;
import com.securebank.repository.UserRepo;
import com.securebank.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final UserRepo userRepo;
	private final AdminMapper adminMapper;

	@Override
	public AdminUpdateResponseDto updateMyProfile(String email, AdminUpdateRequestDto dto) {

		User exUser = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found on this email " + email));

		exUser.setUsername(dto.username());
		exUser.setEmail(dto.email());
		exUser.setMobile(dto.mobile());
		exUser.setStatus(dto.status());

		User save = userRepo.save(exUser);
		
		AdminUpdateResponseDto entityToDto = adminMapper.entityToDto(save);

		return entityToDto;
	}

	@Override
	public AdminUpdateResponseDto getMyProfile(String email) {
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found on this email " + email));
		
		
		return adminMapper.entityToDto(user);
	}
	

}
