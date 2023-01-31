package br.com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dev.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public String findByCep(String cep);
	public Integer findByNumero(Integer numero);
	public String findByLogradouro(String logradouro);
}
