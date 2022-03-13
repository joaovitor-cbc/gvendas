package com.gvendas.gestaovendas.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private Date data;

    @ManyToOne
    private Cliente cliente;

    public Venda() {}

    public Venda(Long codigo, Date data, Cliente cliente) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(codigo, venda.codigo) && Objects.equals(data, venda.data) && Objects.equals(cliente, venda.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, data, cliente);
    }
}
