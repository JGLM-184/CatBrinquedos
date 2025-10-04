package br.edu.fatecguarulhos.catalogobrinquedos.dto;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class BrinquedoDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "A marca é obrigatória")
	private String marca;
	
	@NotBlank(message = "A categoria é obrigatória")
	private Categoria categoria;
	
	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;

	@NotBlank(message = "A idade recomendada é obrigatória")
	private String idadeIdeal;
	
	@NotBlank(message = "A imagem é obrigatória")
	private String imagemPrincipal;
	
	@NotNull(message = "O preço é obrigatório")
	@PositiveOrZero
	(message = "O preço não pode ser negativo")
	private double preco;
	
	private boolean destaque;

	public BrinquedoDTO() {

	}

	

	public BrinquedoDTO(@NotBlank(message = "O nome é obrigatório") String nome,
			@NotBlank(message = "A marca é obrigatória") String marca,
			@NotBlank(message = "A categoria é obrigatória") Categoria categoria, 
			@NotBlank(message = "A descrição é obrigatória") String descricao,
			@NotBlank(message = "A idade recomendada é obrigatória") String idadeIdeal,
			@NotBlank(message = "A imagem é obrigatória") String imagemPrincipal,
			@NotBlank(message = "O preço é obrigatório") double preco, boolean destaque) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.categoria = categoria;
		this.descricao = descricao;
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

	public Categoria getCategoria() { 
		return categoria; 
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
