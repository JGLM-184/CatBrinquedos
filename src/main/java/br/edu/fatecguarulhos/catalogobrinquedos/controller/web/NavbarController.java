package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@Controller
public class NavbarController {

    @Autowired
    private CategoriaService categoriaService;

    // Esse método é chamado automaticamente antes de qualquer página que use a navbar
    @ModelAttribute("listaCategorias")
    public List<Categoria> populateCategorias() {
        return categoriaService.listarTodas();
    }
}
