package br.edu.fatecguarulhos.catalogobrinquedos.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.UsuarioDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;
import br.edu.fatecguarulhos.catalogobrinquedos.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // LISTAR TODOS OS USUÁRIOS (apenas ADMIN)
    @GetMapping("/lista")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    // BUSCAR USUÁRIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id);
        return usuarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CRIAR NOVO USUÁRIO
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        Usuario criado = usuarioService.salvar(dto);
        URI uri = URI.create("/api/usuarios/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    // ATUALIZAR USUÁRIO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioDTO dto) {
        Optional<Usuario> atualizado = usuarioService.atualizar(id, dto);
        return atualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // EXCLUIR USUÁRIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable int id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
