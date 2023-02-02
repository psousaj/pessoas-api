package br.com.dev.pessoa.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dev.endereco.EnderecoDto;
import br.com.dev.endereco.EnderecoService;
import br.com.dev.pessoa.dto.PessoaDto;
import br.com.dev.pessoa.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	@Autowired PessoaService service;
	@Autowired EnderecoService enderecoService;
	
	@GetMapping
	public List<PessoaDto> list(){
		logger.info("request received to list all registries");
		List<PessoaDto> list = new ArrayList<>();
		service.findAll().stream().map(PessoaDto::of).forEach(x->list.add(x));
		return list;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> getPessoa(@PathVariable Long id){
		logger.info("request received to get pessoa by Id: {}", id);
		
		return service.findById(id)
				.map(x->ResponseEntity.ok(PessoaDto.of(x)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/cadastro")
	public ResponseEntity<PessoaDto> create(@RequestBody @Valid PessoaDto pessoaDto){
		logger.info("request received for create pessoa: {}", pessoaDto);
		return service.cadastro(pessoaDto);
	}
	
	@PostMapping("{pessoaId}/enderecos/adicionar")
	public ResponseEntity<PessoaDto> addEnderecos(@PathVariable Long pessoaId, @RequestBody List<EnderecoDto> enderecosDto){
		logger.info("request received for create adresses for person with id: {}", pessoaId);
		return service.addEnderecos(pessoaId, enderecosDto);
	}
	
	@GetMapping("/{pessoaId}/enderecos/listar")
	public ResponseEntity<List> listarEnderecos(@PathVariable Long pessoaId){
		logger.info("request received for list adresses of person with id: {}", pessoaId);
		return service.listarEnderecos(pessoaId);
	}
	
	@PutMapping("{pessoaId}/enderecos/favoritar")
	public ResponseEntity<PessoaDto> setEnderecoPrincipal(@PathVariable Long pessoaId, @RequestParam(value="endereco") Long enderecoId){
		logger.info("request received to favorite endereco with id: {} for person with id: {}", enderecoId, pessoaId);
		return service.setEnderecoPrincipal(pessoaId, enderecoId);
	}
	
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/atualizar/{pessoaId}")
	public ResponseEntity<PessoaDto> atualizar(@PathVariable Long pessoaId, @RequestBody PessoaDto pessoaDto){
		logger.info("request received for update pessoa with id: {}", pessoaId);
		return service.update(pessoaId, pessoaDto);
	}
	
	@DeleteMapping("/{pessoaId}")
	public ResponseEntity<Void> delete (@PathVariable Long pessoaId){
		logger.info("request received for delete person with id: {}", pessoaId);
		return service.delete(pessoaId);
	}
}
