package br.pokemon.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pokemon.configuracoes.LeituraTxt;
import br.pokemon.constantes.Tipo;
import br.pokemon.dtos.PokemonDto;
import br.pokemon.excecoes.ErrosExcecao;
import br.pokemon.forms.PokemonForm;
import br.pokemon.modelos.Pokemon;
import br.pokemon.repositorios.PokemonRepositorio;

@Service
public class PokemonServico {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;

	@Autowired
	private ModelMapper modelMapper;
	
	private LeituraTxt leituraTxt = new LeituraTxt();
	
	public PokemonDto criarPokemon(PokemonForm pokemon) {
		Optional<Pokemon> findByNome = pokemonRepositorio.findByNome(pokemon.getNome());	
		if(findByNome.isPresent()) {
			throw new ErrosExcecao("Já existe um pokemon cadastrado com esse nome");
		}
		return  modelMapper.map(pokemonRepositorio.saveAndFlush(modelMapper.map(pokemon, Pokemon.class)), PokemonDto.class);
	}
	
	public List<PokemonDto> listaPokemons() {
		List<PokemonDto> listaPokemons = new ArrayList<>();
		pokemonRepositorio.findAll().forEach(p -> listaPokemons.add(modelMapper.map(p, PokemonDto.class)));
		return listaPokemons;
	}	

	public List<PokemonDto> buscaTiposPokemons(String tipo1, String tipo2) {
		List<PokemonDto> listaPokemons = new ArrayList<>();
		if (!tipo1.isBlank() || !tipo2.isBlank()) {
			return buscaTipo(tipo1, tipo2, listaPokemons);
		}
		return listaPokemons();		
	}

	private List<PokemonDto> buscaTipo(String tipo1, String tipo2, List<PokemonDto> listaPokemons) {
		List<Pokemon> buscaTipo;		
		if (!tipo1.isBlank() && tipo2.isBlank()) {
			Tipo tipoPokemon1 = leituraTxt.tipoPokemon(tipo1);
			buscaTipo = pokemonRepositorio.findByTipo1(tipoPokemon1);
		} else if (tipo1.isBlank() && !tipo2.isBlank()) {
			Tipo tipoPokemon2 = leituraTxt.tipoPokemon(tipo2);
			buscaTipo = pokemonRepositorio.findByTipo2(tipoPokemon2);
		} else {
			Tipo tipoPokemon1 = leituraTxt.tipoPokemon(tipo1);
			Tipo tipoPokemon2 = leituraTxt.tipoPokemon(tipo2);
			buscaTipo = pokemonRepositorio.findByTipo1AndTipo2(tipoPokemon1,tipoPokemon2);
		}
		
		buscaTipo.stream().forEach(p -> listaPokemons.add(modelMapper.map(p, PokemonDto.class)));
		return listaPokemons;
	}
	
	
	public PokemonDto buscaPokemonPorId(Long id) {
		PokemonDto dto = new PokemonDto();
		Optional<Pokemon> pokemon = pokemonRepositorio.findById(id);
		if(pokemon.isPresent()) {
			dto = modelMapper.map(pokemon.get(), PokemonDto.class);
		}
		return dto;
	}
	
	public PokemonDto buscaPokemonPorNome(String nome) {
		PokemonDto dto = new PokemonDto();
		Optional<Pokemon> pokemon = pokemonRepositorio.findByNome(nome);
		if(pokemon.isPresent()) {
			dto = modelMapper.map(pokemon.get(), PokemonDto.class);
		}
		return dto;
	}

}
