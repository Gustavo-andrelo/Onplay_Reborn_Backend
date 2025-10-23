package com.onplay.reborn.onplayreborn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String nome;
    
    @NotBlank
    @Column(nullable = false, length = 1000)
    private String descricao;
    
    @NotBlank
    @Column(nullable = false)
    private String genero;
    
    @NotNull
    @Min(1900)
    @Max(2030)
    @Column(nullable = false)
    private Integer ano;
    
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer duracao; // em minutos
    
    @NotBlank
    @Column(nullable = false)
    private String faixaEtaria;
    
    @NotBlank
    @Column(nullable = false)
    private String poster;
    
    @NotBlank
    @Column(nullable = false)
    private String linkTrailer;
    
    public Movie() {}
    
    public Movie(String nome, String descricao, String genero, Integer ano, Integer duracao, 
                 String faixaEtaria, String poster, String linkTrailer) {
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