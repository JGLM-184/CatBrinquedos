package br.edu.fatecguarulhos.catalogobrinquedos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Bean do encoder, usado para comparar senha criptografada
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desabilita CSRF para testes via Postman
            .authorizeHttpRequests(auth -> auth
               // .requestMatchers("/api/usuarios/**").hasRole("ADMIN") // só admin acessa
                .anyRequest().permitAll() // todas as outras URLs liberadas
            )
            .userDetailsService(customUserDetailsService) // usa nosso serviço de usuários
            .httpBasic(Customizer.withDefaults()); // Basic Auth

        return http.build();
    }
}
