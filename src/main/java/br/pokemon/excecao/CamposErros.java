package br.pokemon.excecao;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CamposErros {
	
	private String nome;
	
	private String mensagem;

}
