package br.pokemon.endpoints;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ConstantesEndPoints {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface EndPointPokemon {
		String pokemonRequest = "/pokemons";
		String pokemonTipo = "/tipos";
		String pokemonId = "/{id}";
		String pokemonNome = "nomes/{nome}";
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface EndPointUsuario {
		String usuarioRequest = "/usuarios";
		String listaPokemon = "/pokemons";
		String adicionaPokemon = "/pokemons/{id}";
	}
}
