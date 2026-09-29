package com.securebank.mapper.mapperimpl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.securebank.dto.admindto.AdminUpdateRequestDto;
import com.securebank.dto.admindto.AdminUpdateResponseDto;
import com.securebank.entity.User;
import com.securebank.mapper.AdminMapper;

@Component
public class AdminMapperImpl implements AdminMapper {

	@Override
	public AdminUpdateResponseDto entityToDto(User user) {

		AdminUpdateResponseDto dto = new AdminUpdateResponseDto();

		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		dto.setStatus(user.getStatus());
		dto.setRole(user.getRoles());
		dto.setUpdatedAt(LocalDateTime.now());
		dto.setMobile(user.getMobile());

		return dto;
	}

	@Override
	public User dtoToEntity(AdminUpdateRequestDto dto) {
	
		User user = new User();
		user.setUsername(dto.username());
		user.setEmail(dto.email());
		user.setMobile(dto.mobile());
		user.setStatus(dto.status());
		
		return user;
	}

}
