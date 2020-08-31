package br.pokemon.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CadastroUsuarioForm {

	@NotBlank(message = "O campo nome é obrigatório.")
	private String nome;
	
	@NotBlank(message = "O campo e-mail é obrigatório.")
	@Email(message = "Insera um e-mail válido.")
	private String email;

	@NotBlank(message = "O campo apelido é obrigatório.")
	private String apelido;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	private String senha;
	
	private boolean adminCliente;
	
}
