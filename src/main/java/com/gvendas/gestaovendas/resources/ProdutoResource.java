package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "{codigo}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.buscarPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listaProduto() {
        return ResponseEntity.ok().body(service.listaProduto());
    }

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@Valid @RequestBody Produto produto) {
        Produto produtoSalvo = service.salvarProduto(produto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @PutMapping(value = "{codigo}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long codigo, @RequestBody Produto produto) {
        service.atualizarProduto(codigo, produto);
        return ResponseEntity.noContent().build();
    }
}