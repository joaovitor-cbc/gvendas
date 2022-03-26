package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Itens venda retorno DTO")
public class ItemVendaReponseDTO {

    @ApiModelProperty(value = "Código do item da venda")
    private Long codigo;

    @ApiModelProperty(value = "Quanridade do item da venda")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço do item da venda")
    private BigDecimal precoVendido;

    @ApiModelProperty(value = "Código do produto do item da venda")
    private Long condigoProduto;

    @ApiModelProperty(value = "Descrição do produto do item da venda")
    private String produtoDescricao;

    public ItemVendaReponseDTO() {}

    public ItemVendaReponseDTO(Long codigo, Integer quantidade, BigDecimal precoVendido, Long condigoProduto, String produtoDescricao) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.condigoProduto = condigoProduto;
        this.produtoDescricao = produtoDescricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public Long getCondigoProduto() {
        return condigoProduto;
    }

    public void setCondigoProduto(Long condigoProduto) {
        this.condigoProduto = condigoProduto;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }
}
