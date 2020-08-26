package br.pokemon.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@Column(name = "APELIDO", nullable = false, unique = true)
	private String apelido;
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "SENHA", nullable = false)
	private String senha;
		
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_pokemon_usuario", 
		joinColumns = @JoinColumn(name = "pokemon_id"),
		inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Pokemon> listaPokemons = new ArrayList<>();
	

}
