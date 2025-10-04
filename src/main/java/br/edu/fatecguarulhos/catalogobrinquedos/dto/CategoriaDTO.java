package br.edu.fatecguarulhos.catalogobrinquedos.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}