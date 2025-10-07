package br.edu.fatecguarulhos.catalogobrinquedos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
        	    .requestMatchers("/", "/inicio", "/detalhe/**", "/catalogo", "/buscar", "/sobre", "/categoria/**", "/imagens/**", "/css/**", "/js/**").permitAll()
        	    .requestMatchers("/usuarios/painel").authenticated()
        	    .requestMatchers("/usuarios/**").hasRole("ADMIN")
        	    .requestMatchers("/admin/brinquedos/**").authenticated() // qualquer usuário logado pode CRUD
        	    .anyRequest().authenticated()
        	)

            .formLogin(form -> form
                .loginPage("/usuarios/login")
                .defaultSuccessUrl("/usuarios/painel", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/inicio")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
