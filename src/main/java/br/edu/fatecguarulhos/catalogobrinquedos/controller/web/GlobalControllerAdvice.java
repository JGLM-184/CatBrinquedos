package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CategoriaService categoriaService;

    @ModelAttribute("listaDeCategorias")
    public List<Categoria> listaDeCategorias() {
        return categoriaService.listarTodas();
    }
}
