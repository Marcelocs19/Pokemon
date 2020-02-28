package br.pokemon.configurar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.pokemon.repositorio.PokemonRepositorio;

@Configuration
public class Inicializacao implements CommandLineRunner {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;	
	
	@Override
	public void run(String... args) throws Exception {
	
		pokemonRepositorio.deleteAll();
		
		pokemonRepositorio.saveAll(LeituraTxt.leitura());
		
	}
	

}