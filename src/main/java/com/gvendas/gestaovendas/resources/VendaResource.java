package com.gvendas.gestaovendas.resources;

import com.gvendas.gestaovendas.dtos.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
