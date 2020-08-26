package br.pokemon.modelo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "usuario")
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "NOME", nullable = false)
	private String nome;

	@NotBlank(message = "O campo apelido é obrigatório.")
	@Column(name = "APELIDO", nullable = false)
	private String apelido;
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@ManyToMany(mappedBy = "listaUsuarios")
	private Set<Pokemon> listaPokemons;

}
