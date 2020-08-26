package br.pokemon.controler;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.constante.ConstantesEndPoints;
import br.pokemon.dto.UsuarioCompletoDto;
import br.pokemon.dto.UsuarioDto;
import br.pokemon.form.CadastroUsuarioForm;
import br.pokemon.form.UsuarioForm;
import br.pokemon.servico.UsuarioServico;

@RestController
@RequestMapping(ConstantesEndPoints.EndPointUsuario.usuarioRequest)
public class UsuarioControler {
	
	@Autowired
	private UsuarioServico usuarioServico;
		
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
	
}
