package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.UsuarioDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;
import br.edu.fatecguarulhos.catalogobrinquedos.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {

    @Autowired
    private UsuarioService usuarioService;
    
    //TELA DE LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    //AO FAZER LOGIN CHAMA A TELA DE PAINEL
    @GetMapping("/painel")
    public String painel(Model model, Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("username", auth.getName());
        return "painel";
    }

    //LISTA OS USUÁRIOS
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "painelUsuario"; 
    }

    //USA O USUARIOFORM.HTML PARA CRIAR NOVO, VEM VAZIO
    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuarioDto", new UsuarioDTO());
        return "UsuarioForm";
    }

    //USA O USUARIOFORM.HTML PARA EDITAR, VEM PREENCHIDO
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        Optional<Usuario> opt = usuarioService.buscarPorId(id);
        if (opt.isPresent()) {
            Usuario u = opt.get();
            UsuarioDTO dto = new UsuarioDTO();
            dto.setNome(u.getNome());
            dto.setUsername(u.getUsername());
            dto.setSenha(""); 
            dto.setAdmin(u.isAdmin());
            model.addAttribute("usuarioDto", dto);
            model.addAttribute("usuarioId", u.getId());
            return "UsuarioForm";
        }
        return "redirect:/usuarios/lista";
    }
    
    //SALVA E RETORNA À LISTA, SE DER ERRADO CONTINUA NO FORM E MOSTRA ERRO
    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute UsuarioDTO usuarioDto,
                                @RequestParam(required=false) Integer id,
                                Model model) {
        try {
            if (id != null) {
                usuarioService.atualizar(id, usuarioDto);
            } else {
                usuarioService.salvar(usuarioDto);
            }
            return "redirect:/usuarios/lista";
        } catch (RuntimeException e) {
            model.addAttribute("usuarioDto", usuarioDto);
            model.addAttribute("usuarioId", id);
            model.addAttribute("erro", e.getMessage());
            return "UsuarioForm";
        }
    }

    //EXCLUI E RETORNA À LISTA
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable int id) {
        usuarioService.excluir(id);
        return "redirect:/usuarios/lista";
    }
}
