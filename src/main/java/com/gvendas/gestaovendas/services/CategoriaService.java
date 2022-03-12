package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.dtos.CategoriaInsertDTO;
import com.gvendas.gestaovendas.dtos.CategoriaModelDTO;
import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.repositories.CategoriaRepository;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;
import com.gvendas.gestaovendas.services.exception.CategoriaDuplicadaException;
import com.gvendas.gestaovendas.services.exception.CategoriaNaoEncontradaException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CategoriaModelDTO> buscarTudo() {
        return listaEntidadeParaListaDtoModel(repository.findAll());
    }

    public CategoriaModelDTO buscarPorCodigo(Long codigo) {
        Categoria categoria = repository.findById(codigo)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria com codigo: " + codigo + " não existe."));
        return entidadeParaDtoModel(categoria);
    }

    public CategoriaModelDTO salvarCategoria(CategoriaInsertDTO categoriaInsertDTO) {
        Categoria categoria = dtoInsertParaEntidade(categoriaInsertDTO);
        categoriaEhDuplicada(categoria);
        return entidadeParaDtoModel(repository.save(categoria));
    }

    public void atualizarCagetoria(Long codigo, Categoria categoria) {
        Categoria categoriaSalva = dtoModelParaEntidade(buscarPorCodigo(codigo));
        categoriaEhDuplicada(categoria);
        BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
        repository.save(categoriaSalva);
    }

    public void apagarCategoria(Long codigo) throws SQLIntegrityConstraintViolationException {
        Categoria categoria = dtoModelParaEntidade(buscarPorCodigo(codigo));
        categoriaPossuiProdutosCadastrados(categoria);
        repository.delete(categoria);
    }

    private void categoriaEhDuplicada(Categoria categoria) {
        Optional<Categoria> categoriaOptional = repository.findByNomeContaining(categoria.getNome());
        if (categoriaOptional.isPresent() && !categoriaOptional.get().getCodigo().equals(categoria.getCodigo()))
            throw new CategoriaDuplicadaException("Categoria já está cadastrada " + categoria.getNome().toUpperCase());
    }


    public void categoriaPossuiProdutosCadastrados(Categoria categoria) throws SQLIntegrityConstraintViolationException {
        Optional<List<Produto>> produtoOpt = produtoRepository.findByCategoriaCodigo(categoria.getCodigo());
        if (produtoOpt.isPresent())
            throw new SQLIntegrityConstraintViolationException("Categoria: " + categoria.getNome() + " possui produtos cadastrados.");
    }

    private Categoria dtoInsertParaEntidade(CategoriaInsertDTO dtoInsert) {
        return modelMapper.map(dtoInsert, Categoria.class);
    }

    private Categoria dtoModelParaEntidade(CategoriaModelDTO categoriaModelDTO) {
        return modelMapper.map(categoriaModelDTO, Categoria.class);
    }

    private CategoriaModelDTO entidadeParaDtoModel(Categoria entidade) {
        return modelMapper.map(entidade, CategoriaModelDTO.class);
    }

    private List<CategoriaModelDTO> listaEntidadeParaListaDtoModel(List<Categoria> listaCategoria) {
        return listaCategoria.stream().map(this::entidadeParaDtoModel).toList();
    }
}