package br.pokemon.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pokemon.dto.PokemonDto;
import br.pokemon.dto.UsuarioCompletoDto;
import br.pokemon.dto.UsuarioDto;
import br.pokemon.excecao.NegocioExcecao;
import br.pokemon.form.CadastroUsuarioForm;
import br.pokemon.form.UsuarioForm;
import br.pokemon.modelo.Pokemon;
import br.pokemon.modelo.Usuario;
import br.pokemon.repositorio.PokemonRepositorio;
import br.pokemon.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PokemonRepositorio pokemonRepositorio;
	
		
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioDto criarUsuario(CadastroUsuarioForm usuario) {
		Optional<Usuario> findByApelido = usuarioRepositorio.findByApelido(usuario.getApelido());
		if(findByApelido.isPresent()) {
			throw new NegocioExcecao("Já existe um usuário cadastrado com esse apelido");
		}
		return modelMapper.map(usuarioRepositorio.saveAndFlush(modelMapper.map(usuario, Usuario.class)), UsuarioDto.class);
	}
	
	public UsuarioCompletoDto adicionarPokemonNaLista(Long id, UsuarioForm usuario) {
		Optional<Pokemon> findById = pokemonRepositorio.findById(id);
		if(!findById.isPresent()) {
			throw new NegocioExcecao("Nenhum registro foi encontrado");
		}
		
		Optional<Usuario> findByApelido = buscaUsuarioApelido(usuario.getApelido());
				
		findByApelido.get().getListaPokemons().add(findById.get());
						
		return modelMapper.map(usuarioRepositorio.saveAndFlush(findByApelido.get()), UsuarioCompletoDto.class);		
	}
	
	public List<PokemonDto> listarPokemonsUsuario(UsuarioForm usuario) {
		List<Pokemon> listaPokemons = buscaUsuarioApelido(usuario.getApelido()).get().getListaPokemons();
		List<PokemonDto> retorno = new ArrayList<>();
		listaPokemons.stream().forEach(p ->	retorno.add(modelMapper.map(p, PokemonDto.class)));
		return retorno;
	}
	
	private Optional<Usuario> buscaUsuarioApelido(String apelido){
		Optional<Usuario> findByApelido = usuarioRepositorio.findByApelido(apelido);
		if(!findByApelido.isPresent()) {
			throw new NegocioExcecao("Nenhum usuário foi encontrado com esse apelido");
		}
		return findByApelido;
	}
	
}
