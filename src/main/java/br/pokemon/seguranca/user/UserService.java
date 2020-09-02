package br.pokemon.seguranca.user;

import org.springframework.security.core.context.SecurityContextHolder;

import br.pokemon.seguranca.UserSpringSecurity;

public class UserService {
	
	public static UserSpringSecurity autenticado() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
