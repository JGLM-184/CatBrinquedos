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
	
	@GetMapping 	//GET = CONSULTA
	public List<Brinquedo> listar() {
		return brinquedoService.listarTodos();
	}
	
	@GetMapping("/categoria/{categoria}")
	public List<Brinquedo> listarPorCategoria(@PathVariable String categoria) {
	    return brinquedoService.buscarPorCategoria(categoria);
	    
	    //PARA PESQUISAR UMA CATEGORIA QUE TEM ESPAÇO NO NOME, COLOQUE %20 ENTRE OS ESPAÇOS
	}
	
	@GetMapping("/nome/{nome}")
	public List<Brinquedo> buscarPorNome(@PathVariable String nome) {
	    return brinquedoService.buscarPorNome(nome);
	}
	
	@GetMapping("/preco")
	public List<Brinquedo> buscarPorFaixaDePreco(@RequestParam double min, @RequestParam double max) {
	    return brinquedoService.buscarPorFaixaDePreco(min, max);
	    
	    //EXEMPLO DE BUSCA -> preco?min=50&max=200
	}


	//-------------------- CRUD --------------------
	@PostMapping	//POST = CRIAR
    public ResponseEntity<Brinquedo> criar(@Valid @RequestBody BrinquedoDTO dto) {
        Brinquedo salvo = brinquedoService.salvar(dto);         
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salvo.getId()).toUri();
        
        return ResponseEntity.created(uri).body(salvo);

        // CHAMA O SERVICE, QUE TRANSFORMA O 'DTO' EM 'ENTITY' E SALVA NO BANCO
        // MONTA URI DO RECURSO CRIADO -> /api/brinquedos/{id}
        //URI = UNIFORM RESOURCE IDENTIFIER
        //É O QUE COSTUMAMOS CHAMAR DE "CAMINHO" OU URL
        //É O 'ENDEREÇO' QUE O FRONTEND OU O POSTMAN VAI ACESSAR PARA INTERAGIR COM OS DADOS
        // RETORNA 200 QUE DEU CERTO
    }
	
	@PutMapping("/{id}") 	//PUT = ALTERAR/ATUALIZAR
	public ResponseEntity<Brinquedo> atualizar(@PathVariable int id, @Valid @RequestBody BrinquedoDTO dto) {
		Optional<Brinquedo> brinquedoAtualizado = brinquedoService.atualizar(id, dto);
		
		return brinquedoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		
		// SE EXISTIR O BRINQUEDO, RETORNA 200 COM O OBJETO ATUALIZADO
		// SE NÃO EXISTIR, RETORNA 404
	}
	
	@DeleteMapping("/{id}") 	//DELETE = EXCLUIR
	public ResponseEntity<Void> excluir(@PathVariable int id) {
	    brinquedoService.excluir(id);
	    return ResponseEntity.noContent().build();
	    
	    //PEGA O ID DO BRINQUEDO E EXCLUI
	    //SE FOR CERTO RETORNA 204
	}
}
