package br.edu.fatecguarulhos.catalogobrinquedos.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String username;
	private String senha; //CRIPTOGRAFADA NA CAMADA/PACOTE 'SERVICE'
	private boolean admin; //SE VERDADEIRO, REALIZA CRUD DE OUTROS USUÁRIOS
	
	public Usuario() {
		
	}

	public Usuario(String nome, String username, String senha, boolean admin) {
		super();
		this.nome = nome;
		this.username = username;
		this.senha = senha;
		this.admin = admin;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
}
