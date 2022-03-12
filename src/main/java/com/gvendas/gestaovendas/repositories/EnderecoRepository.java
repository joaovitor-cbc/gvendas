package com.gvendas.gestaovendas.repositories;

import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT e FROM Endereco e WHERE e.logradouro = :logradouro AND e.numero = :numero AND e.complemento = :complemento" +
            " AND e.bairro = :bairro AND e.cep = :cep AND e.cidade = :cidade AND e.estado = :estado AND e.cliente = :cliente")
    Optional<Endereco> findEndereco(@Param("logradouro") String logradouro, @Param("numero") Integer numero,
                                    @Param("complemento") String complemento, @Param("bairro") String bairro,
                                    @Param("cep") String cep, @Param("cidade") String cidade, @Param("estado") String estado,
                                    @Param("cliente") Cliente cliente);
}