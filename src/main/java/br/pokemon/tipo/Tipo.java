package br.pokemon.tipo;

public enum Tipo {

	FOGO("Fogo"),
	AGUA("Água"),
	TERRA("Terra"),
	ELETRICO("Elétrico"),
	NORMAL("Normal"),
	GRAMA("Grama"),
	GELO("Gelo"),
	LUTADOR("Lutador"),
	VENENOSO("Venenoso"),
	VOADOR("Voador"),
	PSIQUICOS("Psíquicos"),
	INSETO("Inseto"),
	PEDRA("Pedra"),
	FANTASMA("Fantasma"),
	DRAGAO("Dragão"),
	NOTURNO("Noturno"),
	FADA("Fada"),
	ACO("Aço");
	
	
	private final String descricao;
	
	private Tipo(String tipo) {
		descricao = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
