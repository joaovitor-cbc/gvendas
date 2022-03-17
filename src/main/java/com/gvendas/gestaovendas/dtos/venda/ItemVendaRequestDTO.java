package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Itens da venda requisição DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Codigo do produto do item da venda")
    @NotNull(message = "Codigo do produto não pode ser nulo")
    private Long codigoProduto;

    @ApiModelProperty(value = "Quantidade de item da venda")
    @NotNull(message = "Quantidade do produto não pode ser nulo")
    @Min(message = "Quantidade minima é 1", value = 1L)
    private Integer quantidade;

    @NotNull(message = "Preco do produto não pode ser nulo")
    @ApiModelProperty(value = "Preço do item da venda")
    private BigDecimal precoVendido;

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
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
}
