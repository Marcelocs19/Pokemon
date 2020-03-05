package br.pokemon.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pokemon.dto.PokemonDto;
import br.pokemon.modelo.Pokemon;
import br.pokemon.repositorio.PokemonRepositorio;

@Service
public class PokemonServico {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;

	public List<PokemonDto> listaPokemons() {
		List<PokemonDto> listaPokemons = new ArrayList<>();
		pokemonRepositorio.findAll().forEach(p -> listaPokemons.add(convertePokemonDto(p)));
		return listaPokemons;
	}	

	private PokemonDto convertePokemonDto(Pokemon pokemon) {
		return new PokemonDto(pokemon);
	}

}
