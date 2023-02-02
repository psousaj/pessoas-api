package br.com.dev.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dev.pessoa.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
