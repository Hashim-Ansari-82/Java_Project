package com.securebank.mapper;

import com.securebank.dto.refreshtokendto.AccessTokenResponseDto;
import com.securebank.entity.RefreshToken;

public interface RefreshTokenMapper {

	AccessTokenResponseDto refreshToAccessToken(RefreshToken refreshToken);
	
}
