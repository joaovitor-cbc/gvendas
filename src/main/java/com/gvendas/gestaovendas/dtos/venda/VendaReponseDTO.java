package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda retorno DTO")
public class VendaReponseDTO implements Serializable {

    @ApiModelProperty(value = "Código da venda")
    private Long codigo;

    @ApiModelProperty(value = "Data da venda")
    private LocalDate data;

    @ApiModelProperty(value = "Lista de itens da venda")
    private List<ItemVendaReponseDTO> itemVendaReponseDTOS;

    public VendaReponseDTO() {}

    public VendaReponseDTO(Long codigo, LocalDate data, List<ItemVendaReponseDTO> itemVendaReponseDTOS) {
        this.codigo = codigo;
        this.data = data;
        this.itemVendaReponseDTOS = itemVendaReponseDTOS;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaReponseDTO> getItemVendaReponseDTOS() {
        return itemVendaReponseDTOS;
    }

    public void setItemVendaReponseDTOS(List<ItemVendaReponseDTO> itemVendaReponseDTOS) {
        this.itemVendaReponseDTOS = itemVendaReponseDTOS;
    }
}
