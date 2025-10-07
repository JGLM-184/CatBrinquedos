package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.BrinquedoDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.BrinquedoRepository;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.CategoriaRepository;

@Service
public class BrinquedoService {

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final String PASTA_STATIC = "src/main/resources/static";
    private final String PASTA_IMAGENS = "src/main/resources/static/imagens/brinquedos/";
    private final String DIRETORIO_IMAGEM = "/imagens/brinquedos/";

    //-------------------- CONSULTAR E FILTROS --------------------

    public List<Brinquedo> listarTodos() {
        return brinquedoRepository.findAll();
    }
    
    public List<Brinquedo> listarTodosAlfabetico() {
        return brinquedoRepository.findAllByOrderByNomeAsc();
    }
    
    public Brinquedo buscarPorId(int id) {
        return brinquedoRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Brinquedo não encontrado com id: " + id));
    }
    
	public List<Brinquedo> buscarPorIds(List<Integer> ids) {
		return brinquedoRepository.findAllById(ids);
	}

	public List<Brinquedo> buscarPorCategoria(int categoriaId) {
	    Categoria categoria = categoriaRepository.findById(categoriaId)
	            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
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
        Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Brinquedo brinquedo = new Brinquedo();
        brinquedo.setNome(dto.getNome());
        brinquedo.setMarca(dto.getMarca());
        brinquedo.setCategoria(categoria);
        brinquedo.setDescricao(dto.getDescricao());
        brinquedo.setIdadeIdeal(dto.getIdadeIdeal());
        brinquedo.setImagemPrincipal(dto.getImagemPrincipal());
        brinquedo.setPreco(dto.getPreco());
        brinquedo.setDestaque(dto.isDestaque());

        return brinquedoRepository.save(brinquedo);
    }

    // SOBRECARGA DO MÉTODO SALVAR PARA INCLUIR TAMBÉM A IMAGEM
    public Brinquedo salvar(BrinquedoDTO dto, MultipartFile file) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Brinquedo brinquedo = new Brinquedo();
        brinquedo.setNome(dto.getNome());
        brinquedo.setMarca(dto.getMarca());
        brinquedo.setCategoria(categoria);
        brinquedo.setDescricao(dto.getDescricao());
        brinquedo.setIdadeIdeal(dto.getIdadeIdeal());
        brinquedo.setImagemPrincipal(salvarImagem(dto.getNome(), file));
        brinquedo.setPreco(dto.getPreco());
        brinquedo.setDestaque(dto.isDestaque());

        return brinquedoRepository.save(brinquedo);
    }

    //SALVA IMAGEM NO DIRETÓRIO
    public String salvarImagem(String nomeImagem, MultipartFile file) {
        try {
            String antigoNomeImagem = file.getOriginalFilename();
            String extensaoImagem = "";

            if (antigoNomeImagem != null && antigoNomeImagem.contains(".")) {
                extensaoImagem = antigoNomeImagem.substring(antigoNomeImagem.lastIndexOf("."));
            }

            String novoNomeImagem = nomeImagem + extensaoImagem;

            Path pastaUpload = Paths.get(PASTA_IMAGENS);
            Files.createDirectories(pastaUpload);

            Files.copy(file.getInputStream(), pastaUpload.resolve(novoNomeImagem), StandardCopyOption.REPLACE_EXISTING);

            return DIRETORIO_IMAGEM + novoNomeImagem;

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public Optional<Brinquedo> atualizar(int id, BrinquedoDTO dto) {
        Optional<Brinquedo> brinquedoOpt = brinquedoRepository.findById(id);

        if (brinquedoOpt.isPresent()) {
            Brinquedo brinquedo = brinquedoOpt.get();

            Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            String nomeAntigo = brinquedo.getNome();
            String nomeNovo = dto.getNome();

            if (!nomeAntigo.equals(nomeNovo) && brinquedo.getImagemPrincipal() != null) {
                try {
                    Path caminhoAntigo = Paths.get(PASTA_STATIC + brinquedo.getImagemPrincipal());
                    String extensao = "";

                    String nomeArquivoAntigo = caminhoAntigo.getFileName().toString();
                    int posPonto = nomeArquivoAntigo.lastIndexOf(".");
                    if (posPonto != -1) {
                        extensao = nomeArquivoAntigo.substring(posPonto);
                    }

                    Path caminhoNovo = caminhoAntigo.getParent().resolve(nomeNovo + extensao);
                    Files.move(caminhoAntigo, caminhoNovo, StandardCopyOption.REPLACE_EXISTING);

                    brinquedo.setImagemPrincipal(DIRETORIO_IMAGEM + nomeNovo + extensao);

                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            brinquedo.setNome(dto.getNome());
            brinquedo.setMarca(dto.getMarca());
            brinquedo.setCategoria(categoria);
            brinquedo.setDescricao(dto.getDescricao());
            brinquedo.setIdadeIdeal(dto.getIdadeIdeal());
            brinquedo.setPreco(dto.getPreco());
            brinquedo.setDestaque(dto.isDestaque());

            brinquedoRepository.save(brinquedo);
        }

        return brinquedoOpt;
    }

    public Optional<Brinquedo> atualizarImagem(int id, BrinquedoDTO dto, MultipartFile file) {
        Optional<Brinquedo> brinquedoOpt = brinquedoRepository.findById(id);
        if (brinquedoOpt.isPresent()) {
            Brinquedo brinquedo = brinquedoOpt.get();
            brinquedo.setImagemPrincipal(salvarImagem(brinquedo.getNome(), file));
            brinquedoRepository.save(brinquedo);
        }
        return brinquedoOpt;
    }

    public void excluir(int id) {
        Optional<Brinquedo> brinquedoOpt = brinquedoRepository.findById(id);

        if (brinquedoOpt.isPresent()) {
            Brinquedo brinquedo = brinquedoOpt.get();

            if (brinquedo.getImagemPrincipal() != null) {
                try {
                    Path caminhoImagem = Paths.get(PASTA_STATIC + brinquedo.getImagemPrincipal());
                    Files.deleteIfExists(caminhoImagem);
                } catch (IOException e) {
                    System.err.println("Não foi possível excluir a imagem: " + e.getMessage());
                }
            }

            brinquedoRepository.deleteById(id);
        }
    }
}