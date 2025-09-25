package br.edu.fatecguarulhos.catalogobrinquedos.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.UsuarioDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.UsuarioRepository;
import br.edu.fatecguarulhos.catalogobrinquedos.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository; // precisa para buscar pelo username

    // LISTAR TODOS OS USUÁRIOS
    @GetMapping("/lista")
    public List<Usuario> listarTodos(@AuthenticationPrincipal UserDetails userDetails) {
        // pega o username logado
        Usuario usuarioLogado = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuarioService.listarTodos(usuarioLogado);
    }

    // BUSCAR USUÁRIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuarioLogado = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id, usuarioLogado);
        return usuarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CRIAR NOVO USUÁRIO
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuarioLogado = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Usuario criado = usuarioService.salvar(dto, usuarioLogado);
        URI uri = URI.create("/api/usuarios/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    // ATUALIZAR USUÁRIO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuarioLogado = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Usuario> atualizado = usuarioService.atualizar(id, dto, usuarioLogado);
        return atualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // EXCLUIR USUÁRIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuarioLogado = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioService.excluir(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }
} 	