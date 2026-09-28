package com.securebank.mapper;

import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.managerdto.ManagerResponseDto;
import com.securebank.dto.managerdto.ManagerUpdateRequestDto;
import com.securebank.dto.managerdto.ManagerUpdateResponseDto;
import com.securebank.entity.User;

public interface ManagerMapper {

	ManagerResponseDto entityToDto(User user);
	User  dtoToEntity(ManagerRequestDto dto);
	User updateDtoToEntity(ManagerUpdateRequestDto dto);
	ManagerUpdateResponseDto updateEntityToDto(User user);
}
