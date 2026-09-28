package com.securebank.mapper.mapperimpl;

import org.springframework.stereotype.Component;

import com.securebank.dto.refreshtokendto.RefreshTokenRequestDto;
import com.securebank.dto.refreshtokendto.RefreshTokenResponseDto;
import com.securebank.entity.RefreshToken;
import com.securebank.mapper.RefreshTokenMapper;

@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

	@Override
	public RefreshTokenResponseDto entityToDto(RefreshToken refreshToken) {
		RefreshTokenResponseDto dto = new RefreshTokenResponseDto();
		
		dto.setId(refreshToken.getId());
		dto.setExpiryDate(refreshToken.getExpiryDate());
		dto.setRefreshToken(refreshToken.getRefreshToken());
		
		return dto;
	}
 
	@Override
	public RefreshToken dtoToEntity(RefreshTokenRequestDto dto) {
		
		RefreshToken token = new RefreshToken();
		
		token.setRefreshToken(dto.getRefreshToken());
		
		return null;
	}

}
