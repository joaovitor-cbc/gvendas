package com.gvendas.gestaovendas.services.exception;

public class CategoriaNaoEncontradaException extends RuntimeException{

    public CategoriaNaoEncontradaException(String message) {
        super(message);
    }
}