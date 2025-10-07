package br.edu.fatecguarulhos.catalogobrinquedos.dto;

import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Brinquedo;
import br.edu.fatecguarulhos.catalogobrinquedos.model.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class BrinquedoDTO {

    private int id; // novo campo

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A idade recomendada é obrigatória")
    private String idadeIdeal;

    @NotBlank(message = "A imagem é obrigatória")
    private String imagemPrincipal;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "O preço não pode ser negativo")
    private double preco;

    private boolean destaque;

    // Construtor vazio
    public BrinquedoDTO() { }

    // Construtor com todos os campos
    public BrinquedoDTO(int id, String nome, String marca, Categoria categoria, String descricao, String idadeIdeal,
                        String imagemPrincipal, double preco, boolean destaque) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.descricao = descricao;
        this.idadeIdeal = idadeIdeal;
        this.imagemPrincipal = imagemPrincipal;
        this.preco = preco;
        this.destaque = destaque;
    }

    // Construtor que recebe um Brinquedo
    public BrinquedoDTO(Brinquedo brinquedo) {
        this.id = brinquedo.getId(); // adiciona o id
        this.nome = brinquedo.getNome();
        this.marca = brinquedo.getMarca();
        this.categoria = brinquedo.getCategoria();
        this.descricao = brinquedo.getDescricao();
        this.idadeIdeal = brinquedo.getIdadeIdeal();
        this.imagemPrincipal = brinquedo.getImagemPrincipal();
        this.preco = brinquedo.getPreco();
        this.destaque = brinquedo.isDestaque();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria; 
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdadeIdeal() {
        return idadeIdeal;
    }
    public void setIdadeIdeal(String idadeIdeal) {
        this.idadeIdeal = idadeIdeal;
    }

    public String getImagemPrincipal() {
        return imagemPrincipal;
    }
    public void setImagemPrincipal(String imagemPrincipal) {
        this.imagemPrincipal = imagemPrincipal;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDestaque() {
        return destaque;
    }
    public void setDestaque(boolean destaque) {
        this.destaque = destaque;
    }
}
