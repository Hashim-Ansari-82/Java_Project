package com.securebank.service;

import java.util.Optional;

import com.securebank.entity.RefreshToken;

public interface RefreshTokenService {

 	RefreshToken saveToken(RefreshToken refreshToken);
 	Optional<RefreshToken> findByToken(String refreshToken);
 	void deleteByToken(String refreshToken);
}
