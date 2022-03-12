package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;


    @GetMapping(value = "{codigo}")
    public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.buscarPorId(codigo));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> buscarListaCategoria(){
        return ResponseEntity.ok().body(service.buscarTudo());
    }
}