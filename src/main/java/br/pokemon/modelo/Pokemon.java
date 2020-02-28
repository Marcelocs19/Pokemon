package br.pokemon.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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

	@Id
	private Long id;
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;
		
	@Enumerated
	@Column(name = "Tipo1")
	private Tipo tipo1;
	
	@Enumerated
	@Column(name = "Tipo2")
	private Tipo tipo2;
	
	@Column(name = "DESCRICAO", length = 2000, nullable = false)
	private String descricao;
	
	
	
}
