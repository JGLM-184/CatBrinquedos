package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@Controller
public class CategoriaControllerWeb {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private BrinquedoService brinquedoService;

    @GetMapping("/categoria/{id}")
    public String exibirCategoria(@PathVariable int id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        List<Brinquedo> brinquedos = brinquedoService.buscarPorCategoria(categoria.getId()); // ✅ passa o ID

        model.addAttribute("categoria", categoria);
        model.addAttribute("brinquedos", brinquedos);

        return "categoria";
    }



}
