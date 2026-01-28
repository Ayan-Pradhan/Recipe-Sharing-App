package com.spring.projects.app.services;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.spring.projects.app.entites.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {
	
	private final JwtEncoder encoder;
	
	private String createToken(User user) {
		var claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(Instant.now())
				.expiresAt(Instant.now().plusSeconds(60*3))
				.subject(user.getEmail())
				.claim("scope", user.getRole())
				.build();
		
		return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
	public String token(User user) {
		return createToken(user);
	}
	
}
