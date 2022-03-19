package com.gvendas.gestaovendas.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErro extends ErroPadrao{

    private List<CampoMensagem> erros = new ArrayList<>();

    public ValidacaoErro(String dataHora, Integer status, String erro, String mensagem, String path) {
        super(dataHora, status, erro, mensagem, path);
    }

    public List<CampoMensagem> getErros() {
        return erros;
    }

    public void addErros(String campoNome, String mensagem){
        erros.add(new CampoMensagem(campoNome, mensagem));
    }
}