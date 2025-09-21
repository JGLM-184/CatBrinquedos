package br.edu.fatecguarulhos.catalogobrinquedos.controller.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	
	@GetMapping    //GET = CONSULTA
	public List<Brinquedo> listar() {
		return brinquedoService.listarTodos();
	}
	
	
	@PostMapping
    public ResponseEntity<Brinquedo> criar(@Valid @RequestBody BrinquedoDTO dto) {
		
        // CHAMA O SERVICE, QUE TRANSFORMA O 'DTO' EM 'ENTITY' E SALVA NO BANCO
        Brinquedo salvo = brinquedoService.salvar(dto);

        // MONTA URI DO RECURSO CRIADO -> /api/brinquedos/{id}
        
        
        //URI = UNIFORM RESOURCE IDENTIFIER
        //É O QUE COSTUMAMOS CHAMAR DE "CAMINHO" OU URL
        //É O 'ENDEREÇO' QUE O FRONTEND OU O POSTMAN VAI ACESSAR PARA INTERAGIR COM OS DADOS
         
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();

        // RETORNA 200 QUE DEU CERTO
        return ResponseEntity.created(uri).body(salvo);
    }
	
	
}
