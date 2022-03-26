package com.gvendas.gestaovendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("Cliente da venda retorno DTO")
public class ClienteVendaResponseDTO implements Serializable {

    @ApiModelProperty(value = "Nome cliente")
    private String nome;

    @ApiModelProperty(value = "Lista de vendas")
    private List<VendaReponseDTO> vendaReponseDTO;

    public ClienteVendaResponseDTO() {}

    public ClienteVendaResponseDTO(String nome, List<VendaReponseDTO> vendaReponseDTO) {
        this.nome = nome;
        this.vendaReponseDTO = vendaReponseDTO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<VendaReponseDTO> getVendaReponseDTO() {
        return vendaReponseDTO;
    }

    public void setVendaReponseDTO(List<VendaReponseDTO> vendaReponseDTO) {
        this.vendaReponseDTO = vendaReponseDTO;
    }
}
