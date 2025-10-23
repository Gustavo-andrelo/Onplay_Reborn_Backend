package com.onplay.reborn.onplayreborn.dtos;

import jakarta.validation.constraints.*;

public class MovieRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;
    
    @NotBlank(message = "Gênero é obrigatório")
    private String genero;
    
    @NotNull(message = "Ano é obrigatório")
    @Min(value = 1900, message = "Ano deve ser maior que 1900")
    @Max(value = 2030, message = "Ano deve ser menor que 2030")
    private Integer ano;
    
    @NotNull(message = "Duração é obrigatória")
    @Min(value = 1, message = "Duração deve ser maior que 0")
    private Integer duracao;
    
    @NotBlank(message = "Faixa etária é obrigatória")
    private String faixaEtaria;
    
    @NotBlank(message = "Poster é obrigatório")
    private String poster;
    
    @NotBlank(message = "Link do trailer é obrigatório")
    private String linkTrailer;
    
    public MovieRequestDTO() {}
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    
    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    
    public String getFaixaEtaria() { return faixaEtaria; }
    public void setFaixaEtaria(String faixaEtaria) { this.faixaEtaria = faixaEtaria; }
    
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    
    public String getLinkTrailer() { return linkTrailer; }
    public void setLinkTrailer(String linkTrailer) { this.linkTrailer = linkTrailer; }
}