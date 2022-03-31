package com.gvendas.gestaovendas.services;

import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gvendas.gestaovendas.builder.ProdutoBuilder;
import com.gvendas.gestaovendas.dtos.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.models.Categoria;
import com.gvendas.gestaovendas.models.Produto;
import com.gvendas.gestaovendas.repositories.ProdutoRepository;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {
	
	@InjectMocks
	private ProdutoService produtoService;
	
    @Mock
    private ProdutoRepository repo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoriaService categoriaService;
    
    @BeforeEach
    void setup() {
    	Produto produto = new ProdutoBuilder().umProduto().comDescricao("descrição teste")
    			.comObservacao("observação teste").comPrecoCusto(new BigDecimal(100))
    			.comPrecoVenda(new BigDecimal(500)).comCategoria(new Categoria(1L, "Categoria Teste"))
    			.comQuantidade(10).agora();
    	
    	ProdutoResponseDTO dto = new ProdutoResponseDTO();
    	BeanUtils.copyProperties(produto, dto);
    	dto.setCodigo(1L);
    	
    	BDDMockito.when(repo.findById(ArgumentMatchers.anyLong()))
    			.thenReturn(Optional.of(produto));
    	BDDMockito.when(this.produtoService.entidadeParaDtoModel(produto))
    	.thenReturn(dto);
    	
    }
    
	@Test
    void deveRetornaProdutoReponseDTO_QuandoBuscarPorCodigo() {
    	ProdutoResponseDTO dto = this.produtoService.buscarPorCodigoModelProduto(1L);
    	assertThat(dto.getCodigo(), CoreMatchers.notNullValue());
    	assertThat(dto.getDescricao(), CoreMatchers.is("descrição teste"));
    	assertThat(dto.getCodigo(), CoreMatchers.is(1L));
    }
}
