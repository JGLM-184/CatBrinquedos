package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; //PARA CRIPTOGRAFIA

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;
import br.edu.fatecguarulhos.catalogobrinquedos.dto.UsuarioDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	//TIPO FINAL GARANTE QUE SEMPRE SERÁ O MESMO ENCODER DURANTE A VIDA DO OBJETO SERVICE, BOA PRÁTICA DE SEGURANÇA
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/*
	 * EM TODOS OS MÉTODOS HÁ O PARÂMETRO DE USUÁRIO LOGADO
	 * ISSO SERVE PARA VERIFICAR SE É UM ADMINISTRADOR
	 * SOMENTE ELE PODE REALIZAR A MANUTENÇÃO DOS USUÁRIOS
	 */
	
	public Usuario buscarPorUsername(String username) {
	    return usuarioRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
	
	public List<Usuario> listarTodos(Usuario usuarioLogado) {
	    if (!usuarioLogado.isAdmin()) {
	        throw new RuntimeException("ACESSO NEGADO: APENAS ADMINISTRADORES PODEM LISTAR USUÁRIOS.");
	    }
	    return usuarioRepository.findAll();
	}

	public Optional<Usuario> buscarPorId(int id, Usuario usuarioLogado) {
	    if (!usuarioLogado.isAdmin()) {
	        throw new RuntimeException("ACESSO NEGADO: APENAS ADMINISTRADORES PODEM BUSCAR USUÁRIOS.");
	    }
	    return usuarioRepository.findById(id);
	}

	public Usuario salvar(UsuarioDTO dto, Usuario usuarioLogado) {
	    if (!usuarioLogado.isAdmin()) {
	        throw new RuntimeException("ACESSO NEGADO: APENAS ADMINISTRADORES PODEM CADASTRAR USUÁRIOS.");
	    }

	    // CRIA UM OBJETO USUARIO E CRIPTOGRAFA A SENHA ANTES DE SALVAR
	    Usuario usuario = new Usuario();
	    usuario.setNome(dto.getNome());
	    usuario.setUsername(dto.getUsername());
	    usuario.setAdmin(dto.isAdmin());
	    usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

	    return usuarioRepository.save(usuario);
	}

	public Optional<Usuario> atualizar(int id, UsuarioDTO dto, Usuario usuarioLogado) {
	    if (!usuarioLogado.isAdmin()) {
	        throw new RuntimeException("ACESSO NEGADO: APENAS ADMINISTRADORES PODEM ATUALIZAR USUÁRIOS.");
	    }

	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
	    if (usuarioOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();
	        usuario.setNome(dto.getNome());
	        usuario.setUsername(dto.getUsername());
	        usuario.setAdmin(dto.isAdmin());

	        // CRIPTOGRAFA A NOVA SENHA SE HOUVER
	        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
	            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
	        }

	        usuarioRepository.save(usuario);
	    }
	    return usuarioOpt;
	}

	public void excluir(int id, Usuario usuarioLogado) {
	    if (!usuarioLogado.isAdmin()) {
	        throw new RuntimeException("ACESSO NEGADO: APENAS ADMINISTRADORES PODEM EXCLUIR USUÁRIOS.");
	    }
	    usuarioRepository.deleteById(id);
	}
}
