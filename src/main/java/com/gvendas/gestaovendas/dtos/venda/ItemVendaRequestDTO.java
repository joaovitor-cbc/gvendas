package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel("Itens da venda requisição DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Codigo do produto do item da venda")
    @NotNull(message = "Codigo do produto não pode ser nulo")
    private Long codigoProduto;

    @ApiModelProperty(value = "Quantidade de item da venda")
    @NotNull(message = "Quantidade do produto não pode ser nulo")
    @Min(message = "Quantidade minima é 1", value = 1L)
    private Integer quantidade;

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
}
