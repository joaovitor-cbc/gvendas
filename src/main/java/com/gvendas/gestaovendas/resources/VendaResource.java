package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dtos.venda.VendaRequestDTO;
import com.gvendas.gestaovendas.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Venda")
@RestController
@RequestMapping("vendas")
public class VendaResource {

    @Autowired
    private VendaService service;

    @ApiOperation(value = "Listar venda por cliente", response = ClienteVendaResponseDTO.class, httpMethod = "GET",
            produces = "json", protocols = "http", nickname = "listarVendaPorCliente")
    @GetMapping(value = "cliente/{codigo}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.listarVendaPorCliente(codigo));
    }

    @ApiOperation(value = "Listar venda por codigo", response = ClienteVendaResponseDTO.class, httpMethod = "GET",
            produces = "json", protocols = "http", nickname = "listarVendaPorCodigo")
    @GetMapping(value = "/{codigo}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigo){
        return ResponseEntity.ok().body(service.listarVendaPorCodigo(codigo));
    }

    @ApiOperation(value = "Salvar venda", response = ClienteVendaResponseDTO.class, httpMethod = "POST",
            produces = "json", protocols = "http", nickname = "salvarVenda")
    @PostMapping(value = "/{clienteCodigo}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteVendaResponseDTO> salvarVenda(@PathVariable Long clienteCodigo,
                                                               @Valid @RequestBody VendaRequestDTO vendaDto){
        return ResponseEntity.ok().body(service.salvar(clienteCodigo, vendaDto));
    }
}
