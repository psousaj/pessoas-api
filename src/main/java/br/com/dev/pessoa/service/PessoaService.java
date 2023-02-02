package br.com.dev.pessoa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dev.endereco.Endereco;
import br.com.dev.endereco.EnderecoDto;
import br.com.dev.endereco.EnderecoService;
import br.com.dev.pessoa.dto.PessoaDto;
import br.com.dev.pessoa.model.Pessoa;
import br.com.dev.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EnderecoService enderecoService;

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Optional<Pessoa> pessoaExists(Long id) {
		return pessoaRepository.findAll().stream().filter(x -> x.getId().equals(id)).findFirst();
	}

	public ResponseEntity<PessoaDto> cadastro(PessoaDto pessoaDto) {
		List<Endereco> enderecos = pessoaDto.getEnderecos()
				.stream()
				.map(e -> e.toEntity())
				.collect(Collectors.toList());
		List<EnderecoDto> enderecosDto = new ArrayList<>();

		enderecos.forEach(e -> {
			var enderecoSalvo = enderecoService.save(e);
			enderecosDto.add(EnderecoDto.of(enderecoSalvo));
		});

		pessoaDto.setEnderecos(enderecosDto);
		Pessoa pessoa = pessoaRepository.save(pessoaDto.toEntity()); // transforma pessoaDto em entidade e salva no
																		// banco
		pessoaDto = PessoaDto.of(pessoa); // aponta a entidade já salva com id em dto para o parametro novamente
		// set enderecosdto com id para parametro dto com id

		return ResponseEntity.ok(pessoaDto);
	}

	public Optional<Pessoa> findById(Long Id) {
		return pessoaRepository.findById(Id);
	}

	public ResponseEntity<PessoaDto> update(Long pessoaId, PessoaDto pessoa) {
		if (pessoaRepository.existsById(pessoaId)) { // se a busca retornou algo continua
			Pessoa pessoaUpdate = pessoaRepository.findById(pessoaId).get(); // aponta pessoaUpdate para o registro já salvo
			pessoaUpdate.setNome(pessoa.getNome());
			pessoaUpdate.setDataDeNascimento(pessoa.getDataDeNascimento());
			pessoaUpdate = pessoaRepository.saveAndFlush(pessoaUpdate);

			return ResponseEntity.ok(PessoaDto.of(pessoaUpdate)); // transforma pessoa em pessoaDto e retorn no corpo de
		}

		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<PessoaDto> addEnderecos(Long pessoaId, List<EnderecoDto> enderecosDto) {
		var byId = pessoaRepository.findById(pessoaId).get();
		List<Endereco> enderecos = new ArrayList<>();

		enderecosDto.forEach(e->{
			var endereco = enderecoService.save(e.toEntity());
			enderecos.add(endereco); 
		});
		
		var filtro = enderecos.stream().distinct().collect(Collectors.toList());
		byId.getEnderecos().addAll(filtro);
		pessoaRepository.saveAndFlush(byId);
		
		return ResponseEntity.ok(PessoaDto.of(byId));
	}

	public ResponseEntity<List> listarEnderecos(Long id) {
		var byId = findById(id);
		
		if(byId.isPresent()) {
			var pessoa = byId.get();
			return ResponseEntity.ok(pessoa.getEnderecos());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<PessoaDto> setEnderecoPrincipal(Long pessoaId, Long enderecoId){
		var byId = findById(pessoaId);
		var enderecoById = enderecoService.findById(enderecoId);
		
		if (byId.isPresent() && enderecoById.isPresent()) {
			var pessoa = byId.get();
			enderecoById.get().setFavorite(true);
			pessoa.setEnderecoPrincipal(pessoaId);
			var responseBody = pessoaRepository.saveAndFlush(pessoa);
			
			return ResponseEntity.ok(PessoaDto.of(responseBody));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Void> delete(Long id) {
		Optional<Pessoa> byId = findById(id);

		if (byId.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}
