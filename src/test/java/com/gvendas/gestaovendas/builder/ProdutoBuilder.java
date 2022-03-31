package com.gvendas.gestaovendas.builder;

import java.math.BigDecimal;

import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.models.Produto;

public class ProdutoBuilder {
	
	private Produto produto;
	
	public ProdutoBuilder umProduto() {
		produto = new Produto();
		return this;
	}
	
	public ProdutoBuilder comDescricao(String descricao) {
		this.produto.setDescricao(descricao);
		return this;
	}
	
	public ProdutoBuilder comQuantidade(Integer quantidade) {
		this.produto.setQuantidade(quantidade);
		return this;
	}
	
	public ProdutoBuilder comPrecoVenda(BigDecimal preco) {
		this.produto.setPrecoVenda(preco);
		return this;
	}
	
	public ProdutoBuilder comPrecoCusto(BigDecimal preco) {
		this.produto.setPrecoCusto(preco);
		return this;
	}
	
	public ProdutoBuilder comObservacao(String observacao) {
		this.produto.setObservacao(observacao);
		return this;
	}
	
	public ProdutoBuilder comCategoria(Categoria categoria) {
		this.produto.setCategoria(categoria);
		return this;
	}
	
	public Produto agora() {
		return this.produto;
	}
}
