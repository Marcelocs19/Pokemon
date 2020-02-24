package br.pokemon.controler;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.modelo.Pokemon;
import br.pokemon.servico.PokemonServico;

@RestController
@RequestMapping("/pokemons")
public class PokemonControler {

	@Autowired
	private PokemonServico pokemonServico;
	
	@GetMapping
	public ResponseEntity<List<Pokemon>> listaPokemon() throws IOException {
		List<Pokemon> lista = pokemonServico.listaPokemons();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lista);
	}		

	
	
}
