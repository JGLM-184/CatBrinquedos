package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@Controller
@RequestMapping
public class BrinquedoWebController {

    @Autowired
    private BrinquedoService brinquedoService;

    @Autowired
    private CategoriaService categoriaService;

    // ------------------- ROTAS PÚBLICAS -------------------
    
    //TELA INICIAL
    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("listaDeBrinquedos", brinquedoService.listarTodos());
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        return "inicio";
    }

    //TELA DO CATÁLOGO COM TODOS OS BRINQUEDOS
    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("listaDeBrinquedos", brinquedoService.listarTodos());
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        return "catalogo";
    }

    //PESQUISAR NOME DE BRINQUEDO
    //USA A TELA DO CATÁLOGO MAS COM FILTRO DA PESQUISA
    @GetMapping("/buscar")
    public String buscarBrinquedo(@RequestParam("nome") String nome, Model model) {
        model.addAttribute("listaDeBrinquedos", brinquedoService.buscarPorNome(nome));
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        model.addAttribute("termoBusca", nome);
        return "catalogo";
    }

    //TELA COM DETALHE DE UM BRINQUEDO
    @GetMapping("/detalhe/{id}")
    public String detalheBrinquedo(@PathVariable int id, Model model) {
        Brinquedo brinquedo = brinquedoService.buscarPorId(id);
        model.addAttribute("brinquedo", brinquedo);
        return "detalheProduto";
    }
    
    //TELA COM OS MEMBROS DA EQUIPE
    @GetMapping("/sobre")
    public String sobre() {
     
        return "sobre";
    }
}
