package br.pokemon.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDto {

	@NotBlank(message = "O campo e-mail é obrigatório")
	@Email(message = "O e-mail deve ser válido")
	private String email;
	
	@NotBlank(message = "O campo senha é obrigatório")
	private String novaSenha;
	
}
