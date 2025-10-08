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
        		//TORNA ESSAS ROTAS PÚBLICAS (NÃO PEDE LOGIN)
        	    .requestMatchers("/**", "/inicio", "/detalhe/**", "/catalogo", "/buscar", "/sobre", "/categoria/**", "/imagens/**", "/css/**", "/js/**").permitAll()
        	    
        	    //QUALQUER USUÁRIO AUTENTICADO PODE ACESSAR O PAINEL
        	    .requestMatchers("/usuarios/painel").authenticated()
        	    
        	    //SOMENTE UM USUÁRIO COM A TAG/BOOLEAN ADMINISTRADOR PODE FAZER CRUD DE USUÁRIOS
        	    .requestMatchers("/usuarios/**").hasRole("ADMIN")
        	    
        	    //QUALQUER USUÁRIO AUTENTICADO PODE FAZER O CRUD DE BRINQUEDOS E CATEGORIAS
        	    .requestMatchers("/admin/brinquedos/**").authenticated()
        	    .anyRequest().authenticated()
        	)

        
        //CUIDA DA PARTE DE LOGIN, CHAMA A API QUE TEM O LOGIN.HTML E LEVA AO PAINEL.HTML
            .formLogin(form -> form
                .loginPage("/usuarios/login")
                .defaultSuccessUrl("/usuarios/painel", true)
                .permitAll()
            )
        //CUIDA DA PARTE DE LOGOU, CHAMA A API QUE TEM O INICIO.HTML AO SAIR
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            //CSRF: PROTEÇÃO CONTRA REQUISIÇÕES MALICIOSAS USANDO A SESSÃO DO USUÁRIO
            //PODE SER DESABILITADA EM TESTES PARA FACILITAR
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
