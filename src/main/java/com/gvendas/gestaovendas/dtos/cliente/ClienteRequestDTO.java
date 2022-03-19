package com.gvendas.gestaovendas.dtos.cliente;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ClienteRequestDTO implements Serializable {

    @NotBlank(message = "O campo nome não pode ser vazio ou nulo")
    private String nome;
    @NotBlank(message = "O campo telefone não pode ser vazio ou nulo")
    private String telefone;

    public ClienteRequestDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}