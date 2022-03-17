package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda requisição DTO")
public class VendaRequestDTO implements Serializable {

    @ApiModelProperty(value = "Data da venda")
    private LocalDate data;
    @ApiModelProperty(value = "Itens da venda")
    private List<ItemVendaRequestDTO> itensVendaDto;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaRequestDTO> getItensVendaDto() {
        return itensVendaDto;
    }

    public void setItensVendaDto(List<ItemVendaRequestDTO> itensVendaDto) {
        this.itensVendaDto = itensVendaDto;
    }
}
