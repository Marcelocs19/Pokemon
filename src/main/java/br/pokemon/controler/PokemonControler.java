package br.pokemon.controler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.configurar.LeituraArquivoTexto;
import br.pokemon.modelo.Pokemon;
import br.pokemon.repositorio.PokemonRepositorio;

@RestController
@RequestMapping("/pokemons")
public class PokemonControler {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;
		
	private LeituraArquivoTexto texto;
	
	@GetMapping
	public ResponseEntity<Map<Long, Pokemon>> listaPokemon() throws IOException {
		Map<Long, Pokemon> lerArquivotxt = texto.lerArquivotxt();
		if (lerArquivotxt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(lerArquivotxt);
	}	
	
	
}
