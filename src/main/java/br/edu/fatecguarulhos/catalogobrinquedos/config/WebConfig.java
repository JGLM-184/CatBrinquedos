package br.edu.fatecguarulhos.catalogobrinquedos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//MAPEIA A URL /IMAGENS/BRINQUEDOS/** PARA A PASTA DO PROJETO
        registry.addResourceHandler("/imagens/brinquedos/**")
                .addResourceLocations("file:src/main/resources/static/imagens/brinquedos/");
    }
}
