package br.com.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {
	
	private Long id;
	
	@NotNull(message = "O campo NOME não pode ser nulo")
	@NotEmpty(message = "O campo nome não pode estar em branco")
	private String nome;
	
	@NotNull(message = "O campo CPF não pode ser nulo")
	@NotEmpty(message = "O campo CPF não pode estar em branco")
	@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@NotNull(message = "O campo ENDERECO não pode ser nulo")
	@NotEmpty(message = "O campo ENDERECO não pode estar em branco")
	private String endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereço) {
		this.endereco = endereço;
	}
}
