package com.gvendas.gestaovendas.dtos.categoria;

import java.io.Serializable;

public class CategoriaResponseDTO implements Serializable {

    private Long codigo;
    private String nome;

    public CategoriaResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public CategoriaResponseDTO() {
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
