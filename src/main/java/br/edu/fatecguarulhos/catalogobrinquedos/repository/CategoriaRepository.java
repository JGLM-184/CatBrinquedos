package br.edu.fatecguarulhos.catalogobrinquedos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Buscar categoria pelo nome
    Categoria findByNome(String nomeCategoria);
   
    Categoria findByNomeContainingIgnoreCase(String nomeCategoria);
    
}