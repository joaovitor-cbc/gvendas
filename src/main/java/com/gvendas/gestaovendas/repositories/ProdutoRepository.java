package com.gvendas.gestaovendas.repositories;

import com.gvendas.gestaovendas.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByDescricaoContaining(String descricao);
}