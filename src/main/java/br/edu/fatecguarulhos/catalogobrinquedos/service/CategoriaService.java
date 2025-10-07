package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        return categoriaRepository.findAllByOrderByNomeAsc();
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

            // Busca todos os brinquedos associados à categoria
            List<Brinquedo> brinquedos = brinquedoRepository.findByCategoria(categoria);

            // Exclui todos os brinquedos associados
            if (!brinquedos.isEmpty()) {
            	for (Brinquedo brinquedo : brinquedos) {
	            	if (brinquedo.getImagemPrincipal() != null) {
	                    try {
	                        Path caminhoImagem = Paths.get("src/main/resources/static" + brinquedo.getImagemPrincipal());
	                        Files.deleteIfExists(caminhoImagem);
	                    } catch (IOException e) {
	                        System.err.println("Não foi possível excluir a imagem: " + e.getMessage());
	                    }
	            	}
                }
                brinquedoRepository.deleteAll(brinquedos);
            }

            // Agora exclui a categoria
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada.");
        }
    }
}