package br.pokemon.excecao;

public class ErrosExcecao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ErrosExcecao(String mensagem) {
		super(mensagem);
	}

	public ErrosExcecao(String msg, Throwable cause) {
		super(msg,cause);
	}
}
