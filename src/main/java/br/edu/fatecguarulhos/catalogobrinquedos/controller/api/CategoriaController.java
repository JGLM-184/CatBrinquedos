package br.edu.fatecguarulhos.catalogobrinquedos.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.CategoriaDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    //-------------------- CONSULTAS --------------------
    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listarTodas();
    }
    
    @GetMapping("/nome/{nome}")
    public Categoria listarPorNome(String nome) {
        return categoriaService.buscarPorNome(nome);
    }

    //-------------------- CRUD --------------------
    @PostMapping	//POST = CRIAR
    public ResponseEntity<Categoria> criar(@Valid @RequestBody CategoriaDTO dto) {
        Categoria salva = categoriaService.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salva.getId()).toUri();

        return ResponseEntity.created(uri).body(salva);

        // CHAMA O SERVICE PARA SALVAR A CATEGORIA
        // MONTA A URI -> /api/categorias/{id}
        // RETORNA 201 (CREATED) COM O OBJETO SALVO
    }

    @PutMapping("/{id}")	//PUT = ATUALIZAR
    public ResponseEntity<Categoria> atualizar(@PathVariable int id, @Valid @RequestBody CategoriaDTO dto) {
        Optional<Categoria> categoriaAtualizada = categoriaService.atualizar(id, dto);

        return categoriaAtualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        // SE A CATEGORIA EXISTIR, RETORNA 200 COM O OBJETO ATUALIZADO
        // SE NÃO EXISTIR, RETORNA 404
    }

    @DeleteMapping("/{id}")	//DELETE = EXCLUIR
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();

        // PEGA O ID DA CATEGORIA E EXCLUI
        // SE TUDO DER CERTO, RETORNA 204 (NO CONTENT)
    }
}