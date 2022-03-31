package com.gvendas.gestaovendas.dtos.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ProdutoRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "A descricao não pode ser vazio.")
    @Length(min = 2, max = 100, message = "O minimo é 2 e o maximo  é 100 de caracteres para descricao")
    private String descricao;

    @NotNull(message = "A quantidade não pode ser nulo.")
    @Min(value = 2, message = "O minimo de caracteres permitido é 1")
    private Integer quantidade;

    @NotNull(message = "O preco custo não pode ser nulo.")
    private BigDecimal precoCusto;

    @NotNull(message = "O preco venda não pode ser nulo.")
    private BigDecimal precoVenda;

    @Max(value = 500, message = "O maximo de caracteres é de 500 para observacao")
    private String observacao;

    @NotNull
    private Long categoriaCodigo;

    public ProdutoRequestDTO() {}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getCategoriaCodigo() {
        return categoriaCodigo;
    }

    public void setCategoriaCodigo(Long categoriaCodigo) {
        this.categoriaCodigo = categoriaCodigo;
    }
}