package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.endereco.EnderecoRequestDTO;
import com.gvendas.gestaovendas.dtos.endereco.EnderecoResponseDTO;
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
    public ResponseEntity<EnderecoResponseDTO> salvarEndereco(@PathVariable("codigo") Long codigo,
                                                              @Valid @RequestBody EnderecoRequestDTO enderecoInsertDTO) throws SQLIntegrityConstraintViolationException {
        EnderecoResponseDTO enderecoModelDTO = service.salvarEndereco(enderecoInsertDTO, codigo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(enderecoModelDTO.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(enderecoModelDTO);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> ApagarEndereco(@PathVariable("codigo") Long codigo, @RequestBody EnderecoResponseDTO enderecoModelDTO) {
        service.apagarEndereco(enderecoModelDTO, codigo);
        return ResponseEntity.noContent().build();
    }
}