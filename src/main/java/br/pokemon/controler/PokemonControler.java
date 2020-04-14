package br.pokemon.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.dto.PokemonDto;
import br.pokemon.servico.PokemonServico;

@RestController
@RequestMapping("/pokemons")
public class PokemonControler {

	private static final String BUSCAR_POKEMON_TIPO = "/buscar";
	private static final String BUSCAR_POKEMON_ID = "/buscar/{id}";
	private static final String BUSCAR_POKEMON_NOME = "/buscarNome/{nome}";
	
	@Autowired
	private PokemonServico pokemonServico;
	
	@GetMapping
	public ResponseEntity<List<PokemonDto>> listaPokemon() {
		List<PokemonDto> lista = pokemonServico.listaPokemons();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lista);
	}	
	
	@GetMapping(BUSCAR_POKEMON_TIPO)
	public ResponseEntity<List<PokemonDto>> buscaTipoPokemon(@RequestParam(required = false) String tipo1, @RequestParam(required = false) String tipo2) {
		List<PokemonDto> lista = pokemonServico.buscaTiposPokemons(tipo1, tipo2);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(BUSCAR_POKEMON_ID)
	public ResponseEntity<PokemonDto> buscaPokemonPorId(@PathVariable(name = "id", required = true) Long id) {
		PokemonDto busca = pokemonServico.buscaPokemonPorId(id);
		if (busca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(busca);
	}
	
	@GetMapping(BUSCAR_POKEMON_NOME)
	public ResponseEntity<PokemonDto> buscaPokemonPorNome(@PathVariable(name = "nome", required = true) String nome) {
		PokemonDto busca = pokemonServico.buscaPokemonPorNome(nome);
		if (busca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(busca);
	}
	
}
