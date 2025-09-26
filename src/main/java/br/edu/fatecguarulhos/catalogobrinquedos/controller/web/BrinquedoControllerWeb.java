package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrinquedoControllerWeb {
	
	@GetMapping("/inicio")
    public String inicio() {
        return "inicio"; // retorna o arquivo inicio.html
    }
	
}
