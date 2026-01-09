package com.hospital.lms_portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/swagger-ui/**",
						"/v3/api-docs/**",
						"/swagger-ui.html",
						"/api/auth/**"
						).permitAll()
				.anyRequest().authenticated()
				)
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}
}
