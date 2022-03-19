package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.cliente.ClienteRequestDTO;
import com.gvendas.gestaovendas.dtos.cliente.ClienteResponseDTO;
import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.repositories.ClienteRepository;
import com.gvendas.gestaovendas.services.exception.ClienteNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

    public Cliente buscarPorCodigo(Long codigo) {
        Optional<Cliente> clienteOpt = repository.findById(codigo);
        if (clienteOpt.isEmpty())
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        return clienteOpt.get();
    }

    public void telefoneJaCadastrado(String telefone) throws SQLIntegrityConstraintViolationException {
        Optional<Cliente> clienteOpt = repository.findByTelefone(telefone);
        if (clienteOpt.isPresent())
            throw new SQLIntegrityConstraintViolationException("Já existe um cliente cadastrato com esse numero de telefone");
    }

    public ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteInsertDTO) throws SQLIntegrityConstraintViolationException {
        telefoneJaCadastrado(clienteInsertDTO.getTelefone());
        Cliente cliente = clienteInsertDtoToEntity(clienteInsertDTO);
        cliente.setAtivo(true);
        return entityToClienteModelDto(repository.save(cliente));

    }

    public ClienteResponseDTO atualizar(ClienteRequestDTO clienteInsertDTO, Long codigo) throws SQLIntegrityConstraintViolationException {
        Cliente cliente = buscarPorCodigo(codigo);
        validarAtualizacao(clienteInsertDTO, cliente);
        BeanUtils.copyProperties(clienteInsertDTO, cliente);
        return entityToClienteModelDto(repository.save(cliente));
    }

    public void validarAtualizacao(ClienteRequestDTO clienteInsertDTO, Cliente cliente) throws SQLIntegrityConstraintViolationException {
        if(cliente.getAtivo().equals(false))
            throw new IllegalArgumentException("Cliente com perfil desativado");

        if (!cliente.getTelefone().equals(clienteInsertDTO.getTelefone()))
            telefoneJaCadastrado(clienteInsertDTO.getTelefone());
    }

    private Cliente clienteInsertDtoToEntity(ClienteRequestDTO clienteInsertDTO) {
        return modelMapper.map(clienteInsertDTO, Cliente.class);
    }

    private ClienteResponseDTO entityToClienteModelDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public void apagarCliente(Long codigo) {
        Cliente cliente = buscarPorCodigo(codigo);
        repository.delete(cliente);
    }
}