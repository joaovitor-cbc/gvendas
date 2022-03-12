package com.gvendas.gestaovendas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String telefone;
    private Boolean ativo;

    protected Cliente() {}

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codigo, cliente.codigo) && Objects.equals(nome, cliente.nome)
                && Objects.equals(telefone, cliente.telefone) && Objects.equals(ativo, cliente.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, telefone, ativo);
    }

    static class ClienteBuilder {

        private Cliente cliente;

        private ClienteBuilder() {
            this.cliente = new Cliente();
        }

        public static ClienteBuilder clienteBuilder() {
            ClienteBuilder builder = new ClienteBuilder();
            return builder;
        }

        public ClienteBuilder codigo(Long codigo) {
            this.cliente.setCodigo(codigo);
            return this;
        }

        public ClienteBuilder nome(String nome) {
            this.cliente.setNome(nome);
            return  this;
        }

        public ClienteBuilder telefone(String telefone) {
            this.cliente.setTelefone(telefone);
            return this;
        }

        public ClienteBuilder ativo(Boolean ativo) {
            this.cliente.setAtivo(ativo);
            return this;
        }

        public Cliente construir() {
            return this.cliente;
        }
    }
}