package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.CategoriaDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.BrinquedoRepository;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
    private CategoriaRepository categoriaRepository;
	@Autowired
    private BrinquedoRepository brinquedoRepository;

    //-------------------- CONSULTAS --------------------
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(int id) {
        return categoriaRepository.findById(id);
    }

    public Categoria buscarPorNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    //-------------------- CRUD --------------------
    public Categoria salvar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> atualizar(int id, CategoriaDTO dto) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();
            categoria.setNome(dto.getNome());
            categoriaRepository.save(categoria);
        }
        return categoriaOpt;
    }

    public void excluir(int id) {
    	Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
       
    	if (categoriaOpt.isPresent()) {
            Categoria categoria = categoriaOpt.get();

            // Verifica se existem brinquedos associados
            List<Brinquedo> brinquedos = brinquedoRepository.findByCategoria(categoria);
            if (!brinquedos.isEmpty()) {
                throw new RuntimeException("Não é possível excluir a categoria. " +
                                           "Existem brinquedos associados a ela. " +
                                           "Exclua os brinquedos antes de remover a categoria.\n");
            }

            // Se não houver brinquedos, pode excluir
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }
}