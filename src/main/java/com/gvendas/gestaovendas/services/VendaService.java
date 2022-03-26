package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.venda.*;
import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.models.ItemVenda;
import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.models.Venda;
import com.gvendas.gestaovendas.repositories.ItemVendaRepository;
import com.gvendas.gestaovendas.repositories.VendaRepository;
import com.gvendas.gestaovendas.services.exception.ProdutoSemEstoqueException;
import com.gvendas.gestaovendas.services.exception.VendaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private ProdutoService produtoService;

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
        return clienteVendaResponseDTOBuilder(venda);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {RuntimeException.class, Exception.class})
    public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO vendaDto){
        Cliente cliente = validarClienteExiste(codigoCliente);
        validarProdutoExiste(vendaDto.getItensVendaDto());
        Venda venda = salvarVenda(cliente, vendaDto);
        return clienteVendaResponseDTOBuilder(venda);
    }

    private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaDto){
        Venda venda = repository.save(new Venda(null, vendaDto.getData(), cliente));
        List<ItemVenda> itemVendas = vendaDto.getItensVendaDto().stream()
                .map(obj -> criandoItemVenda(obj, venda)).toList();
        itemVendaRepository.saveAll(itemVendas);
        return venda;
    }

    private Venda atualizarVenda(Long codigoVenda, Cliente cliente, VendaRequestDTO vendaDto){
        Venda venda = repository.save(new Venda(codigoVenda, vendaDto.getData(), cliente));
        List<ItemVenda> itemVendas = vendaDto.getItensVendaDto().stream()
                .map(obj -> criandoItemVenda(obj, venda)).toList();
        itemVendaRepository.saveAll(itemVendas);
        return venda;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {RuntimeException.class, Exception.class})
    public ClienteVendaResponseDTO atualizar(Long codigoVenda, Long codigoCliente, VendaRequestDTO vendaDto){
        Venda venda = validarVendaExiste(codigoVenda);
        Cliente cliente = validarClienteExiste(codigoCliente);
        List<ItemVenda> itensVenda = itemVendaRepository.findByVendaCodigo(venda.getCodigo());
        devolverProdutoEstoque(itensVenda);
        validarProdutoExiste(vendaDto.getItensVendaDto());
        itemVendaRepository.deleteAll(itensVenda);
        Venda vendaAtualizada = atualizarVenda(venda.getCodigo(), cliente, vendaDto);
        return clienteVendaResponseDTOBuilder(vendaAtualizada);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {RuntimeException.class, Exception.class})
    public void deletarVenda(Long codigo){
        Venda venda = validarVendaExiste(codigo);
        List<ItemVenda> itensVendas = itemVendaRepository.findByVendaCodigo(venda.getCodigo());
        devolverProdutoEstoque(itensVendas);
        itemVendaRepository.deleteAll(itensVendas);
        repository.delete(venda);
    }

    private void devolverProdutoEstoque(List<ItemVenda> itensVendas){
        itensVendas.forEach(item -> {
            Produto produto = produtoService.validarProdutoExiste(item.getProduto().getCodigo());
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoService.atualizarEstoqueProduto(produto);
        });
    }

    private ClienteVendaResponseDTO clienteVendaResponseDTOBuilder(Venda venda) {
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(criandoVendaResponseDTO(venda)));
    }

    private void validarProdutoExiste(List<ItemVendaRequestDTO> itensVendaDto) {
        itensVendaDto.forEach(item -> {
            Produto produto = produtoService.validarProdutoExiste(item.getCodigoProduto());
            validarProdutoEstoque(produto, item.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoService.atualizarEstoqueProduto(produto);
        });
    }

    private void validarProdutoEstoque(Produto produto, Integer qtdVendaDto){
        if (qtdVendaDto > produto.getQuantidade())
            throw new ProdutoSemEstoqueException(String.format("A quantidade %s informada para o produto %s não está disponivel em estoque",
                                                qtdVendaDto, produto.getDescricao()));
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
        VendaReponseDTO vendaReponseDTO = new VendaReponseDTO(venda.getCodigo(), venda.getData(), itensVendaDTO);
        calcularValorTotalVenda(itensVendaDTO, vendaReponseDTO);
        return vendaReponseDTO;
    }

    private void calcularValorTotalVenda(List<ItemVendaReponseDTO> itensVendaDTO, VendaReponseDTO vendaReponseDTO){
        vendaReponseDTO.setValorTotal(new BigDecimal(0));
        itensVendaDTO.stream().forEach(item -> {
            int cc = 0;
            while (item.getQuantidade() > cc) {
                vendaReponseDTO.addValorTotal(item.getPrecoVendido());
                cc++;
            }
        });
    }

    private ItemVendaReponseDTO criandoItemVendaReponseDTO(ItemVenda itemVenda){
        return new ItemVendaReponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(),
                itemVenda.getPrecoVendido(), itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }

    private ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaDto, Venda venda){
        Produto produto = produtoService.validarProdutoExiste(itemVendaDto.getCodigoProduto());
        return new ItemVenda(null, new Produto(produto.getCodigo()), venda,
                itemVendaDto.getQuantidade(), produto.getPrecoVenda());
    }
}
