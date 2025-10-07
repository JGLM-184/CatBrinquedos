package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.CategoriaDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@Controller
@RequestMapping("/admin/categorias")
public class AdminCategoriaWebController {

    @Autowired
    private CategoriaService categoriaService;

    // ------------------- LISTAR -------------------
    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> lista = categoriaService.listarTodas();
        model.addAttribute("listaDeCategorias", lista);
        return "painelCategoria";
    }

    // ------------------- CRIAR -------------------
    @GetMapping("/criar-categoria")
    public String novoBrinquedo(Model model) {
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        model.addAttribute("isEdicao", false); // flag para o formulário
        return "categoriaForm";
    }

    @PostMapping("/salvar-categoria")
    public String salvarCategoria(@ModelAttribute CategoriaDTO dto) {
        categoriaService.salvar(dto);
        return "redirect:/admin/categorias";
    }

    // ------------------- EDITAR -------------------
    @GetMapping("/editar-categoria/{id}")
    public String editarCategoria(@PathVariable int id, Model model) {
    	Optional<Categoria> categoria = categoriaService.buscarPorId(id);
    	
    	if (categoria.isPresent()) {
	        CategoriaDTO dto = new CategoriaDTO(categoria.get()); // já preenche o ID
	        model.addAttribute("categoriaDTO", dto);
	        model.addAttribute("isEdicao", true);
	        return "categoriaForm";
    	}
    	return "redirect:/admin/categorias";
    }


    @PostMapping("/atualizar-categoria/{id}")
    public String atualizarBrinquedo(@PathVariable int id,
                                     @ModelAttribute CategoriaDTO dto) {
    	categoriaService.atualizar(id, dto);
       
        return "redirect:/admin/categorias";
    }

    // ------------------- EXCLUIR -------------------
    @GetMapping("/excluir-categoria/{id}")
    public String excluirBrinquedo(@PathVariable int id) {
        categoriaService.excluir(id);
        return "redirect:/admin/categorias";
    }
}
