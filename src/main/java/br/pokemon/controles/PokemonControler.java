package br.pokemon.controles;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.dtos.PokemonDto;
import br.pokemon.endpoints.ConstantesEndPoints;
import br.pokemon.forms.PokemonForm;
import br.pokemon.servicos.PokemonServico;

@RestController
@RequestMapping(ConstantesEndPoints.EndPointPokemon.pokemonRequest)
public class PokemonControler {
	
	@Autowired
	private PokemonServico pokemonServico;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@Transactional
	public ResponseEntity<PokemonDto> criarPokemon(@Valid @RequestBody PokemonForm pokemon) {
		return ResponseEntity.ok().body(pokemonServico.criarPokemon(pokemon));
	}
	
	@GetMapping
	public ResponseEntity<List<PokemonDto>> listarPokemon() {
		List<PokemonDto> lista = pokemonServico.listaPokemons();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lista);
	}	
	
	@GetMapping(ConstantesEndPoints.EndPointPokemon.pokemonTipo)
	public ResponseEntity<List<PokemonDto>> buscarTipoPokemon(@RequestParam(required = false) String tipo1, @RequestParam(required = false) String tipo2) {
		List<PokemonDto> lista = pokemonServico.buscaTiposPokemons(tipo1, tipo2);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(ConstantesEndPoints.EndPointPokemon.pokemonId)
	public ResponseEntity<PokemonDto> buscarPokemonPorId(@PathVariable(name = "id", required = true) Long id) {
		PokemonDto busca = pokemonServico.buscaPokemonPorId(id);
		if (busca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(busca);
	}
	
	@GetMapping(ConstantesEndPoints.EndPointPokemon.pokemonNome)
	public ResponseEntity<PokemonDto> buscarPokemonPorNome(@PathVariable(name = "nome", required = true) String nome) {
		PokemonDto busca = pokemonServico.buscaPokemonPorNome(nome);
		if (busca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(busca);
	}
	
}
