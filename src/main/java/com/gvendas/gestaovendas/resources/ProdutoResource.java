package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.ProdutoInsertDTO;
import com.gvendas.gestaovendas.dtos.ProdutoModelDTO;
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
    public ResponseEntity<ProdutoModelDTO> buscarProduto(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(service.buscarPorCodigoModelProduto(codigo));
    }

    @ApiOperation(value = "Listar produto")
    @GetMapping
    public ResponseEntity<List<ProdutoModelDTO>> listaProduto() {
        return ResponseEntity.ok().body(service.listaProduto());
    }


    @ApiOperation(value = "Salvar produto")
    @PostMapping
    public ResponseEntity<ProdutoModelDTO> salvarProduto(@Valid @RequestBody ProdutoInsertDTO produtoInsertDTO) {
        ProdutoModelDTO produtoSalvo = service.salvarProduto(produtoInsertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @ApiOperation(value = "Atualizar produto")
    @PutMapping(value = "{codigo}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long codigo, @RequestBody ProdutoInsertDTO produtoInsertDTO) {
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