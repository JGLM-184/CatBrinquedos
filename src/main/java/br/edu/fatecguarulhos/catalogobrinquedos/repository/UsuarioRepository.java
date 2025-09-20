package br.edu.fatecguarulhos.catalogobrinquedos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
