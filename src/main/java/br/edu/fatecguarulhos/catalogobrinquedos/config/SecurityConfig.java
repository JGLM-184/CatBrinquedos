package br.edu.fatecguarulhos.catalogobrinquedos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/**").permitAll() // LIBERA TODAS AS APIs PARA TESTE
	        )
	        .httpBasic(Customizer.withDefaults()); // só para não quebrar o builder
	    return http.build();
	}

}

