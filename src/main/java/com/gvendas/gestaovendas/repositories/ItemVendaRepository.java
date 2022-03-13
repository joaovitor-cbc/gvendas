package com.gvendas.gestaovendas.repositories;

import com.gvendas.gestaovendas.models.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long> {

    List<ItemVenda> findByVendaCodigo(Long codigo);
}
