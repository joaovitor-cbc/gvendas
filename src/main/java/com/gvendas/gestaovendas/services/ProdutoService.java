package com.gvendas.gestaovendas.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.dtos.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dtos.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;
import com.gvendas.gestaovendas.services.exception.ProdutoDuplicadoException;
import com.gvendas.gestaovendas.services.exception.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    public ProdutoResponseDTO buscarPorCodigoModelProduto(Long codigo) {
        Produto produto = repo.findById(codigo)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(String.format("Produto com codigo %s não encontrado",codigo)));
        return entidadeParaDtoModel(produto);
    }

    public List<ProdutoResponseDTO> listaProduto() {
        return listaEntidadeParaListaDtoModel(repo.findAll());
    }

    public ProdutoResponseDTO salvarProduto(ProdutoRequestDTO produtoInsertDTO) {
        Produto produto = dtoInsertParaEntidade(produtoInsertDTO);
        Categoria categoria = categoriaService.buscarPorCodigoEntidade(produtoInsertDTO.getCategoriaCodigo());
        produto.setCategoria(categoria);
        produtoEhDuplicado(produto);
        return entidadeParaDtoModel(repo.save(produto));
    }

    protected Produto validarProdutoExiste(Long codigo){
        return repo.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(String.format("Produto com codigo %s não encontrado",codigo), 1));
    }

    public void produtoEhDuplicado(Produto produto) {
        Optional<Produto> produtoOpt = repo.findByDescricaoAndCategoriaCodigo(produto.getDescricao(), produto.getCategoria().getCodigo());
        if (produtoOpt.isPresent())
            throw new ProdutoDuplicadoException("Produto já cadastrado na base de dados para essa categoria: "
                    + produto.getCategoria().getNome());
    }

    public void atualizarProduto(Long codigo, ProdutoRequestDTO produtoInsertDTO) {
        Produto produtoSalvo = dtoModelParaEntidade(buscarPorCodigoModelProduto(codigo));
        Categoria categoria = categoriaService.buscarPorCodigoEntidade(produtoInsertDTO.getCategoriaCodigo());
        Produto produto = dtoInsertParaEntidade(produtoInsertDTO);
        produto.setCategoria(categoria);
        produtoEhDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
        repo.save(produtoSalvo);
    }

    protected Produto atualizarEstoqueProduto(Produto produto) {
        Produto produtoSalvo = validarProdutoExiste(produto.getCodigo());
        BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
        return repo.save(produtoSalvo);
    }

    public void apagarProduto(Long codigo) {
        Produto produto = dtoModelParaEntidade(buscarPorCodigoModelProduto(codigo));
        repo.delete(produto);
    }

    public Produto dtoInsertParaEntidade(ProdutoRequestDTO dtoInsert) {
        return modelMapper.map(dtoInsert, Produto.class);
    }

    public Produto dtoModelParaEntidade(ProdutoResponseDTO produtoModelDTO) {
        return modelMapper.map(produtoModelDTO, Produto.class);
    }

    public ProdutoResponseDTO entidadeParaDtoModel(Produto entidade) {
        return modelMapper.map(entidade, ProdutoResponseDTO.class);
    }

    public List<ProdutoResponseDTO> listaEntidadeParaListaDtoModel(List<Produto> listaCategoria) {
        return listaCategoria.stream().map(this::entidadeParaDtoModel).toList();
    }
}