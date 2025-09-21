package br.edu.fatecguarulhos.catalogobrinquedos.dto;

import jakarta.validation.constraints.NotBlank;

public class BrinquedoDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "A marca é obrigatória")
	private String marca;
	
	@NotBlank(message = "A categoria é obrigatória")
	private String categoria;

	@NotBlank(message = "A idade recomendada é obrigatória")
	private String idadeIdeal;
	
	@NotBlank(message = "A imagem é obrigatória")
	private String imagemPrincipal;
	
	private double preco;
	
	private boolean destaque;

	public BrinquedoDTO() {

	}

	

	public BrinquedoDTO(@NotBlank(message = "O nome é obrigatório") String nome,
			@NotBlank(message = "A marca é obrigatória") String marca,
			@NotBlank(message = "A categoria é obrigatória") String categoria,
			@NotBlank(message = "A idade recomendada é obrigatória") String idadeIdeal,
			@NotBlank(message = "A imagem é obrigatória") String imagemPrincipal,
			@NotBlank(message = "O preço é obrigatório") double preco, boolean destaque) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.categoria = categoria;
		this.idadeIdeal = idadeIdeal;
		this.imagemPrincipal = imagemPrincipal;
		this.preco = preco;
		this.destaque = destaque;
	}



	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() { 
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca; 
	}

	public String getCategoria() { 
		return categoria; 
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getIdadeIdeal() {
		return idadeIdeal;
	}
	
	public void setIdadeIdeal(String idadeIdeal) {
		this.idadeIdeal = idadeIdeal;
	}

	public String getImagemPrincipal() {
		return imagemPrincipal;
	}
	
	public void setImagemPrincipal(String imagemPrincipal) {
		this.imagemPrincipal = imagemPrincipal;
	}

	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isDestaque() {
		return destaque;
	}
	
	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}



}
