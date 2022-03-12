package com.gvendas.gestaovendas.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @ManyToOne
    private Cliente cliente;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    protected Endereco() {}

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(codigo, endereco.codigo) && Objects.equals(cliente, endereco.cliente) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero) && Objects.equals(complemento, endereco.complemento) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cep, endereco.cep) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, cliente, logradouro, numero, complemento, bairro, cep, cidade, estado);
    }

    static class EnderecoBuilder {

        private Endereco endereco;

        private EnderecoBuilder() {
            this.endereco = new Endereco();
        }

        public EnderecoBuilder enderecoBuilder() {
            EnderecoBuilder builder = new EnderecoBuilder();
            return builder;
        }

        public EnderecoBuilder codigo(Long codigo) {
            this.endereco.setCodigo(codigo);
            return this;
        }

        public EnderecoBuilder cliente(Cliente cliente) {
            this.endereco.setCliente(cliente);
            return this;
        }

        public EnderecoBuilder logradouro(String logradouro) {
            this.endereco.setLogradouro(logradouro);
            return this;
        }

        public EnderecoBuilder logradouro(Integer numero) {
            this.endereco.setNumero(numero);
            return this;
        }

        public EnderecoBuilder complemento(String complemento) {
            this.endereco.setComplemento(complemento);
            return this;
        }

        public EnderecoBuilder bairro(String bairro) {
            this.endereco.setBairro(bairro);
            return this;
        }

        public EnderecoBuilder cep(String cep) {
            this.endereco.setCep(cep);
            return this;
        }

        public EnderecoBuilder cidade(String cidade) {
            this.endereco.setCidade(cidade);
            return this;
        }

        public EnderecoBuilder estado(String estado) {
            this.endereco.setEstado(estado);
            return this;
        }

        public Endereco construir() {
            return this.endereco;
        }
    }
}