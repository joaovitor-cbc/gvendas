
package com.gvendas.gestaovendas.repositorys;

import com.gvendas.gestaovendas.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}