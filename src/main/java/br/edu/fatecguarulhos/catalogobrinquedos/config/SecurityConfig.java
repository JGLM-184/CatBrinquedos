package br.edu.fatecguarulhos.catalogobrinquedos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            .csrf(csrf -> csrf.disable()) // desabilita CSRF para testes (depois pode reativar)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN") // só admin acessa
                .anyRequest().permitAll() // todo o resto está liberado
            )
            .formLogin(form -> form
                .loginPage("/login.html") // sua página de login (HTML que você já fez)
                .loginProcessingUrl("/login") // endpoint que o form envia (action="/login")
                .defaultSuccessUrl("/index.html", true) // para onde vai se o login der certo
                .failureUrl("/login.html?error=true") // se der erro, volta para login com erro
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL do logout
                .logoutSuccessUrl("/index") // redireciona depois de sair
                .permitAll()
            )
            .userDetailsService(customUserDetailsService);

        return http.build();
    }

}
