package br.pokemon.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.pokemon.modelo.Usuario;
import br.pokemon.repositorio.UsuarioRepositorio;
import br.pokemon.seguranca.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String apelido) {
		Usuario usuario = usuarioRepositorio.findByApelido(apelido).orElseThrow(() -> new UsernameNotFoundException("Não foi encontrado ninguém com esse apelido: " + apelido));
		return new UserSpringSecurity(usuario.getId(), usuario.getApelido() , usuario.getSenha(), usuario.getPerfis());
	}
	
}
