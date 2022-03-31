package com.gvendas.gestaovendas.resources;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.gvendas.gestaovendas.dtos.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.services.ProdutoService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(ProdutoResource.class)
public class ProdutoResourceTest {

	@Autowired
	private ProdutoResource produtoResource;
	
	@MockBean
	private ProdutoService produtoService;

	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.produtoResource);
	}

	@Test
	void deveRetornaSucesso_QuandoBuscarProdutoPorCodigo() {
		Mockito.when(this.produtoService.buscarPorCodigoModelProduto(1L))
		.thenReturn(new ProdutoResponseDTO());
		
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when().get("produtos/{codigo}", 1L)
		.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornaSucesso_QuandoListarProduto() {
		Mockito.when(this.produtoService.listaProduto())
		.thenReturn(Arrays.asList(new ProdutoResponseDTO(), new ProdutoResponseDTO()));
		
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when().get("produtos")
		.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornaSemConteudo_QuandoDeletarProduto() {
		Mockito.doNothing().when(this.produtoService).apagarProduto(1L);
		
		RestAssuredMockMvc.given()
		.accept(ContentType.JSON)
		.when().delete("/produtos/{codigo}", 1L)
		.then().statusCode(HttpStatus.NO_CONTENT.value());
	}
}
