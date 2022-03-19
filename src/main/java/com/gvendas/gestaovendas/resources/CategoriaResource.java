package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.categoria.CategoriaRequestDTO;
import com.gvendas.gestaovendas.dtos.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.services.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@Api(tags = "Categoria")
@RestController
@RequestMapping(value = "categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;


    @ApiOperation(value = "Salvar categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvarCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaInsertDTO){
        CategoriaResponseDTO categoriaSalva = service.salvarCategoria(categoriaInsertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @ApiOperation(value = "Lista de categoria")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listaCategoria(){
        return ResponseEntity.ok().body(service.buscarTudo());
    }

    @ApiOperation(value = "Buscar categoria")
    @GetMapping(value = "{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarCategoria(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.buscarPorCodigoModelCategoria(codigo));
    }

    @ApiOperation(value = "Atualizar categoria")
    @PutMapping(value = "{codigo}")
    public ResponseEntity<Void> atualizarCategoria(@PathVariable Long codigo,@Valid @RequestBody CategoriaRequestDTO categoriaInsertDTO){
        service.atualizarCagetoria(codigo, categoriaInsertDTO);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Apagar categoria")
    @DeleteMapping(value = "{codigo}")
    public ResponseEntity<Void> apagarCategoria(@PathVariable Long codigo) throws SQLIntegrityConstraintViolationException {
        service.apagarCategoria(codigo);
        return ResponseEntity.noContent().build();
    }
}