package br.pokemon.dto;

import br.pokemon.modelo.Pokemon;
import lombok.Data;

@Data
public class PokemonDto {

	private Long id;

	private String nome;

	private String tipo1;

	private String tipo2;

	private String descricao;

	public PokemonDto(Pokemon pokemon) {
		this.id = pokemon.getId();
		this.nome = pokemon.getNome();
		this.tipo1 = pokemon.getTipo1().getDescricao();
		this.tipo2 = pokemon.getTipo2().getDescricao();
		this.descricao = pokemon.getDescricao();
	}
	
}
