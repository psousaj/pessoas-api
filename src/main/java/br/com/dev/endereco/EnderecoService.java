package br.com.dev.endereco;

import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.sql.results.graph.collection.internal.UnfetchedCollectionAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dev.exceptionHandler.NotFoundException;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;
	
	public String findByCep (Endereco endereco){
		return repository.findByCep(endereco.getCep());
	}
	
	public Integer findByNumero (Endereco endereco) {
		return repository.findByNumero(endereco.getNumero());
	}
	
	public Optional<Endereco> findById (Long id) {
		return repository.findById(id);
	}
	
	public Endereco save(Endereco endereco) {
		List<String> cepsExistentes = repository.findAll().stream().map(e->e.getCep()).collect(Collectors.toList());
		List<Integer> numerosExistentes = repository.findAll().stream().map(e->e.getNumero()).collect(Collectors.toList());
		
		var cepAtual = endereco.getCep();
		var numeroAtual = endereco.getNumero();
		
		if(cepsExistentes.contains(cepAtual)) {
			if (numerosExistentes.contains(numeroAtual)) {
				List<Endereco> enderecoEncontrado = repository.findAll().stream()
						.filter(e->e.getCep().equals(cepAtual) && e.getNumero().equals(numeroAtual))
						.toList();
				return enderecoEncontrado.get(0);
			} 
		}
		return repository.save(endereco);
	}
	
	public Endereco saveAndFlush(Endereco endereco) {
		var byId = repository.findById(endereco.getId()).get();
		
		if(byId.getCep().equals(endereco.getCep()) 
				&& byId.getNumero().equals(endereco.getNumero())
				&& byId.getLogradouro().equals(endereco.getLogradouro())
				&& byId.getCidade().equals(endereco.getCidade())) {
			return byId;
		}
		if(byId.getCep().equals(endereco.getCep()) 
				&& byId.getNumero().equals(endereco.getNumero())
				&& byId.getLogradouro().equals(endereco.getLogradouro())) {
			
			byId.setCidade(endereco.getCidade());
			return repository.saveAndFlush(byId);
		}
		if(byId.getCep().equals(endereco.getCep()) 
				&& byId.getNumero().equals(endereco.getNumero())){
			
			byId.setCidade(endereco.getCidade());
			byId.setLogradouro(endereco.getLogradouro());
			return repository.saveAndFlush(byId);
		}
		if(byId.getCep().equals(endereco.getCep())) {
			byId.setCidade(endereco.getCidade());
			byId.setLogradouro(endereco.getLogradouro());
			byId.setNumero(endereco.getNumero());
		}
		
		return repository.saveAndFlush(endereco);
	}
	
	public Endereco update(Endereco endereco) {
		var byId = repository.findById(endereco.getId());
		
		if(byId.isPresent()) {
			return saveAndFlush(endereco);
		} else 
			throw new NotFoundException();
	}
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
}
