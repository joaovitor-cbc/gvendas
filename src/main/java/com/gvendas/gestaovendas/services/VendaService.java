package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dtos.venda.ItemVendaReponseDTO;
import com.gvendas.gestaovendas.dtos.venda.VendaReponseDTO;
import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.models.ItemVenda;
import com.gvendas.gestaovendas.models.Venda;
import com.gvendas.gestaovendas.repositories.ItemVendaRepository;
import com.gvendas.gestaovendas.repositories.VendaRepository;
import com.gvendas.gestaovendas.services.exception.VendaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente){
        Cliente cliente = validarClienteExiste(codigoCliente);
        List<VendaReponseDTO> vendasDTO = repository.findByClienteCodigo(cliente.getCodigo())
                .stream().map(this::criandoVendaResponseDTO).toList();
        return new ClienteVendaResponseDTO(cliente.getNome(), vendasDTO);
    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigo){
        Venda venda = validarVendaExiste(codigo);
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(criandoVendaResponseDTO(venda)));
    }

    private Venda validarVendaExiste(Long codigo) {
        Optional<Venda> vendaOpt = repository.findById(codigo);
        if (vendaOpt.isEmpty())
            throw new VendaNaoEncontradaException(String.format("Venda de codigo %s não encontrada", codigo));
        return vendaOpt.get();
    }

    private Cliente validarClienteExiste(Long codigoCliente) {
        return clienteService.buscarPorCodigo(codigoCliente);
    }

    private VendaReponseDTO criandoVendaResponseDTO(Venda venda){
        List<ItemVendaReponseDTO> itensVendaDTO = itemVendaRepository.findByVendaCodigo(venda.getCodigo())
                .stream().map(this::criandoItemVendaReponseDTO).toList();
        return new VendaReponseDTO(venda.getCodigo(), venda.getData(), itensVendaDTO);
    }

    private ItemVendaReponseDTO criandoItemVendaReponseDTO(ItemVenda itemVenda){
        return new ItemVendaReponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(),
                itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }
}
