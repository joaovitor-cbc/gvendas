package com.gvendas.gestaovendas.resources.exception;

import java.io.Serializable;

public class ErroPadrao implements Serializable {

    private String dataHora;
    private Integer status;
    private String erro;
    private String mensagem;
    private String path;

    public ErroPadrao(String dataHora, Integer status, String erro, String mensagem, String path) {
        this.dataHora = dataHora;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}