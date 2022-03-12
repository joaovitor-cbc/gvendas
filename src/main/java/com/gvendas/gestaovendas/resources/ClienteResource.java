package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.ClienteInsertDTO;
import com.gvendas.gestaovendas.dtos.ClienteModelDTO;
import com.gvendas.gestaovendas.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteModelDTO> salvarCliente(@Valid @RequestBody ClienteInsertDTO clienteInsertDTO) throws SQLIntegrityConstraintViolationException {
        ClienteModelDTO clienteModelDTO = service.salvarCliente(clienteInsertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteModelDTO.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(clienteModelDTO);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Void> atualizarCliente(@Valid @RequestBody ClienteInsertDTO clienteInsertDTO, @PathVariable("codigo") Long codigo) throws SQLIntegrityConstraintViolationException {
        service.atualizar(clienteInsertDTO, codigo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> ApagarCliente(@PathVariable("codigo") Long codigo) {
        service.apagarCliente(codigo);
        return ResponseEntity.noContent().build();
    }
}