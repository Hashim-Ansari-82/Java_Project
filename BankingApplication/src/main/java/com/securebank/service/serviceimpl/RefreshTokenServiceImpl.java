package com.securebank.service.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.securebank.entity.RefreshToken;
import com.securebank.repository.RefreshTokenRepo;
import com.securebank.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final RefreshTokenRepo refreshTokenRepo;
	
	@Override
	public RefreshToken saveToken(RefreshToken refreshToken) {
		
		return refreshTokenRepo.save(refreshToken);
	}

	@Override
	public Optional<RefreshToken> findByToken(String refreshToken) {
		
		return refreshTokenRepo.findByRefreshToken(refreshToken);
	}

	@Override
	public void deleteByToken(String refreshToken) {
		
		refreshTokenRepo.deleteByRefreshToken(refreshToken);
	}

}
