package br.pokemon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PokemonDto {

	private Long id;

	private String nome;

	private String tipo1;

	private String tipo2;

	private String descricao; 
	

}
