package br.pokemon.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pokemon.modelo.Pokemon;
import br.pokemon.repositorio.PokemonRepositorio;

@Service
public class PokemonServico {
	
	@Autowired
	private PokemonRepositorio pokemonRepositorio;		
		
	public List<Pokemon> listaPokemons(){
		return pokemonRepositorio.findAll();
	}

}
