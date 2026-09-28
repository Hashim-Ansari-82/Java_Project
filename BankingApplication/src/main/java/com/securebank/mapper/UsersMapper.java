package com.securebank.mapper;

import com.securebank.dto.userdto.UsersRegisterRequestDto;
import com.securebank.dto.userdto.UsersRegisterResponseDto;
import com.securebank.dto.userdto.UsersUpdateRequestDto;
import com.securebank.dto.userdto.UsersUpdateResponseDto;
import com.securebank.entity.User;

public interface UsersMapper {
 
	UsersRegisterResponseDto entityToDto(User user);
	UsersUpdateResponseDto updateEntityToDto(User user);
	User dtoToEntity(UsersRegisterRequestDto dto);
	User updateDtoToEntity(UsersUpdateRequestDto dto);
}
