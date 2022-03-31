package com.gvendas.gestaovendas.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gvendas.gestaovendas.dtos.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dtos.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produto")
@RestController
@RequestMapping("produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @ApiOperation(value = "Buscar produto")
    @GetMapping(value = "{codigo}")
    public ResponseEntity<ProdutoResponseDTO> buscarProduto(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(service.buscarPorCodigoModelProduto(codigo));
    }

    @ApiOperation(value = "Listar produto")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listaProduto() {
        return ResponseEntity.ok().body(service.listaProduto());
    }


    @ApiOperation(value = "Salvar produto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvarProduto(@Valid @RequestBody ProdutoRequestDTO produtoInsertDTO) {
        ProdutoResponseDTO produtoSalvo = service.salvarProduto(produtoInsertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @ApiOperation(value = "Atualizar produto")
    @PutMapping(value = "{codigo}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long codigo, @RequestBody ProdutoRequestDTO produtoInsertDTO) {
        service.atualizarProduto(codigo, produtoInsertDTO);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Apagar produto")
    @DeleteMapping(value = "{codigo}")
    public ResponseEntity<Void> apagarProduto(@PathVariable Long codigo) {
        service.apagarProduto(codigo);
        return ResponseEntity.noContent().build();
    }
}