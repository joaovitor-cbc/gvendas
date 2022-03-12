package com.gvendas.gestaovendas.dtos;

import java.io.Serializable;

public class ClienteInsertDTO implements Serializable {

    private String nome;
    private String telefone;

    public ClienteInsertDTO() {}

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