package br.pokemon.seguranca.token;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.dtos.EmailDto;
import br.pokemon.seguranca.UserSpringSecurity;
import br.pokemon.seguranca.servico.AutorizacaoServico;
import br.pokemon.seguranca.user.UserService;

@RestController
@RequestMapping(value = "/autorizacao")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AutorizacaoServico autorizacaoServico;
	
	@PostMapping(value = "/atualiza_token")
	public ResponseEntity<Void> atualizaToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.autenticado();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/novaSenha")
	public ResponseEntity<Void> atualizaSenha(@Valid @RequestBody EmailDto dto) {
		autorizacaoServico.trocarSenhaUsuario(dto.getEmail(), dto.getNovaSenha());
		return ResponseEntity.noContent().build();
	}
	
}
