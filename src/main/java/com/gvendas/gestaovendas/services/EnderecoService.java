package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.EnderecoInsertDTO;
import com.gvendas.gestaovendas.dtos.EnderecoModelDTO;
import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.models.Endereco;
import com.gvendas.gestaovendas.repositories.ClienteRepository;
import com.gvendas.gestaovendas.repositories.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;


    public EnderecoModelDTO salvarEndereco(EnderecoInsertDTO enderecoInsertDTO, Long clienteCodigo) throws SQLIntegrityConstraintViolationException {
        Cliente cliente = clienteService.buscarPorCodigo(clienteCodigo);
        enderecoJaCadastrado(enderecoInsertDTO, cliente);
        Endereco endereco = enderecoInsertDtoToEntity(enderecoInsertDTO);
        endereco.setCliente(cliente);
        endereco = repository.save(endereco);
        cliente.getEnderecos().add(endereco);
        clienteRepository.save(cliente);
        return entityToEnderecoModelDto(endereco);
    }

    private void enderecoJaCadastrado(EnderecoInsertDTO enderecoInsertDTO, Cliente cliente) throws SQLIntegrityConstraintViolationException {
        Optional<Endereco> enderecoOpt = repository.findEndereco(enderecoInsertDTO.getLogradouro(), enderecoInsertDTO.getNumero(), enderecoInsertDTO.getComplemento(),
                enderecoInsertDTO.getBairro(), enderecoInsertDTO.getCep(), enderecoInsertDTO.getCidade(), enderecoInsertDTO.getEstado(),
                cliente);
        if (enderecoOpt.isPresent())
            throw new SQLIntegrityConstraintViolationException("Esse endereço já está cadastrato para esse cliente.");
    }

    private Endereco enderecoInsertDtoToEntity(EnderecoInsertDTO enderecoInsertDTO) {
        return modelMapper.map(enderecoInsertDTO, Endereco.class);
    }

    private EnderecoModelDTO entityToEnderecoModelDto(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoModelDTO.class);
    }
}