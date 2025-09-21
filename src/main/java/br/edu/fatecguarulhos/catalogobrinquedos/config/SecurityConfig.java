package br.edu.fatecguarulhos.catalogobrinquedos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // ROTAS PÚBLICAS
                .requestMatchers("/api/brinquedos/**").permitAll()

               /* // ROTAS RESTRITAS (SÓ ADMIN)
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // QUALQUER OUTRA REQUISIÇÃO PRECISA DE LOGIN
                .anyRequest().authenticated()*/
            )
            .formLogin(login -> login.permitAll()) // HABILITA LOGIN QUANDO PRECISO
            .logout(logout -> logout.permitAll()); // HABILITA LOGOUT
        return http.build();
    }
}

