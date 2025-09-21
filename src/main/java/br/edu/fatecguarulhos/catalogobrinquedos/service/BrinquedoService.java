package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatecguarulhos.catalogobrinquedos.repository.BrinquedoRepository;
import br.edu.fatecguarulhos.catalogobrinquedos.dto.BrinquedoDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;


@Service
public class BrinquedoService {

	@Autowired
	private BrinquedoRepository brinquedoRepository;
	
	
	
	//-------------------- CONSULTAR E FILTROS --------------------
	
	public List<Brinquedo> listarTodos(){
		return brinquedoRepository.findAll();
	}
	
	public List<Brinquedo> buscarPorCategoria(String categoria) {
	    return brinquedoRepository.findByCategoria(categoria);
	}

	public List<Brinquedo> buscarPorNome(String nome) {
	    return brinquedoRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Brinquedo> buscarPorFaixaDePreco(double min, double max) {
	    return brinquedoRepository.findByPrecoBetween(min, max);
	}

	//-------------------- CRUD --------------------
	public Brinquedo salvar(BrinquedoDTO dto) {
		Brinquedo brinquedo = new Brinquedo();
		
		brinquedo.setNome(dto.getNome());
		brinquedo.setMarca(dto.getMarca());
		brinquedo.setCategoria(dto.getCategoria());
		brinquedo.setIdadeIdeal(dto.getIdadeIdeal());
		brinquedo.setImagemPrincipal(dto.getImagemPrincipal());
		brinquedo.setPreco(dto.getPreco());
		brinquedo.setDestaque(dto.isDestaque());
		
		return brinquedoRepository.save(brinquedo);
	}
	
	
	public Optional<Brinquedo> atualizar(int id, BrinquedoDTO dto) {
		Optional<Brinquedo> brinquedoOpt = brinquedoRepository.findById(id);
		if (brinquedoOpt.isPresent()) {
			Brinquedo brinquedo = brinquedoOpt.get();
			brinquedo.setNome(dto.getNome());
			brinquedo.setMarca(dto.getMarca());
			brinquedo.setCategoria(dto.getCategoria());
			brinquedo.setIdadeIdeal(dto.getIdadeIdeal());
			brinquedo.setImagemPrincipal(dto.getImagemPrincipal());
			brinquedo.setPreco(dto.getPreco());
			brinquedo.setDestaque(dto.isDestaque());		
		
			brinquedoRepository.save(brinquedo);
		}
		return brinquedoOpt;
	}
	
	public void excluir (int id) {
		brinquedoRepository.deleteById(id);
	}
	
}
