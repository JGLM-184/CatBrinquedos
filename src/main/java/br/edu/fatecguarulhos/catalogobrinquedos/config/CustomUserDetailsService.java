package br.edu.fatecguarulhos.catalogobrinquedos.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //VERIFICA SE O USUÁRIO EXISTE
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // TRANSFORMA BOOLEAN DE "ADMIN" EM UM ROLE
        //ROLE_ADMIN É A TAG QUE O SPRING USA PARA RECONHECER QUE AQUELE USUÁRIO TEM PRIVILÉGIOS DE ADMINISTRADOR
        String role = usuario.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        //AUTENTICAÇÃO SEGURA COM A SENHA CRIPTOGRAFADA
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getSenha())
                .authorities(Collections.singletonList(() -> role))
                .build();
    }
}
