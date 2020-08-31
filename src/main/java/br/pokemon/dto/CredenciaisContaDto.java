package br.pokemon.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredenciaisContaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String apelido;
	
	private String senha;

}
