package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/")
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
    //TELA DO CATÁLOGO COM TODOS OS BRINQUEDOS FILTRADOS
    @GetMapping("/catalogo/pesquisa")
    public String catalogoFiltrado(
    	//Filtros de Busca
    	@RequestParam(defaultValue = "") String nome,
        @RequestParam(defaultValue = "0") int categoriaId,
        @RequestParam(defaultValue = "") String marca,
        @RequestParam(defaultValue = "") String idadeIdeal,
        @RequestParam(defaultValue = "0") double precoMin,
        @RequestParam(defaultValue = "0") double precoMax,
        Model model) {

        // Busca todos ou filtrados
        List<Brinquedo> brinquedos = brinquedoService.buscarFiltrado(
        		nome, categoriaId, marca, idadeIdeal, precoMin, precoMax);
    	
    	model.addAttribute("listaDeBrinquedos", brinquedos);
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        return "catalogo";
    }
    
    @GetMapping("/favoritos")
    public String favoritos(Model model) {
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        return "favoritos";
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
    
    //URL PARA A LISTA DE DESEJOS PEGAR OS BRINQUEDOS
    @GetMapping("/api/brinquedos")
    @ResponseBody
    public List<Brinquedo> listarTodos() {
        return brinquedoService.listarTodos();
    }
}
