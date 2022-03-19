package com.gvendas.gestaovendas.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
    private Venda venda;

    private Integer quantidade;

    @Column(name = "preco_vendido")
    private BigDecimal precoVendido;

    public ItemVenda() {}

    public ItemVenda(Long codigo, Produto produto, Venda venda, Integer quantidade, BigDecimal precoVendido) {
        this.codigo = codigo;
        this.produto = produto;
        this.venda = venda;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVendido() {
        return precoVendido;
    }

    public void setPrecoVendido(BigDecimal precoVendido) {
        this.precoVendido = precoVendido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(codigo, itemVenda.codigo) && Objects.equals(produto, itemVenda.produto) && Objects.equals(venda, itemVenda.venda) && Objects.equals(quantidade, itemVenda.quantidade) && Objects.equals(precoVendido, itemVenda.precoVendido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, produto, venda, quantidade, precoVendido);
    }
}
