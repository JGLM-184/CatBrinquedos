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

    //INSTANCIA A CLASSE DO SPRING SECURITY PAR CRIPTOGRAFAR
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    //----------------------- CONSULTAS -----------------------
    
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

    
    //----------------------- CRUD -----------------------
    
    public Usuario salvar(UsuarioDTO dto) {
        
    	//VALIDAÇÕES
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Nome de usuário já existe.");
        }
        if (usuarioRepository.findAll().stream()
                .anyMatch(u -> u.getNome().equalsIgnoreCase(dto.getNome()))) {
            throw new RuntimeException("Nome completo já existe.");
        }

        //CRIANDO A ENTIDADE USUÁRIO
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setUsername(dto.getUsername());
        usuario.setAdmin(dto.isAdmin());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha())); //MÉTODO PARA CRIPTOGRAFAR SENHA

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> atualizar(int id, UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // NÃO PERMITIR SALVAR SEM ALTERAÇÕES
            boolean semAlteracao = usuario.getNome().equalsIgnoreCase(dto.getNome())
                    && usuario.getUsername().equalsIgnoreCase(dto.getUsername())
                    && dto.getSenha().isBlank()
                    && usuario.isAdmin() == dto.isAdmin();
            if (semAlteracao) {
                throw new RuntimeException("Nenhuma alteração foi realizada.");
            }

            // VERIFICA DUPLICIDADE DE NOME E USERNAME
            if (usuarioRepository.findAll().stream()
                    .anyMatch(u -> u.getId() != usuario.getId()
                            && u.getNome().equalsIgnoreCase(dto.getNome()))) {
                throw new RuntimeException("Nome completo já existe.");
            }
            if (usuarioRepository.findAll().stream()
                    .anyMatch(u -> u.getId() != usuario.getId()
                            && u.getUsername().equalsIgnoreCase(dto.getUsername()))) {
                throw new RuntimeException("Nome de usuário já existe.");
            }

            usuario.setNome(dto.getNome());
            usuario.setUsername(dto.getUsername());
            usuario.setAdmin(dto.isAdmin());

            //SENHA SÓ MUDA SE FOR DIFERENTE
            if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
                if (passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
                    throw new RuntimeException("A nova senha não pode ser igual à anterior.");
                }
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
