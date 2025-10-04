package br.edu.fatecguarulhos.catalogobrinquedos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;


public interface BrinquedoRepository extends JpaRepository<Brinquedo, Integer>{
	
	List<Brinquedo> findByCategoria(Categoria categoria);
	
	public List<Brinquedo> findByNomeContainingIgnoreCase(String nome);

    List<Brinquedo> findByPrecoBetween(double min, double max);

}