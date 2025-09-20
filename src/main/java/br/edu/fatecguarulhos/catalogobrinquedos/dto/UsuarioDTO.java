package br.edu.fatecguarulhos.catalogobrinquedos.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O nome de usuário é obrigatório")
	private String username;
	
	@NotBlank(message = "A senha é obrigatória") 
	private String senha;  // JÁ CRIPTOGRAFADA NA CAMADA SERVICE
	
	private boolean admin;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(@NotBlank(message = "O nome é obrigatório") String nome,
			@NotBlank(message = "O nome de usuário é obrigatório") String username,
			@NotBlank(message = "A senha é obrigatória") String senha, boolean admin) {
		super();
		this.nome = nome;
		this.username = username;
		this.senha = senha;
		this.admin = admin;
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
