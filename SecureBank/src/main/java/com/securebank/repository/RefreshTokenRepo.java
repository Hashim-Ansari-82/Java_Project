package com.securebank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securebank.entity.RefreshToken;
import com.securebank.entity.User;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer>{

	 Optional<RefreshToken> findByRefreshToken(String refreshToken);
	 void deleteByUser(User user);
	 
	    boolean existsByRefreshToken(String refreshToken);

	    void deleteByRefreshToken(String refreshToken);
}
