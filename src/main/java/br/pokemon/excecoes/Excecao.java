package br.pokemon.excecoes;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL) // só inclui campos que não forem nulos
@Data
public class Excecao {
	
	private Integer status;
	
	private OffsetDateTime dataHora;
	
	private String titulo;
	
	private List<CamposErros> campos;

}
