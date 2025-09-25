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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Transforma boolean admin em ROLE
        String role = usuario.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getSenha()) // já criptografada
                .authorities(Collections.singletonList(() -> role))
                .build();
    }
}
