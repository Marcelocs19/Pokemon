package br.pokemon.servico;

import org.springframework.security.core.context.SecurityContextHolder;

import br.pokemon.seguranca.UserSpringSecurity;

public class Servico {
	
	public static UserSpringSecurity autenticado() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
