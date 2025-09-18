package br.edu.fatecguarulhos.catalogobrinquedos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;


public interface BrinquedoRepository extends JpaRepository<Brinquedo, Integer>{

}
