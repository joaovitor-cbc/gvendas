package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.EnderecoInsertDTO;
import com.gvendas.gestaovendas.dtos.EnderecoModelDTO;
import com.gvendas.gestaovendas.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @PostMapping("/{codigo}")
    public ResponseEntity<EnderecoModelDTO> salvarEndereco(@PathVariable("codigo") Long codigo,
                                                           @Valid @RequestBody EnderecoInsertDTO enderecoInsertDTO) throws SQLIntegrityConstraintViolationException {
        EnderecoModelDTO enderecoModelDTO = service.salvarEndereco(enderecoInsertDTO, codigo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enderecoModelDTO.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(enderecoModelDTO);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> ApagarEndereco(@PathVariable("codigo") Long codigo, @RequestBody EnderecoModelDTO enderecoModelDTO) {
        service.apagarEndereco(enderecoModelDTO, codigo);
        return ResponseEntity.noContent().build();
    }
}