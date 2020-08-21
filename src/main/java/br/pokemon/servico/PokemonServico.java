package br.pokemon.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pokemon.configurar.LeituraTxt;
import br.pokemon.dto.PokemonDto;
import br.pokemon.form.PokemonForm;
import br.pokemon.modelo.Pokemon;
import br.pokemon.repositorio.PokemonRepositorio;
import br.pokemon.tipo.Tipo;

@Service
public class PokemonServico {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;

	@Autowired
	private ModelMapper modelMapper;
	
	public PokemonDto criarPokemon(PokemonForm pokemon) {
		Optional<Pokemon> findByNome = pokemonRepositorio.findByNome(pokemon.getNome());	
		if(findByNome.isPresent()) {
			throw new IllegalStateException("Já existe um pokemon cadastrado com esse nome");
		}
		Pokemon salvo = pokemonRepositorio.save(modelMapper.map(pokemon, Pokemon.class));
		return  modelMapper.map(salvo, PokemonDto.class);
	}
	
	public List<PokemonDto> listaPokemons() {
		List<PokemonDto> listaPokemons = new ArrayList<>();
		pokemonRepositorio.findAll().forEach(p -> listaPokemons.add(modelMapper.map(p, PokemonDto.class)));
		return listaPokemons;
	}	

	public List<PokemonDto> buscaTiposPokemons(String tipo1, String tipo2) {
		List<PokemonDto> listaPokemons = new ArrayList<>();
		if (tipo1 != null || tipo2 != null) {
			return buscaTipo(tipo1, tipo2, listaPokemons);
		}
		return listaPokemons();		
	}

	private List<PokemonDto> buscaTipo(String tipo1, String tipo2, List<PokemonDto> listaPokemons) {
		List<Pokemon> buscaTipo;		
		if(tipo1 != null && tipo2 == null) {
			Tipo tipoPokemon1 = LeituraTxt.tipoPokemon(tipo1);
			buscaTipo = pokemonRepositorio.findByTipo1(tipoPokemon1);
		} else if(tipo1 == null && tipo2 != null) {
			Tipo tipoPokemon2 = LeituraTxt.tipoPokemon(tipo2);
			buscaTipo = pokemonRepositorio.findByTipo2(tipoPokemon2);
		} else {
			Tipo tipoPokemon1 = LeituraTxt.tipoPokemon(tipo1);
			Tipo tipoPokemon2 = LeituraTxt.tipoPokemon(tipo2);
			buscaTipo = pokemonRepositorio.findByTipo1AndTipo2(tipoPokemon1,tipoPokemon2);
		}
		
		buscaTipo.stream().forEach(p -> listaPokemons.add(modelMapper.map(p, PokemonDto.class)));
		return listaPokemons;
	}
	
	
	public PokemonDto buscaPokemonPorId(Long id) {
		PokemonDto dto = null;
		Optional<Pokemon> pokemon = pokemonRepositorio.findById(id);
		if(pokemon.isPresent()) {
			dto = modelMapper.map(pokemon.get(), PokemonDto.class);
		}
		return dto;
	}
	
	public PokemonDto buscaPokemonPorNome(String nome) {
		PokemonDto dto = null;
		Optional<Pokemon> pokemon = pokemonRepositorio.findByNome(nome);
		if(pokemon.isPresent()) {
			dto = modelMapper.map(pokemon.get(), PokemonDto.class);
		}
		return dto;
	}

}
