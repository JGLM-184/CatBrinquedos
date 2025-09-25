package br.edu.fatecguarulhos.catalogobrinquedos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String username);

}
