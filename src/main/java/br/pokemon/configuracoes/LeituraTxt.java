package br.pokemon.configuracoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pokemon.constantes.Tipo;
import br.pokemon.modelos.Pokemon;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {
	
	private static String path = "src/main/resources/Lista Pokemons.txt";
	
	private List<Tipo> listaTipos = Arrays.asList(Tipo.ACO, Tipo.AGUA, Tipo.DRAGAO, Tipo.ELETRICO, Tipo.FADA, Tipo.FANTASMA, Tipo.FOGO,
			Tipo.GELO, Tipo.GRAMA, Tipo.INSETO, Tipo.LUTADOR, Tipo.NORMAL, Tipo.NOTURNO, Tipo.PEDRA, Tipo.PEDRA,
			Tipo.PSIQUICO, Tipo.TERRA, Tipo.VENENOSO, Tipo.VOADOR);


	public List<Pokemon> leitura() throws IOException {
		File fileReader = new File(path);
		
		List<Pokemon> listaPokemonMap = new ArrayList<>();
		
		var tipo1 = "";
		var tipo2 = "";
		var descricao = "";
		
		try (FileReader fr = new FileReader(fileReader); BufferedReader bufferedReader = new BufferedReader(fr)) {

			while (bufferedReader.ready()) {
				var line = bufferedReader.readLine();

				String[] arrayAux = new String[4];
				arrayAux = line.split(";");

				StringBuilder aux = new StringBuilder();

				Arrays.asList(arrayAux).forEach(i -> aux.append(i));				

				tipo1 = arrayAux[2].replace("tipo1:", "");
				if (aux.toString().contains("tipo2:")) {
					tipo2 = arrayAux[3].replace("tipo2:", "");
					descricao = arrayAux[4].replace("descricao:", "");
				} else {
					tipo2 = tipo1;
					descricao = arrayAux[3].replace("descricao:", "");
				}

				Pokemon pokemon = new Pokemon();
				
				pokemon.setId(Long.parseLong(arrayAux[0].substring(3)));
				pokemon.setNome(arrayAux[1].replace("nome:", ""));
				pokemon.setDescricao(descricao);
				pokemon.setTipo1(tipoPokemon(tipo1));
				pokemon.setTipo2(tipoPokemon(tipo2));
				
				listaPokemonMap.add(pokemon);
			}
		}

		return listaPokemonMap;

	}

	public Tipo tipoPokemon(String tipoPokemon) {
		for (Tipo tipo : listaTipos) {
			if(tipo.getDescricao().equals(tipoPokemon)) {
				return tipo;
			}
		}
		return null;
	}

}
