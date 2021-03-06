package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.endereco.EnderecoRequestDTO;
import com.gvendas.gestaovendas.dtos.endereco.EnderecoResponseDTO;
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


    public EnderecoResponseDTO salvarEndereco(EnderecoRequestDTO enderecoInsertDTO, Long clienteCodigo) throws SQLIntegrityConstraintViolationException {
        Cliente cliente = clienteService.buscarPorCodigo(clienteCodigo);
        enderecoJaCadastrado(enderecoInsertDTO, cliente);
        Endereco endereco = enderecoInsertDtoToEntity(enderecoInsertDTO);
        endereco.setCliente(cliente);
        endereco = repository.save(endereco);
        cliente.getEnderecos().add(endereco);
        clienteRepository.save(cliente);
        return entityToEnderecoModelDto(endereco);
    }

    private void enderecoJaCadastrado(EnderecoRequestDTO enderecoInsertDTO, Cliente cliente) throws SQLIntegrityConstraintViolationException {
        Optional<Endereco> enderecoOpt = repository.findEndereco(enderecoInsertDTO.getLogradouro(), enderecoInsertDTO.getNumero(), enderecoInsertDTO.getComplemento(),
                enderecoInsertDTO.getBairro(), enderecoInsertDTO.getCep(), enderecoInsertDTO.getCidade(), enderecoInsertDTO.getEstado(),
                cliente);
        if (enderecoOpt.isPresent())
            throw new SQLIntegrityConstraintViolationException("Esse endere??o j?? est?? cadastrato para esse cliente.");
    }

    public void apagarEndereco(EnderecoResponseDTO enderecoModelDTO, Long codigoCliente) {
        Optional<Endereco> enderecoOpt = repository.findByCodigoAndClienteCodigo(enderecoModelDTO.getCodigo(), codigoCliente);

        if (enderecoOpt.isEmpty())
            throw new IllegalArgumentException("N??o existe nenhum endereco com esse codigo "+enderecoModelDTO.getCodigo()+
                    " cadastrado para esse cliente.");

        Endereco endereco = enderecoOpt.get();
        Cliente cliente = endereco.getCliente();
        cliente.getEnderecos().remove(endereco);
        clienteRepository.save(cliente);
        repository.delete(endereco);
    }

    private Endereco enderecoInsertDtoToEntity(EnderecoRequestDTO enderecoInsertDTO) {
        return modelMapper.map(enderecoInsertDTO, Endereco.class);
    }

    private EnderecoResponseDTO entityToEnderecoModelDto(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoResponseDTO.class);
    }
}