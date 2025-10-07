package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.BrinquedoDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.service.BrinquedoService;
import br.edu.fatecguarulhos.catalogobrinquedos.service.CategoriaService;

@Controller
@RequestMapping("/admin/brinquedos")
public class AdminBrinquedoWebController {

    @Autowired
    private BrinquedoService brinquedoService;

    @Autowired
    private CategoriaService categoriaService;

    // ------------------- LISTAR -------------------
    @GetMapping
    public String listarBrinquedos(Model model) {
        List<Brinquedo> lista = brinquedoService.listarTodosAlfabetico();
        model.addAttribute("listaDeBrinquedos", lista);
        return "painelBrinquedo";
    }

    // ------------------- CRIAR -------------------
    @GetMapping("/criar-brinquedo")
    public String novoBrinquedo(Model model) {
        model.addAttribute("brinquedoDTO", new BrinquedoDTO());
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        model.addAttribute("isEdicao", false); // flag para o formulário
        return "brinquedoForm";
    }

    @PostMapping("/salvar-brinquedo")
    public String salvarBrinquedo(@ModelAttribute BrinquedoDTO dto,
                                  @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            brinquedoService.salvar(dto, file);
        } else {
            brinquedoService.salvar(dto);
        }
        return "redirect:/admin/brinquedos";
    }

    // ------------------- EDITAR -------------------
    @GetMapping("/editar-brinquedo/{id}")
    public String editarBrinquedo(@PathVariable int id, Model model) {
        Brinquedo brinquedo = brinquedoService.buscarPorId(id);
        BrinquedoDTO dto = new BrinquedoDTO(brinquedo); // já preenche o ID
        model.addAttribute("brinquedoDTO", dto);
        model.addAttribute("listaDeCategorias", categoriaService.listarTodas());
        model.addAttribute("isEdicao", true);
        return "brinquedoForm";
    }


    @PostMapping("/atualizar-brinquedo/{id}")
    public String atualizarBrinquedo(@PathVariable int id,
                                     @ModelAttribute BrinquedoDTO dto,
                                     @RequestParam(value = "file", required = false) MultipartFile file) {
    	brinquedoService.atualizar(id, dto);
        
    	if (!file.isEmpty()) {
            brinquedoService.atualizarImagem(id, dto, file);
        } 
        
        return "redirect:/admin/brinquedos";
    }

    // ------------------- EXCLUIR -------------------
    @GetMapping("/excluir-brinquedo/{id}")
    public String excluirBrinquedo(@PathVariable int id) {
        brinquedoService.excluir(id);
        return "redirect:/admin/brinquedos";
    }
}
