package br.pokemon.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.pokemon.tipo.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "pokemon")
@Data
public class Pokemon {
	
	private Long id;
	
	private String nome;
	
	private Tipo tipo;
	
}
