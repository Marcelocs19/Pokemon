package br.pokemon.excecao;

public class NegocioExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NegocioExcecao(String mensagem) {
		super(mensagem);
	}
	
}
