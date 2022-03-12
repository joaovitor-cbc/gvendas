package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.ClienteInsertDTO;
import com.gvendas.gestaovendas.dtos.ClienteModelDTO;
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

    public ClienteModelDTO salvarCliente(ClienteInsertDTO clienteInsertDTO) throws SQLIntegrityConstraintViolationException {
        telefoneJaCadastrado(clienteInsertDTO.getTelefone());
        Cliente cliente = clienteInsertDtoToEntity(clienteInsertDTO);
        cliente.setAtivo(true);
        return entityToClienteModelDto(repository.save(cliente));

    }

    public ClienteModelDTO atualizar(ClienteInsertDTO clienteInsertDTO, Long codigo) throws SQLIntegrityConstraintViolationException {
        Cliente cliente = buscarPorCodigo(codigo);
        validarAtualizacao(clienteInsertDTO, cliente);
        BeanUtils.copyProperties(clienteInsertDTO, cliente);
        return entityToClienteModelDto(repository.save(cliente));
    }

    public void validarAtualizacao(ClienteInsertDTO clienteInsertDTO, Cliente cliente) throws SQLIntegrityConstraintViolationException {
        if(cliente.getAtivo().equals(false))
            throw new IllegalArgumentException("Cliente com perfil desativado");

        if (!cliente.getTelefone().equals(clienteInsertDTO.getTelefone()))
            telefoneJaCadastrado(clienteInsertDTO.getTelefone());
    }

    private Cliente clienteInsertDtoToEntity(ClienteInsertDTO clienteInsertDTO) {
        return modelMapper.map(clienteInsertDTO, Cliente.class);
    }

    private ClienteModelDTO entityToClienteModelDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModelDTO.class);
    }

    public void apagarCliente(Long codigo) {
        Cliente cliente = buscarPorCodigo(codigo);
        repository.delete(cliente);
    }
}