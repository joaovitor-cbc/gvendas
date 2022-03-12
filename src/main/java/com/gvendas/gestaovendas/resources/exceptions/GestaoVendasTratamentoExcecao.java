package com.gvendas.gestaovendas.resources.exceptions;

import com.gvendas.gestaovendas.services.exceptions.CategoriaNaoEncontrada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GestaoVendasTratamentoExcecao {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErroPadrao> ArgumentoNaoValido(MethodArgumentNotValidException ex, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ValidacaoErro validacaoErro = new ValidacaoErro(sdf.format(new Date()), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.", ex.getMessage(), request.getRequestURI());
        ex.getBindingResult().getFieldErrors().forEach(campo ->
                validacaoErro.addErros(campo.getField(), campo.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validacaoErro);
    }

    @ExceptionHandler(CategoriaNaoEncontrada.class)
    protected ResponseEntity<ErroPadrao> ArgumentoNaoValido(CategoriaNaoEncontrada ex, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ErroPadrao erroPadrao = new ErroPadrao(sdf.format(new Date()), HttpStatus.NOT_FOUND.value(), "Não encontrado.",
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroPadrao);
    }
}
