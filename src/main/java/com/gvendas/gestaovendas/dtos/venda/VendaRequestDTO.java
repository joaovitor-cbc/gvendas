package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda requisição DTO")
public class VendaRequestDTO implements Serializable {

    @ApiModelProperty(value = "Data da venda")
    @NotNull(message = "Data não pode ser nulo")
    private LocalDate data;

    @ApiModelProperty(value = "Itens da venda")
    @Valid
    @NotNull(message = "Lista de itens venda não pode ser nulo")
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
