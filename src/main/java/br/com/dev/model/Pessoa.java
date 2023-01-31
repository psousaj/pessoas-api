package br.com.dev.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	private String dataDeNascimento;
	@ManyToMany //(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinTable(name="pessoa_endereco", joinColumns = {@JoinColumn(name="pessoa_id")}, inverseJoinColumns= {@JoinColumn(name="endereco_id")})
	private List<Endereco> enderecos = new ArrayList<>();
	private Long enderecoPrincipalId;

	public void addEndereco(Endereco endereco) {
		enderecos.add(endereco);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String data) {
		this.dataDeNascimento = data;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Long getEnderecoPrincipal() {
		return enderecoPrincipalId;
	}

	public void setEnderecoPrincipal(Long enderecoPrincipalId) {
		this.enderecoPrincipalId = enderecoPrincipalId;
	}
	
}
