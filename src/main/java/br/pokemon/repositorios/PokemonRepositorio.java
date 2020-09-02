package br.pokemon.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pokemon.constantes.Tipo;
import br.pokemon.modelos.Pokemon;

@Repository
public interface PokemonRepositorio extends JpaRepository<Pokemon, Long> {
	
	List<Pokemon> findByTipo1(Tipo tipo);
	
	List<Pokemon> findByTipo2(Tipo tipo);
	
	List<Pokemon> findByTipo1AndTipo2(Tipo tipo1, Tipo tipo2);
	
	Optional<Pokemon> findByNome(String nome);

}
