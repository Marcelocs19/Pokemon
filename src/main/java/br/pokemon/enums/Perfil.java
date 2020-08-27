package br.pokemon.enums;

import br.pokemon.excecao.NegocioExcecao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	
	private String descricao;
	
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil p: Perfil.values()) {
			if(cod.equals(p.getCod())) {
				return p;
			}
		}
		
		throw new NegocioExcecao("Id inválido: " + cod);
	}
	
}
