package br.edu.fatecguarulhos.catalogobrinquedos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByNome(String nomeCategoria);
    
    // Procura por 'nome' e ordena por 'nome' em ordem crescente (alfabética)
    List<Categoria> findAllByOrderByNomeAsc(); 
   
    Categoria findByNomeContainingIgnoreCase(String nomeCategoria);
    
}