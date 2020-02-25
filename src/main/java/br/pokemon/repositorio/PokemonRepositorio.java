package br.pokemon.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pokemon.modelo.Pokemon;

@Repository
public interface PokemonRepositorio extends JpaRepository<Pokemon, Long> {

}
