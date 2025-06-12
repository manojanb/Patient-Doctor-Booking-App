package com.cts.patient_appointment_management_system.securityconfig;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class Security_Config {
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
			return new JdbcUserDetailsManager(dataSource);
	}
	@Bean 
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
			http.authorizeHttpRequests(configurer -> 
			configurer.anyRequest().permitAll());
			
			return http.build();
		}
	
	
	@Bean
	public  BCryptPasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}

	}
		


