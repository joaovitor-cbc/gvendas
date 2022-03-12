package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.repositories.CategoriaRepository;
import com.gvendas.gestaovendas.services.exception.CategoriaNaoEncontrada;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> buscarTudo(){
        return repository.findAll();
    }

    public Categoria buscarPorCodigo(Long codigo){
        return repository.findById(codigo)
                .orElseThrow(() -> new CategoriaNaoEncontrada("Categoria não encontrada"));
    }

    public Categoria salvarCategoria(Categoria categoria){
        return repository.save(categoria);
    }

    public Categoria atualizarCagetoria(Long codigo, Categoria categoria){
        Categoria categoriaSalva = validarCategoriaExistente(codigo);
        BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
        return repository.save(categoriaSalva);
    }

    public void apagarCategoria(Long codigo){
        repository.deleteById(codigo);
    }

    private Categoria validarCategoriaExistente(Long codigo) {
        return buscarPorCodigo(codigo);
    }
}