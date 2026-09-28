package com.securebank.mapper.mapperimpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.securebank.dto.userdto.UsersRegisterRequestDto;
import com.securebank.dto.userdto.UsersRegisterResponseDto;
import com.securebank.dto.userdto.UsersUpdateRequestDto;
import com.securebank.dto.userdto.UsersUpdateResponseDto;
import com.securebank.entity.User;
import com.securebank.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UsersMapper {

	private final PasswordEncoder encoder;
	
	@Override
	public UsersRegisterResponseDto entityToDto(User user) {
		UsersRegisterResponseDto responseDto = new UsersRegisterResponseDto();

		responseDto.setId(user.getId());
		responseDto.setEmail(user.getEmail());
		responseDto.setUsername(user.getUsername());
		responseDto.setMobile(user.getMobile());
		responseDto.setStatus(user.getStatus());
		responseDto.setAddress(user.getAddress());
		responseDto.setCreatedAt(user.getCreatedAt());

		return responseDto;
	}

	@Override
	public User dtoToEntity(UsersRegisterRequestDto dto) {
		User user = new User();
 
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setMobile(dto.getMobile());
		user.setAddress(dto.getAddress());

		return user;
	}

	@Override
	public UsersUpdateResponseDto updateEntityToDto(User user) {
		UsersUpdateResponseDto responseDto = new UsersUpdateResponseDto();

		responseDto.setId(user.getId());
		responseDto.setEmail(user.getEmail());
		responseDto.setUsername(user.getUsername());
		responseDto.setMobile(user.getMobile());
		responseDto.setStatus(user.getStatus());
		responseDto.setRole(user.getRoles());
		responseDto.setUpdatedAt(user.getUpdatedAt());
		responseDto.setAddress(user.getAddress());

		return responseDto;
	}

	@Override
	public User updateDtoToEntity(UsersUpdateRequestDto dto) {
		
		User user = new User();
		 
		user.setUsername(dto.getUsername());
		user.setMobile(dto.getMobile());
		user.setAddress(dto.getAddress());
		
		return user;
	}

}
