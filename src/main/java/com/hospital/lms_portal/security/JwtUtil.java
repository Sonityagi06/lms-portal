package com.hospital.lms_portal.security;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "my-secret-key-my-secret-key-my-secret-key";
	
	private Key getKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String subject, String role) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
         .setSigningKey(getKey())
         .build()
         .parseClaimsJws(token)
         .getBody();
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public String extractRole(String token) {
		 return extractAllClaims(token).get("role",String.class);
	}
	
	public boolean isTokenExpired(String token) {
		return extractAllClaims(token)
				.getExpiration()
				.before(new Date());
	}
	
	public boolean validateToken(String token) {
		try {
			extractAllClaims(token);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
