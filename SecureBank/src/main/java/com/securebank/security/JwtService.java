package com.securebank.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private Long expiry;

    @Value("${jwt.refresh-expiry}")
    private Long refreshExpiry;

    public String generateToken(UserDetails userDetails) {
        return generateJwtToken(userDetails, expiry);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateJwtToken(userDetails, refreshExpiry);
    }

    private String generateJwtToken(
            UserDetails userDetails, Long tokenExpiry) {

        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tokenExpiry))
                .subject(userDetails.getUsername())
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(jwtClaimsSet)
        ).getTokenValue();
    }

    public String extractUsername(String token) {
        Jwt decoded = jwtDecoder.decode(token);
        return decoded.getSubject();
    }
}
