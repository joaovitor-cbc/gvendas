package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(tags = "Produto")
@RestController
@RequestMapping("produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @ApiOperation(value = "Buscar produto")
    @GetMapping(value = "{codigo}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.buscarPorCodigo(codigo));
    }

    @ApiOperation(value = "Listar produto")
    @GetMapping
    public ResponseEntity<List<Produto>> listaProduto() {
        return ResponseEntity.ok().body(service.listaProduto());
    }


    @ApiOperation(value = "Salvar produto")
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

    @ApiOperation(value = "Atualizar produto")
    @PutMapping(value = "{codigo}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long codigo, @RequestBody Produto produto) {
        service.atualizarProduto(codigo, produto);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Apagar produto")
    @DeleteMapping(value = "{codigo}")
    public ResponseEntity<Void> apagarProduto(@PathVariable Long codigo) {
        service.apagarProduto(codigo);
        return ResponseEntity.noContent().build();
    }
}