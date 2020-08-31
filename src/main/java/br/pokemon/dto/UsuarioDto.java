package br.pokemon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioDto {
	
	private Long id;

	private String nome;
	
	private String apelido;
}
