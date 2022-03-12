package com.gvendas.gestaovendas.dtos;

import java.io.Serializable;

public class CategoriaModelDTO implements Serializable {

    private Long codigo;
    private String nome;

    public CategoriaModelDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public CategoriaModelDTO() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
