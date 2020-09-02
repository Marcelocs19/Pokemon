package br.pokemon.forms;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.pokemon.constantes.Tipo;
import lombok.Data;

@Data
public class PokemonForm {

	@NotBlank(message = "O campo nome é obrigatório.")
	private String nome;
		
	@Enumerated(EnumType.STRING)
	private Tipo tipo1;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo2;
	
	@NotBlank(message = "O campo descrição é obrigatório.")
	@Size(min = 10 ,max = 200, message = "O tamanho mínimo da descrição deve ser 10 e o máximo é até 200")
	private String descricao;
}
