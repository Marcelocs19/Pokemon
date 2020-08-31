package br.pokemon.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.pokemon.dto.PokemonDto;
import br.pokemon.dto.UsuarioCompletoDto;
import br.pokemon.dto.UsuarioDto;
import br.pokemon.enums.Perfil;
import br.pokemon.excecao.ErrosExcecao;
import br.pokemon.form.CadastroUsuarioForm;
import br.pokemon.form.UsuarioForm;
import br.pokemon.modelo.Pokemon;
import br.pokemon.modelo.Usuario;
import br.pokemon.repositorio.PokemonRepositorio;
import br.pokemon.repositorio.UsuarioRepositorio;
import br.pokemon.seguranca.UserSpringSecurity;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PokemonRepositorio pokemonRepositorio;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ModelMapper modelMapper;

	public List<UsuarioDto> listarUsuarios() {
		List<Usuario> findAll = usuarioRepositorio.findAll();
		List<UsuarioDto> retorno = new ArrayList<>();
		findAll.stream().forEach(u -> retorno.add(modelMapper.map(u, UsuarioDto.class)));
		return retorno;
	}

	public UsuarioDto criarUsuario(CadastroUsuarioForm usuario) {
		Optional<Usuario> findByApelido = usuarioRepositorio.findByApelido(usuario.getApelido());
		if (findByApelido.isPresent()) {
			throw new ErrosExcecao("Já existe um usuário cadastrado com esse apelido");
		}
		usuario.setSenha(pe.encode(usuario.getSenha()));
		Usuario novoUsuario = modelMapper.map(usuario, Usuario.class);
		novoUsuario.addPerfil((usuario.isAdminCliente()) ? Perfil.ADMIN : Perfil.CLIENTE);
		return modelMapper.map(usuarioRepositorio.saveAndFlush(novoUsuario), UsuarioDto.class);
	}

	public UsuarioCompletoDto adicionarPokemonNaLista(Long id, UsuarioForm usuario) {
		Optional<Pokemon> findById = pokemonRepositorio.findById(id);
		if (!findById.isPresent()) {
			throw new ErrosExcecao("Nenhum registro foi encontrado");
		}

		Optional<Usuario> findByApelido = buscaUsuarioApelido(usuario.getApelido());

		findByApelido.get().getListaPokemons().add(findById.get());

		return modelMapper.map(usuarioRepositorio.saveAndFlush(findByApelido.get()), UsuarioCompletoDto.class);
	}

	public List<PokemonDto> listarPokemonsUsuario() {
		UserSpringSecurity user = AutenticacaoServico.autenticado();
		if (user == null || !user.hasRole(Perfil.ADMIN)) {
			throw new ErrosExcecao("Nenhum usuário foi encontrado com esse apelido");
		}

		List<Pokemon> listaPokemons = buscaUsuarioApelido(user.getUsername()).get().getListaPokemons();
		List<PokemonDto> retorno = new ArrayList<>();
		listaPokemons.stream().forEach(p -> retorno.add(modelMapper.map(p, PokemonDto.class)));
		return retorno;
	}

	private Optional<Usuario> buscaUsuarioApelido(String apelido) {
		Optional<Usuario> findByApelido = usuarioRepositorio.findByApelido(apelido);
		if (!findByApelido.isPresent()) {
			throw new ErrosExcecao("Nenhum usuário foi encontrado com esse apelido");
		}
		return findByApelido;
	}


}
