package com.gvendas.gestaovendas.builder;

import java.math.BigDecimal;

import com.gvendas.gestaovendas.dtos.produto.ProdutoRequestDTO;

public class ProdutoRequestDTOBuilder {

	private ProdutoRequestDTO produtoRequestDTO;
	
	public ProdutoRequestDTOBuilder umProdutoRequest() {
		produtoRequestDTO = new ProdutoRequestDTO();;
		return this;
	}
	
	public ProdutoRequestDTOBuilder comDescricao(String descricao) {
		this.produtoRequestDTO.setDescricao(descricao);
		return this;
	}
	
	public ProdutoRequestDTOBuilder comQuantidade(Integer quantidade) {
		this.produtoRequestDTO.setQuantidade(quantidade);
		return this;
	}
	
	public ProdutoRequestDTOBuilder comPrecoVenda(BigDecimal preco) {
		this.produtoRequestDTO.setPrecoVenda(preco);
		return this;
	}
	
	public ProdutoRequestDTOBuilder comPrecoCusto(BigDecimal preco) {
		this.produtoRequestDTO.setPrecoCusto(preco);
		return this;
	}
	
	public ProdutoRequestDTOBuilder comObservacao(String observacao) {
		this.produtoRequestDTO.setObservacao(observacao);
		return this;
	}
	
	public ProdutoRequestDTOBuilder comCodigoProduto(Long codigoProduto) {
		this.produtoRequestDTO.setCategoriaCodigo(codigoProduto);
		return this;
	}
	
	public ProdutoRequestDTO agora() {
		return this.produtoRequestDTO;
	}
}
