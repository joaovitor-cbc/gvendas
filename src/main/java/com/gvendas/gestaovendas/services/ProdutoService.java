package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.ProdutoInsertDTO;
import com.gvendas.gestaovendas.dtos.ProdutoModelDTO;
import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;
import com.gvendas.gestaovendas.services.exception.ProdutoDuplicadoException;
import com.gvendas.gestaovendas.services.exception.ProdutoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    public ProdutoModelDTO buscarPorCodigoModelProduto(Long codigo) {
        Produto produto = repo.findById(codigo)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com codigo: " + codigo + " não existe."));
        return entidadeParaDtoModel(produto);
    }

    public List<ProdutoModelDTO> listaProduto() {
        return listaEntidadeParaListaDtoModel(repo.findAll());
    }

    public ProdutoModelDTO salvarProduto(ProdutoInsertDTO produtoInsertDTO) {
        Produto produto = dtoInsertParaEntidade(produtoInsertDTO);
        Categoria categoria = categoriaService.buscarPorCodigoEntidade(produtoInsertDTO.getCategoriaCodigo());
        produto.setCategoria(categoria);
        produtoEhDuplicado(produto);
        return entidadeParaDtoModel(repo.save(produto));
    }

    public void produtoEhDuplicado(Produto produto) {
        Optional<Produto> produtoOpt = repo.findByDescricaoAndCategoriaCodigo(produto.getDescricao(), produto.getCategoria().getCodigo());
        if (produtoOpt.isPresent())
            throw new ProdutoDuplicadoException("Produto já cadastrado na base de dados para essa categoria: "
                    + produto.getCategoria().getNome());
    }

    public void atualizarProduto(Long codigo, ProdutoInsertDTO produtoInsertDTO) {
        Produto produtoSalvo = dtoModelParaEntidade(buscarPorCodigoModelProduto(codigo));
        Categoria categoria = categoriaService.buscarPorCodigoEntidade(produtoInsertDTO.getCategoriaCodigo());
        Produto produto = dtoInsertParaEntidade(produtoInsertDTO);
        produto.setCategoria(categoria);
        produtoEhDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
        repo.save(produtoSalvo);
    }

    public void apagarProduto(Long codigo) {
        Produto produto = dtoModelParaEntidade(buscarPorCodigoModelProduto(codigo));
        repo.delete(produto);
    }

    private Produto dtoInsertParaEntidade(ProdutoInsertDTO dtoInsert) {
        return modelMapper.map(dtoInsert, Produto.class);
    }

    public Produto dtoModelParaEntidade(ProdutoModelDTO produtoModelDTO) {
        return modelMapper.map(produtoModelDTO, Produto.class);
    }

    private ProdutoModelDTO entidadeParaDtoModel(Produto entidade) {
        return modelMapper.map(entidade, ProdutoModelDTO.class);
    }

    private List<ProdutoModelDTO> listaEntidadeParaListaDtoModel(List<Produto> listaCategoria) {
        return listaCategoria.stream().map(this::entidadeParaDtoModel).toList();
    }
}