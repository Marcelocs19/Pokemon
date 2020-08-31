package br.pokemon.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsuarioForm {

	@NotBlank(message = "O campo apelido é obrigatório.")
	private String apelido;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	private String senha;
	
}
