package com.securebank.mapper.mapperimpl;

import org.springframework.stereotype.Component;

import com.securebank.dto.refreshtokendto.AccessTokenResponseDto;
import com.securebank.entity.RefreshToken;
import com.securebank.mapper.RefreshTokenMapper;

@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

	@Override
	public AccessTokenResponseDto refreshToAccessToken(RefreshToken refreshToken) {
		AccessTokenResponseDto dto = new AccessTokenResponseDto();
		
		dto.setId(refreshToken.getId());
		dto.setExpiryDate(refreshToken.getExpiryDate());
		
		return dto;
	}
}
