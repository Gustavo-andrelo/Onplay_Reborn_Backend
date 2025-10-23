package com.onplay.reborn.onplayreborn.dtos;

public class MovieResponseDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private String genero;
    private Integer ano;
    private Integer duracao;
    private String faixaEtaria;
    private String poster;
    private String linkTrailer;
    
    public MovieResponseDTO() {}
    
    public MovieResponseDTO(Long id, String nome, String descricao, String genero, Integer ano, 
                           Integer duracao, String faixaEtaria, String poster, String linkTrailer) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.genero = genero;
        this.ano = ano;
        this.duracao = duracao;
        this.faixaEtaria = faixaEtaria;
        this.poster = poster;
        this.linkTrailer = linkTrailer;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
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