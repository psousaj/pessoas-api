package br.com.dev.pessoa.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.dev.endereco.Endereco;
import br.com.dev.endereco.EnderecoDto;
import br.com.dev.pessoa.model.Pessoa;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PessoaDto {
	private Long id;
	@Column(nullable = false)
	private String nome;
	private String dataDeNascimento;
	private Long enderecoPrincipalId;
	private List<EnderecoDto> enderecos = new ArrayList<>();
	

	public void addEndereco(EnderecoDto endereco) {
		if (endereco.getLogradouro() != null && endereco.getNumero() != null)
			enderecos.add(endereco);
	}

	public static PessoaDto of(Pessoa pessoa) {
		var dto = new PessoaDto();
		List<EnderecoDto> enderecosDto = new ArrayList<>();
		dto.setId(pessoa.getId());
		dto.setNome(pessoa.getNome());
		dto.setDataDeNascimento(pessoa.getDataDeNascimento());
		pessoa.getEnderecos().stream().map(EnderecoDto::of).forEach(x -> enderecosDto.add(x));
		dto.setEnderecos(enderecosDto);
		dto.setEnderecoPrincipalId(pessoa.getEnderecoPrincipal());
		return dto;
	}

	public Pessoa toEntity() {
		List<Endereco> enderecos = new ArrayList<>();
		var pessoa = new Pessoa();
		var dto = new EnderecoDto();
		pessoa.setNome(nome);
		pessoa.setDataDeNascimento(dataDeNascimento);
		this.enderecos.stream().filter(e->e.getLogradouro() != null).forEach(e->{
			enderecos.add(e.toEntity());
		});
		pessoa.setEnderecos(enderecos);
		pessoa.setEnderecoPrincipal(enderecoPrincipalId);
		return pessoa;
	}
}
