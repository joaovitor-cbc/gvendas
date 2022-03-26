package com.gvendas.gestaovendas.services.exception;

public class ProdutoSemEstoqueException extends RuntimeException {
    public ProdutoSemEstoqueException(String message) {
        super(message);
    }
}
