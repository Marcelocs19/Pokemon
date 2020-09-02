package br.pokemon.configuracoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.pokemon.enums.Perfil;
import br.pokemon.modelos.Usuario;
import br.pokemon.repositorios.PokemonRepositorio;
import br.pokemon.repositorios.UsuarioRepositorio;

@Configuration
public class Inicializacao implements CommandLineRunner {

	@Autowired
	private PokemonRepositorio pokemonRepositorio;	
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Override
	public void run(String... args) throws Exception {
	
		pokemonRepositorio.deleteAll();
		
		pokemonRepositorio.saveAll(LeituraTxt.leitura());
		
		Usuario admin = new Usuario();
		admin.setApelido("admin");
		admin.setNome("Marcelo");
		admin.setSenha(pe.encode("123"));
		admin.setEmail("marcelo@gmail.com");
		admin.addPerfil(Perfil.ADMIN);
		
		usuarioRepositorio.saveAndFlush(admin);
		
	}
	

}
