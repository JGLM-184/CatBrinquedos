package br.edu.fatecguarulhos.catalogobrinquedos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.fatecguarulhos.catalogobrinquedos.dto.BrinquedoDTO;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.repository.BrinquedoRepository;


@Service
public class BrinquedoService {

	@Autowired
	private BrinquedoRepository brinquedoRepository;
	
	private final String PASTA_STATIC = "src/main/resources/static";
	private final String PASTA_IMAGENS = "src/main/resources/static/imagens/brinquedos/";
	private final String DIRETORIO_IMAGEM = "/imagens/brinquedos/";
	
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
	
	//Sobrecarga do método salvar para incluir também a imagem
	public Brinquedo salvar(BrinquedoDTO dto, MultipartFile file) {
		Brinquedo brinquedo = new Brinquedo();
		
		brinquedo.setNome(dto.getNome());
		brinquedo.setMarca(dto.getMarca());
		brinquedo.setCategoria(dto.getCategoria());
		brinquedo.setIdadeIdeal(dto.getIdadeIdeal());
		brinquedo.setImagemPrincipal(salvarImagem(dto.getNome(), file));
		brinquedo.setPreco(dto.getPreco());
		brinquedo.setDestaque(dto.isDestaque());
		
		return brinquedoRepository.save(brinquedo);
	}
	
	//Salva imagem no diretório /src/main/resources/static/imagens/brinquedos do projeto
	//O nome da imagem será o mesmo que o do campo "nome".
	public String salvarImagem(String nomeImagem, MultipartFile file){
		 try {
			String antigoNomeImagem = file.getOriginalFilename();
			String extensaoImagem = "";
			
	        if (antigoNomeImagem != null && antigoNomeImagem.contains(".")) {
	            extensaoImagem = antigoNomeImagem.substring(antigoNomeImagem.lastIndexOf("."));
	        }
	        
	        String novoNomeImagem = nomeImagem + extensaoImagem;

            Path pastaUpload = Paths.get(PASTA_IMAGENS); // Cria um objeto do diretório para imagens
            Files.createDirectories(pastaUpload); // Cria o diretório caso ele não exista no projeto
            
            Files.copy(file.getInputStream(), pastaUpload.resolve(novoNomeImagem), StandardCopyOption.REPLACE_EXISTING);
            //Salva a imagem no diretório ou a substitui caso já exista uma de mesmo nome. 
            
            return DIRETORIO_IMAGEM + novoNomeImagem; 
            //Retorna o diretório em que a imagem está salva.
            
        } catch (IOException e) {
            return e.getMessage();
        }
	}
	
	public Optional<Brinquedo> atualizar(int id, BrinquedoDTO dto) {
		Optional<Brinquedo> brinquedoOpt = brinquedoRepository.findById(id);
		if (brinquedoOpt.isPresent()) {
			Brinquedo brinquedo = brinquedoOpt.get();
			
	        String nomeAntigo = brinquedo.getNome();
	        String nomeNovo = dto.getNome();

	        // Verifica se o nome foi alterado e se há imagem salva, caso sim, renomeia a imagem.
	        if (!nomeAntigo.equals(nomeNovo) && brinquedo.getImagemPrincipal() != null) {
	            try {
	                Path caminhoAntigo = Paths.get(PASTA_STATIC + brinquedo.getImagemPrincipal());
	                String extensao = "";
	                                
	                String nomeArquivoAntigo = caminhoAntigo.getFileName().toString();
	                
	                int posPonto = nomeArquivoAntigo.lastIndexOf(".");
	                if (posPonto != -1) {
	                    extensao = nomeArquivoAntigo.substring(posPonto);
	                }
	                
	                // Define novo caminho
	                Path caminhoNovo = caminhoAntigo.getParent().resolve(nomeNovo + extensao);

	                // Renomeia o arquivo
	                Files.move(caminhoAntigo, caminhoNovo, StandardCopyOption.REPLACE_EXISTING);

	                // Atualiza o caminho no objeto
	                brinquedo.setImagemPrincipal(DIRETORIO_IMAGEM + nomeNovo + extensao);

	            } catch (IOException e) {
	                throw new RuntimeException(e.getMessage());
	            }
	        }
	        
			brinquedo.setNome(dto.getNome());
			brinquedo.setMarca(dto.getMarca());
			brinquedo.setCategoria(dto.getCategoria());
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
	
	public void excluir (int id) {
		Optional<Brinquedo> brinquedoOpt = brinquedoRepository.findById(id);

	    if (brinquedoOpt.isPresent()) {
	        Brinquedo brinquedo = brinquedoOpt.get();

	        // Remove a imagem do sistema de arquivos
	        if (brinquedo.getImagemPrincipal() != null) {
	            try {
	                // Extrai apenas o nome do arquivo da imagem
	                Path caminhoImagem = Paths.get(PASTA_STATIC + brinquedo.getImagemPrincipal());
	                System.out.println(String.valueOf(caminhoImagem));
	                Files.deleteIfExists(caminhoImagem);
	            } catch (IOException e) {
	                // Registra o erro em log, mas continua a exclusão do brinquedo
	                System.err.println("Não foi possível excluir a imagem: " + e.getMessage());
	            }
	        }

	        // Remove o brinquedo do banco
	        brinquedoRepository.deleteById(id);
	    }
	}
	
}
