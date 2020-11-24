package br.pokemon.configuracoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.pokemon.repositorios.PokemonRepositorio;

@Configuration
public class Inicializacao implements CommandLineRunner {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;	
	
	private LeituraTxt leituraTxt = new LeituraTxt();
	
	@Override
	public void run(String... args) throws Exception {
	
		pokemonRepositorio.deleteAll();
		
		pokemonRepositorio.saveAll(leituraTxt.leitura());
		
	}
	

}
