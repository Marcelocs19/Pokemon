package br.pokemon.constante;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ConstantesEndPoints {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface EndPointPokemon {
		String pokemonRequest = "/pokemons";
		String bucarPokemonTipo = "/buscar";
		String buscarPokemonId = "/buscar/{id}";
		String buscarPokemonNome = "/buscarNome/{nome}";
	}
}
