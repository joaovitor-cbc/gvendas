package com.gvendas.gestaovendas.resources.exception;

import com.gvendas.gestaovendas.services.exception.CategoriaDuplicadaException;
import com.gvendas.gestaovendas.services.exception.CategoriaNaoEncontradaException;
import com.gvendas.gestaovendas.services.exception.ProdutoNaoEncontradoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.gvendas.gestaovendas.utils.DataHoraFormatada.formataDataHora;

@ControllerAdvice
public class GestaoVendasTratamentoExcecao {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErroPadrao> argumentoNaoValido(MethodArgumentNotValidException ex, HttpServletRequest request){
        ValidacaoErro validacaoErro = new ValidacaoErro(formataDataHora(new Date()), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.", ex.getMessage(), request.getRequestURI());
        ex.getBindingResult().getFieldErrors().forEach(campo ->
                validacaoErro.addErros(campo.getField(), campo.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validacaoErro);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErroPadrao> categoriaNaoEncontrada(CategoriaNaoEncontradaException ex, HttpServletRequest request){
        ErroPadrao erroPadrao = new ErroPadrao(formataDataHora(new Date()), HttpStatus.NOT_FOUND.value(), "Não encontrado.",
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroPadrao);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErroPadrao> acessoDadosResultadoVazio(EmptyResultDataAccessException ex, HttpServletRequest request){
        ErroPadrao erroPadrao = new ErroPadrao(formataDataHora(new Date()), HttpStatus.BAD_REQUEST.value(), "Não encontrado.",
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    @ExceptionHandler(CategoriaDuplicadaException.class)
    public ResponseEntity<ErroPadrao> categoriaDuplicada(CategoriaDuplicadaException ex, HttpServletRequest request){
        ErroPadrao erroPadrao = new ErroPadrao(formataDataHora(new Date()), HttpStatus.BAD_REQUEST.value(), "Integridade de dados.",
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> produtoNaoEncontrado(ProdutoNaoEncontradoException ex, HttpServletRequest request) {
        ErroPadrao erroPadrao = new ErroPadrao(formataDataHora(new Date()), HttpStatus.BAD_REQUEST.value(), "Não encontrado",
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadrao);
    }
}
