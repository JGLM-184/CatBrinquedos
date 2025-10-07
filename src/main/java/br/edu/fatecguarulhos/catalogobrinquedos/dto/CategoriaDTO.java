package br.edu.fatecguarulhos.catalogobrinquedos.dto;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

	private int id; // novo campo
	
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }
    
    public CategoriaDTO(Categoria categoria) {
    	this.id = categoria.getId();
    	this.nome = categoria.getNome();
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}