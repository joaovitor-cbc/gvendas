package com.gvendas.gestaovendas.services;

import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.repositorys.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> buscarTudo(){
        return repository.findAll();
    }

    public Categoria buscarPorId(Long codigo){
        Optional<Categoria> categoriaOpt = repository.findById(codigo);
        if (categoriaOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada.");
        return categoriaOpt.get();
    }

    public Categoria salvarCategoria(Categoria categoria){
        if (categoria == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria nulo.");
        return repository.save(categoria);
    }
}