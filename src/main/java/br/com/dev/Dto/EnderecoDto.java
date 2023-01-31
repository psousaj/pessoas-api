package br.com.dev.Dto;

import java.util.ArrayList;
import java.util.List;

import br.com.dev.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoDto {
	private Long id;
	private String logradouro;
	private String cep;
	private Integer numero;
	private String cidade;

	public static EnderecoDto of(Endereco endereco) {
		var dto = new EnderecoDto();
		dto.setId(endereco.getId());
		dto.setLogradouro(endereco.getLogradouro());
		dto.setNumero(endereco.getNumero());
		dto.setCep(endereco.getCep());
		dto.setCidade(endereco.getCidade());
		return dto;
	}

	public Endereco toEntity() {
		var endereco = new Endereco();
		endereco.setId(id);
		endereco.setLogradouro(logradouro);
		endereco.setCep(cep);
		endereco.setNumero(numero);
		endereco.setCidade(cidade);
		return endereco;
	}
	
	@Override
	public String toString() {
		return ""+id+" "+logradouro+" - "+numero+"!";
	}
}
