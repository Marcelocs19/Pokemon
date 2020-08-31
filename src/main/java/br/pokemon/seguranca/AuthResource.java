package br.pokemon.seguranca;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pokemon.servico.AutenticacaoServico;

@RestController
@RequestMapping(value = "/autorizacao")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping(value = "/atualiza_token")
	public ResponseEntity<Void> atualizaToken(HttpServletResponse response) {
		UserSpringSecurity user = AutenticacaoServico.autenticado();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

}
