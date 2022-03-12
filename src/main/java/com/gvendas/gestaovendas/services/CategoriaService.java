package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.repositorys.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    public Categoria salvarCategoria(Categoria categoria){
        if (categoria == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria nulo.");
        return repository.save(categoria);
    }

    public Categoria atualizar(Long codigo, Categoria categoria){
        Categoria categoriaSalva = validarCategoriaExistente(codigo);
        BeanUtils.copyProperties(categoria, categoriaSalva, "codigo", "nome");
        return repository.save(categoria);
    }

    private Categoria validarCategoriaExistente(Long codigo) {
        return buscarPorCodigo(codigo);
    }
}