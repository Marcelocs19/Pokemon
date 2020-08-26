package br.pokemon.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CadastroUsuarioForm {

	@NotBlank(message = "O campo nome é obrigatório.")
	private String nome;

	@NotBlank(message = "O campo apelido é obrigatório.")
	private String apelido;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	private String senha;
	
}
