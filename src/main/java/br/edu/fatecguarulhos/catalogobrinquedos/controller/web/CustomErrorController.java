package br.edu.fatecguarulhos.catalogobrinquedos.controller.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

//    @RequestMapping("/error")
//    public String erro() {
//        return "erro"; // Retorna o arquivo erro.html
//    }
}