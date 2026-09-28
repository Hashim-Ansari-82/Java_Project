package com.securebank.mapper.mapperimpl;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.managerdto.ManagerResponseDto;
import com.securebank.dto.managerdto.ManagerUpdateRequestDto;
import com.securebank.dto.managerdto.ManagerUpdateResponseDto;
import com.securebank.entity.User;
import com.securebank.mapper.ManagerMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ManagerMapperImpl implements ManagerMapper {

	private final PasswordEncoder encode;

	@Override
	public ManagerResponseDto entityToDto(User user) {

		ManagerResponseDto responseDto = new ManagerResponseDto();

		responseDto.setId(user.getId());
		responseDto.setUsername(user.getUsername());
		responseDto.setEmail(user.getEmail());
		responseDto.setAddress(user.getAddress());
		responseDto.setMobile(user.getMobile());
		responseDto.setStatus(user.getStatus());
		responseDto.setCreatedAt(LocalDateTime.now());
		responseDto.setRole(user.getRoles());

		return responseDto;
	}

	@Override
	public User dtoToEntity(ManagerRequestDto dto) {

		User user = new User();

		user.setUsername(dto.username());
		user.setEmail(dto.email());
		user.setMobile(dto.mobile());
		user.setPassword(encode.encode(dto.password()));
		user.setAddress(dto.address());

		return user;
	}

	@Override
	public User updateDtoToEntity(ManagerUpdateRequestDto dto) {

		User user = new User();

		user.setUsername(dto.username());
		user.setEmail(dto.email());
		user.setMobile(dto.mobile());
		user.setPassword(encode.encode(dto.password()));
		user.setRoles(Set.of(dto.roles()));
		user.setAddress(dto.address());

		return user;

	}

	@Override
	public ManagerUpdateResponseDto updateEntityToDto(User user) {
		
		ManagerUpdateResponseDto responseDto = new ManagerUpdateResponseDto();

		responseDto.setId(user.getId());
		responseDto.setUsername(user.getUsername());
		responseDto.setEmail(user.getEmail());
		responseDto.setAddress(user.getAddress());
		responseDto.setMobile(user.getMobile());
		responseDto.setStatus(user.getStatus());
		responseDto.setUpdatedAt(LocalDateTime.now());
		responseDto.setRole(user.getRoles());

		return responseDto;
	}

}
