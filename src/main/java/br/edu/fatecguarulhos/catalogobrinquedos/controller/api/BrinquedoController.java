package br.edu.fatecguarulhos.catalogobrinquedos.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;


@RestController
@RequestMapping("/api/brinquedos")
public class BrinquedoController {

	@Autowired
	private br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService brinquedoService;
	
	@GetMapping
	public List<Brinquedo> listar() {
		return brinquedoService.listarTodos();
	}
}
