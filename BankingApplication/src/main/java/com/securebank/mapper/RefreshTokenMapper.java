package com.securebank.mapper;

import com.securebank.dto.refreshtokendto.RefreshTokenRequestDto;
import com.securebank.dto.refreshtokendto.RefreshTokenResponseDto;
import com.securebank.entity.RefreshToken;

public interface RefreshTokenMapper {

	RefreshTokenResponseDto entityToDto(RefreshToken trefreshTokenoken);
	RefreshToken dtoToEntity(RefreshTokenRequestDto dto);
}
