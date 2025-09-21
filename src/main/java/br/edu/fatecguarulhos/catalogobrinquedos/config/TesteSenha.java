package br.edu.fatecguarulhos.catalogobrinquedos.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteSenha {

	public static void main(String[] args) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String senhaPura = "1234"; // SUA SENHA
	        String senhaCriptografada = encoder.encode(senhaPura);
	        System.out.println("Senha criptografada: " + senhaCriptografada);
	    }
	}


