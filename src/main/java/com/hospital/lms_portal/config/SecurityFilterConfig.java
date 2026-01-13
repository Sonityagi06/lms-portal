package com.hospital.lms_portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hospital.lms_portal.security.JwtAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
		
		.sessionManagement(session ->
		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/api/auth/**",
						"/swagger-ui/**",
						"/v3/api-docs/**"
						).permitAll()
				
				.requestMatchers("/api/admin/**")
				.hasRole("ADMIN")
				
				.requestMatchers("/api/faculty/**")
				.hasRole("FACULTY")
				
				.requestMatchers("/api/student/**")
				.hasRole("STUDENT")
				
				.anyRequest().authenticated()
				)
		
		.addFilterBefore(
				jwtAuthenticationFilter, 
				UsernamePasswordAuthenticationFilter.class
				);
		//.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
}
