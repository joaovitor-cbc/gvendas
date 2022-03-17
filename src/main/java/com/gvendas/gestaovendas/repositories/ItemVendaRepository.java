package com.gvendas.gestaovendas.repositories;

import com.gvendas.gestaovendas.models.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long> {

    @Query(value = "select new com.gvendas.gestaovendas.models.ItemVenda(iv.codigo, iv.produto, iv.venda, iv.quantidade, iv.precoVendido)" +
            " from ItemVenda iv where iv.venda.codigo = :codigo")
    List<ItemVenda> findByVendaCodigo(Long codigo);
}
