package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.ClienteInsertDTO;
import com.gvendas.gestaovendas.dtos.ClienteModelDTO;
import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public ClienteModelDTO salvarCliente(ClienteInsertDTO clienteInsertDTO) throws SQLIntegrityConstraintViolationException {
        Optional<Cliente> clienteOpt = repository.findByTelefone(clienteInsertDTO.getTelefone());
        if (clienteOpt.isPresent())
            throw new SQLIntegrityConstraintViolationException("Cliente já cadastrato com esse numero de telefone");
        Cliente cliente = clienteInsertDtoToEntity(clienteInsertDTO);
        cliente.setAtivo(true);
        return entityToClienteModelDto(repository.save(cliente));

    }

    private Cliente clienteInsertDtoToEntity(ClienteInsertDTO clienteInsertDTO) {
        return modelMapper.map(clienteInsertDTO, Cliente.class);
    }

    private ClienteModelDTO entityToClienteModelDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModelDTO.class);
    }
}