package br.pokemon.controles;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.dtos.PokemonDto;
import br.pokemon.dtos.UsuarioCompletoDto;
import br.pokemon.dtos.UsuarioDto;
import br.pokemon.endpoints.ConstantesEndPoints;
import br.pokemon.forms.CadastroUsuarioForm;
import br.pokemon.forms.UsuarioForm;
import br.pokemon.servicos.UsuarioServico;

@RestController
@RequestMapping(ConstantesEndPoints.EndPointUsuario.usuarioRequest)
public class UsuarioControler {
	
	@Autowired
	private UsuarioServico usuarioServico;
		
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
		return ResponseEntity.ok().body(usuarioServico.listarUsuarios());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> criarPokemon(@Valid @RequestBody CadastroUsuarioForm usuario) {
		return ResponseEntity.ok().body(usuarioServico.criarUsuario(usuario));
	}
	
	@PostMapping(ConstantesEndPoints.EndPointUsuario.adicionaPokemon)
	@Transactional
	public ResponseEntity<UsuarioCompletoDto> adicionarPokemonNaLista(@PathVariable(name = "id", required = true) Long id, @Valid @RequestBody UsuarioForm usuario) {
		return ResponseEntity.ok().body(usuarioServico.adicionarPokemonNaLista(id, usuario));
	}
	
	@GetMapping(ConstantesEndPoints.EndPointUsuario.listaPokemon)
	public ResponseEntity<List<PokemonDto>> listarPokemonsUsuario() {
		return ResponseEntity.ok().body(usuarioServico.listarPokemonsUsuario());
	}
	
}
