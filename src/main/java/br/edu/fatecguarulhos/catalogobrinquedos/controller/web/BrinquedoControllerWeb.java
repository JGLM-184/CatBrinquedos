package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

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
import br.edu.fatecguarulhos.catalogobrinquedos.dto.CategoriaDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@Controller
public class BrinquedoControllerWeb {

    @Autowired
    private BrinquedoService brinquedoService;

    @Autowired
    private CategoriaService categoriaService;

    // Página inicial
    @GetMapping("/inicio")
    public String inicio(Model model) {
        List<Brinquedo> listaDeBrinquedos = brinquedoService.listarTodos();
        List<Categoria> listaDeCategorias = categoriaService.listarTodas();
        model.addAttribute("listaDeBrinquedos", listaDeBrinquedos);
        model.addAttribute("listaDeCategorias", listaDeCategorias);
        return "inicio";
    }

    // Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    
 // Página de detalhes de um brinquedo específico
    @GetMapping("/detalhe/{id}")
    public String detalheBrinquedo(@PathVariable int id, Model model) {
        Brinquedo brinquedo = brinquedoService.buscarPorId(id); // pega do banco
        model.addAttribute("brinquedo", brinquedo);
        return "detalheProduto";
    }
    
    // ------------------ PÁGINA TESTE ------------------
    @GetMapping("/teste")
    public String teste(Model model) {
        List<Brinquedo> listaDeBrinquedos = brinquedoService.listarTodos();
        List<Categoria> listaDeCategorias = categoriaService.listarTodas();
        model.addAttribute("listaDeBrinquedos", listaDeBrinquedos);
        model.addAttribute("listaDeCategorias", listaDeCategorias);
        return "teste";
    }

    // ------------------ CRUD Brinquedos ------------------

    @PostMapping("/teste/criar-brinquedo")
    public String criarBrinquedo(@ModelAttribute BrinquedoDTO dto,
                                 @RequestParam("file") MultipartFile file) {
        brinquedoService.salvar(dto, file);
        return "redirect:/teste";
    }

    @PostMapping("/teste/atualizar-brinquedo/{id}")
    public String atualizarBrinquedo(@PathVariable("id") int id,
                                     @ModelAttribute BrinquedoDTO dto) {
        brinquedoService.atualizar(id, dto);
        return "redirect:/teste";
    }

    @PostMapping("/teste/atualizar-imagem-brinquedo/{id}")
    public String atualizarImagemBrinquedo(@PathVariable("id") int id,
                                           @ModelAttribute BrinquedoDTO dto,
                                           @RequestParam("file") MultipartFile file) {
        brinquedoService.atualizarImagem(id, dto, file);
        return "redirect:/teste";
    }

    @GetMapping("/teste/excluir-brinquedo/{id}")
    public String excluirBrinquedo(@PathVariable("id") int id) {
        brinquedoService.excluir(id);
        return "redirect:/teste";
    }

    // ------------------ CRUD Categorias ------------------

    @PostMapping("/teste/criar-categoria")
    public String criarCategoria(@ModelAttribute CategoriaDTO categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/teste";
    }

    @PostMapping("/teste/atualizar-categoria/{id}")
    public String atualizarCategoria(@PathVariable("id") int id,
                                     @ModelAttribute CategoriaDTO categoria) {
        categoriaService.atualizar(id, categoria);
        return "redirect:/teste";
    }

    @GetMapping("/teste/excluir-categoria/{id}")
    public String excluirCategoria(@PathVariable("id") int id) {
        categoriaService.excluir(id);
        return "redirect:/teste";
    }

    // ------------------ Visualizar Brinquedos por Categoria ------------------

    @GetMapping("/teste/brinquedos-por-categoria/{id}")
    public String listarPorCategoria(@PathVariable("id") int id, Model model) {
    	String categoria;
    	if (categoriaService.buscarPorId(id).isPresent()) {
    		categoria = categoriaService.buscarPorId(id).get().getNome();
    	}
    	else {
    		categoria = "Categoria Não Existe";
    	}
    	
        List<Brinquedo> brinquedos = brinquedoService.buscarPorCategoria(id);

        model.addAttribute("categoriaSelecionada", categoria);
        model.addAttribute("listaDeBrinquedos", brinquedos);
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());

        return "teste";
    }
}
