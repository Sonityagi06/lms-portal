package com.hospital.lms_portal.security;



import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
	        throws ServletException , IOException{
		
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authHeader.substring(7);
		
		try {
			String username = jwtUtil.extractUsername(token);
			String role = jwtUtil.extractRole(token);
			
			if(jwtUtil.isTokenExpired(token)) {
				filterChain.doFilter(request, response);
				return;
			}
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					username,
					null,
					Collections.singletonList(
							new SimpleGrantedAuthority("ROLE_" +role)
							)
					);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch(Exception e) {
			SecurityContextHolder.clearContext();
		}
		
		filterChain.doFilter(request, response);
	}
}
