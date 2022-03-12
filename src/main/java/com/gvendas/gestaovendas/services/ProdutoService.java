package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;
import com.gvendas.gestaovendas.services.exception.ProdutoDuplicadoException;
import com.gvendas.gestaovendas.services.exception.ProdutoNaoEncontradoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public Produto buscarPorCodigo(Long codigo) {
        return repo.findById(codigo)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com codigo: " + codigo + " não existe."));
    }

    public List<Produto> listaProduto() {
        return repo.findAll();
    }

    public Produto salvarProduto(Produto produto) {
        produtoEhDuplicado(produto);
        return repo.save(produto);
    }

    public void produtoEhDuplicado(Produto produto) {
        Optional<Produto> produtoOpt = repo.findByDescricaoAndCategoriaCodigo(produto.getDescricao(), produto.getCategoria().getCodigo());
        if (produtoOpt.isPresent()) throw new ProdutoDuplicadoException("Produto já cadastrado na base de dados para essa categoria: "
                + produto.getCategoria().getNome());
    }

    public void atualizarProduto(Long codigo, Produto produto) {
        Produto produtoSalvo = buscarPorCodigo(codigo);
        produtoEhDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
        repo.save(produtoSalvo);
    }
}