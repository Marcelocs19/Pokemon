package br.pokemon.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pokemon.configurar.LeituraTxt;
import br.pokemon.dto.PokemonDto;
import br.pokemon.modelo.Pokemon;
import br.pokemon.repositorio.PokemonRepositorio;
import br.pokemon.tipo.Tipo;

@Service
public class PokemonServico {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;

	public List<PokemonDto> listaPokemons(String tipo) {
		List<PokemonDto> listaPokemons = new ArrayList<>();
		if (!tipo.isBlank()) {
			return buscaTipo(tipo, listaPokemons);
		}
		pokemonRepositorio.findAll().forEach(p -> listaPokemons.add(convertePokemonDto(p)));
		return listaPokemons;
	}

	private List<PokemonDto> buscaTipo(String tipo, List<PokemonDto> listaPokemons) {
		Tipo tipoPokemon = LeituraTxt.tipoPokemon(tipo);
		
		List<Pokemon> findByTipo = pokemonRepositorio.findByTipo1(tipoPokemon);
		findByTipo.forEach(p -> listaPokemons.add(convertePokemonDto(p)));
		
		return listaPokemons;
	}

	private PokemonDto convertePokemonDto(Pokemon pokemon) {
		return new PokemonDto(pokemon);
	}

}
