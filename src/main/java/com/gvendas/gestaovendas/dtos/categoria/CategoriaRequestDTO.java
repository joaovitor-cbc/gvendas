package com.gvendas.gestaovendas.dtos.categoria;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class CategoriaRequestDTO implements Serializable {

    @NotBlank(message = "Nome não deve ser vazio")
    @Length(min = 3, max = 50, message = "Nome deve conter no minimo 3 e no maximo 50 caracteres")
    private String nome;

    public CategoriaRequestDTO(String nome) {
        this.nome = nome;
    }

    public CategoriaRequestDTO() {
    }

    public String getNome() {
        return nome;
    }
}