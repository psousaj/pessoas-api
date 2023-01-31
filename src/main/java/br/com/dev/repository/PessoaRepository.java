package br.com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dev.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
