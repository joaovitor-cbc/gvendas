package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;


    @PostMapping
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = service.salvarCategoria(categoria);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarListaCategoria(){
        return ResponseEntity.ok().body(service.buscarTudo());
    }

    @GetMapping(value = "{codigo}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.buscarPorCodigo(codigo));
    }

    @PutMapping(value = "{codigo}")
    public ResponseEntity<Void> atualizarCategoria(@PathVariable Long codigo, @RequestBody Categoria categoria){
        service.atualizar(codigo, categoria);
        return ResponseEntity.noContent().build();
    }
}