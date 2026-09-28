package com.securebank.mapper;

import com.securebank.dto.admindto.AdminUpdateRequestDto;
import com.securebank.dto.admindto.AdminUpdateResponseDto;
import com.securebank.entity.User;

public interface AdminMapper {

	AdminUpdateResponseDto entityToDto(User user);
	User dtoToEntity(AdminUpdateRequestDto dto);
}
