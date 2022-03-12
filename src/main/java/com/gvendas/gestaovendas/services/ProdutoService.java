package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;
import com.gvendas.gestaovendas.services.exception.ProdutoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Produto buscarPorCodigo(Long codigo) {
        return repo.findById(codigo).orElseThrow(() -> new ProdutoNaoEncontradoException(
                "Produto com codigo: " + codigo + " não existe."));
    }
}