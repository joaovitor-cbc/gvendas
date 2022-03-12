package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "{codigo}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.buscarPorCodigo(codigo));
    }
}