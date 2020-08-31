package br.pokemon.dto;

import java.util.HashSet;
import java.util.Set;

import br.pokemon.modelo.Pokemon;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioCompletoDto {

	private Long id;

	private String nome;
	
	private String apelido;
	
	private Set<Pokemon> listaPokemons = new HashSet<>();
	
}
