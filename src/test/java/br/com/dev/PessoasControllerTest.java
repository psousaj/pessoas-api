package br.com.dev;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_SELF;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.SelectorResolutionResult.Status;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.dev.Dto.PessoaDto;
import br.com.dev.controller.PessoaController;
import br.com.dev.model.Pessoa;
import br.com.dev.service.PessoaService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoasControllerTest {

	@Autowired
	private PessoaController pessoaController;
	private Gson gson = new Gson();
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeEach
	public void setup( ) {
		RestAssuredMockMvc.standaloneSetup(this.pessoaController);
	}
	
	@MockBean(classes = PessoaService.class)
	private PessoaService service;
	
	private Pessoa criarPessoa() {
		Pessoa pessoa = Mockito.mock(Pessoa.class);
		pessoa.setId(1L);
		pessoa.setEnderecos(Collections.emptyList());
		pessoa.setNome("Teste");
		pessoa.setDataDeNascimento("12/23/3456");
		return pessoa;
	}
	
	@Test
	public void deveRetornarSucessoQuandoBuscarPessoa() {
		Long id = 1L;
		Optional<Pessoa> optional = Optional.of(criarPessoa());
		
		Mockito.when(service.findById(id)).thenReturn(optional);
		RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/pessoas/{id}", id).then().statusCode(200);
	}

	@Test
	public void deveRetornarNaoEncontradoAoBuscarPorIdInexistente() {
		Long id = 1L;
		Optional<Pessoa> optional = Optional.empty();
		
		Mockito.when(service.findById(id)).thenReturn(optional);
		RestAssuredMockMvc.given().accept(ContentType.JSON).get("/pessoas/{id}", id).then().statusCode(404);
	}
	
	@Test
	public void deveRetornarBadRequestQuandoBuscarPessoaPeloNomeNaUri() {
		RestAssuredMockMvc.given().get("/pessoas/Teste").then().status(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deveRetornarUmaListaDePessoas () {
		Mockito.when(service.findAll()).thenReturn(Collections.emptyList());
		
		RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/pessoas").then().statusCode(200);
	}
	
	@Test
	public void deveRetornarStatusCreatedAoLançarNovaPessoa() throws  Exception {
		var pessoa = PessoaDto.of(criarPessoa());
		pessoa.setId(1L);
		
		Mockito.when(service.cadastro(any(PessoaDto.class))).thenReturn(ResponseEntity.ok(pessoa));

		RestAssuredMockMvc.given().post("/pessoas/cadastro", objMapper.writeValueAsString(pessoa)).then().body(null, null);
		
	}
	
}
