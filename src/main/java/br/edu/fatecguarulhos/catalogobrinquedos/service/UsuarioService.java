package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;
import br.edu.fatecguarulhos.catalogobrinquedos.dto.UsuarioDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setUsername(dto.getUsername());
        usuario.setAdmin(dto.isAdmin());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> atualizar(int id, UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setNome(dto.getNome());
            usuario.setUsername(dto.getUsername());
            usuario.setAdmin(dto.isAdmin());

            if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
                usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
            }

            usuarioRepository.save(usuario);
        }
        return usuarioOpt;
    }

    public void excluir(int id) {
        usuarioRepository.deleteById(id);
    }
}
