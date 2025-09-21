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

    /*
     * PARA TODOS OS MÉTODOS, PASSAR O USUÁRIO LOGADO
     * ISSO GARANTE QUE SOMENTE ADMINISTRADORES POSSAM FAZER CRUD
     *
     *NO "@RequestParam Usuario usuarioLogado" SIMULAMOS O USUÁRIO LOGADO PARA O BACKEND
     *QUANDO TIVER LOGIN DE VERDADE, ISSO VIRÁ COMO TOKEN/SESSÃO
     */

    // LISTAR TODOS OS USUÁRIOS
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam Usuario usuarioLogado) {
        List<Usuario> lista = usuarioService.listarTodos(usuarioLogado);
        return ResponseEntity.ok(lista);
    }

    // BUSCAR USUÁRIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id, @RequestParam Usuario usuarioLogado) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(id, usuarioLogado);
        return usuarioOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CRIAR NOVO USUÁRIO
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioDTO dto, @RequestParam Usuario usuarioLogado) {
        Usuario criado = usuarioService.salvar(dto, usuarioLogado);
        URI uri = URI.create("/api/usuarios/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    // ATUALIZAR USUÁRIO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioDTO dto, @RequestParam Usuario usuarioLogado) {
        Optional<Usuario> atualizado = usuarioService.atualizar(id, dto, usuarioLogado);
        return atualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // EXCLUIR USUÁRIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable int id, @RequestParam Usuario usuarioLogado) {
        usuarioService.excluir(id, usuarioLogado);
        return ResponseEntity.noContent().build();
    }
}
