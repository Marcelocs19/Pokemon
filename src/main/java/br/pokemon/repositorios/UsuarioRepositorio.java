package br.pokemon.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pokemon.modelos.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByApelido(String apelido);
	
	Optional<Usuario> findByEmail(String email);
}
