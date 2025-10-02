package br.edu.fatecguarulhos.catalogobrinquedos.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//ENTIDADE -> TABELA NO BANCO DE DADOS
@Entity
public class Brinquedo {
	
	//ATRIBUTOS
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //CHAVE PRIMÁRIO EM AUTO INCREMENTO
	private int id;
	private String nome;
	private String marca;
	private String categoria;
	private String descricao;
	private String idadeIdeal;
	private String imagemPrincipal;
	private double preco;
	private boolean destaque;
	
	public Brinquedo() {
		
	}

	public Brinquedo(String nome, String marca, String categoria, String descricao, String idadeIdeal, String imagemPrincipal,
			double preco, boolean destaque) {
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
