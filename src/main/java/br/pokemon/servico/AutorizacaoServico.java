package br.pokemon.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.pokemon.excecao.ErrosExcecao;
import br.pokemon.modelo.Usuario;
import br.pokemon.repositorio.UsuarioRepositorio;

@Service
public class AutorizacaoServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void trocarSenhaUsuario(String email, String novaSenha) {
		Usuario usuario = usuarioRepositorio.findByEmail(email).orElseThrow(() -> new ErrosExcecao("Email não cadastrado"));
		usuario.setSenha(bCryptPasswordEncoder.encode(novaSenha));
		usuarioRepositorio.saveAndFlush(usuario);
	}
	
}
