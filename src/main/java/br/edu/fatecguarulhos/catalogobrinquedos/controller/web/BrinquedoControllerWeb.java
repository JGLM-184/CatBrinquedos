package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.BrinquedoDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService;

@Controller
public class BrinquedoControllerWeb {
	
	@Autowired
	private BrinquedoService brinquedoService;
	
	@GetMapping("/inicio")
    public String inicio(Model model) {
		List<Brinquedo> listaDeBrinquedos = brinquedoService.listarTodos();
		model.addAttribute("listaDeBrinquedos", listaDeBrinquedos);
        return "inicio"; // retorna o arquivo inicio.html
    }
	
	@GetMapping("/login")
	public String login() {
	    return "login"; // Nome do seu arquivo login.html dentro de templates/
	}
	
	//-------------- URLS PARA TESTAR A APLICAÇÃO WEB --------------
	@GetMapping("/teste")
    public String teste(Model model) {
		List<Brinquedo> listaDeBrinquedos = brinquedoService.listarTodos();
		model.addAttribute("listaDeBrinquedos", listaDeBrinquedos);
        return "teste"; // retorna o arquivo teste.html
	}
	
	@PostMapping("/teste/criar-brinquedo")
    public String criar(@ModelAttribute BrinquedoDTO dto, @RequestParam("file") MultipartFile file) {
		brinquedoService.salvar(dto, file);
        return "redirect:/teste"; // salva o brinquedo e chama a url "/teste"
    }
	
	@PostMapping("/teste/atualizar-brinquedo/{id}")
	public String atualizar(@PathVariable("id") int id, @ModelAttribute BrinquedoDTO dto) {
		brinquedoService.atualizar(id, dto);
		return "redirect:/teste"; // atualiza o brinquedo e chama a url "/teste"
	}
	
	@PostMapping("/teste/atualizar-imagem-brinquedo/{id}")
	public String atualizarImagem(@PathVariable("id") int id, @ModelAttribute BrinquedoDTO dto,  MultipartFile file) {
		brinquedoService.atualizarImagem(id, dto, file);
		return "redirect:/teste"; // atualiza a imagem do brinquedo e chama a url "/teste"
	}
	
	@GetMapping("/teste/excluir-brinquedo/{id}")
	public String excluir(@PathVariable("id") int id) {
		brinquedoService.excluir(id);
		return "redirect:/teste"; // exclui o brinquedo e chama a url "/teste"
	}
	
	
	
	
}
