package com.gvendas.gestaovendas.resources.exceptions;

import java.io.Serializable;

public class CampoMensagem implements Serializable {

    private String campo;
    private String mensagem;

    public CampoMensagem(String campoNome, String mensagem) {
        this.campo = campoNome;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}