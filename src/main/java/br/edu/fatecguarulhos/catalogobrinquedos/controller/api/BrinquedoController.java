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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.BrinquedoDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoService brinquedoService;

    //-------------------- CONSULTAS --------------------
    @GetMapping
    public List<Brinquedo> listar() {
        return brinquedoService.listarTodos();
    }
    
    @GetMapping("/ids")
    public ResponseEntity<List<Brinquedo>> listarPorIds(@RequestParam List<Integer> ids) {
        List<Brinquedo> brinquedos = brinquedoService.buscarPorIds(ids);
        return ResponseEntity.ok(brinquedos);
        
        // RETORNA OS BRINQUEDOS COM A LISTA DE IDS INFORMADA
    }

    @GetMapping("/categoria/{categoria}")
    public List<Brinquedo> listarPorCategoria(@PathVariable int categoriaId) {
        return brinquedoService.buscarPorCategoria(categoriaId);

        // RETORNA OS BRINQUEDOS DE UMA CATEGORIA
        // PARA CATEGORIAS COM ESPAÇO NO NOME, USE "%20" ENTRE AS PALAVRAS
    }

    @GetMapping("/nome/{nome}")
    public List<Brinquedo> buscarPorNome(@PathVariable String nome) {
        return brinquedoService.buscarPorNome(nome);

        // RETORNA OS BRINQUEDOS CUJO NOME CONTÉM O TERMO INFORMADO (IGNORANDO MAIÚSCULAS/MINÚSCULAS)
    }

    @GetMapping("/preco")
    public List<Brinquedo> buscarPorFaixaDePreco(@RequestParam double min, @RequestParam double max) {
        return brinquedoService.buscarPorFaixaDePreco(min, max);

        // EXEMPLO DE USO -> /api/brinquedos/preco?min=50&max=200
    }

    //-------------------- CRUD --------------------
    @PostMapping	//POST = CRIAR
    public ResponseEntity<Brinquedo> criar(@Valid @RequestBody BrinquedoDTO dto) {
        Brinquedo salvo = brinquedoService.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);

        // SALVA O NOVO BRINQUEDO
        // MONTA URI DO NOVO RECURSO -> /api/brinquedos/{id}
        // RETORNA 201 (CREATED) COM O OBJETO SALVO
    }

    @PutMapping("/{id}")	//PUT = ATUALIZAR
    public ResponseEntity<Brinquedo> atualizar(@PathVariable int id, @Valid @RequestBody BrinquedoDTO dto) {
        Optional<Brinquedo> brinquedoAtualizado = brinquedoService.atualizar(id, dto);

        return brinquedoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        // SE EXISTIR O BRINQUEDO, RETORNA 200 COM O OBJETO ATUALIZADO
        // SE NÃO EXISTIR, RETORNA 404
    }

    @DeleteMapping("/{id}")	//DELETE = EXCLUIR
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        brinquedoService.excluir(id);
        return ResponseEntity.noContent().build();

        // EXCLUI O BRINQUEDO PELO ID
        // SE TUDO DER CERTO, RETORNA 204 (NO CONTENT)
    }
}