package br.pokemon.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pokemon.modelo.Pokemon;
import br.pokemon.tipo.Tipo;

@Repository
public interface PokemonRepositorio extends JpaRepository<Pokemon, Long> {
	
	List<Pokemon> findByTipo1(Tipo tipo);

}
