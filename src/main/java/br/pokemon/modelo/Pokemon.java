package br.pokemon.modelo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "Tipo1")
	private Tipo tipo1;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Tipo2")
	private Tipo tipo2;
	
	@Column(name = "DESCRICAO", length = 2000, nullable = false)
	private String descricao;
	
	@ManyToMany
	@JoinTable(name = "tb_pokemon_usuario", 
		joinColumns = @JoinColumn(name = "pokemon_id"),
		inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> listaUsuarios;
	
	
	
}
