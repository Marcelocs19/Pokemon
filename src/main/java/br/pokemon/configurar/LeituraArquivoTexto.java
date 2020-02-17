package br.pokemon.configurar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.pokemon.modelo.Pokemon;

public class LeituraArquivoTexto {

	public Map<Long, Pokemon> lerArquivotxt() throws IOException {
		FileReader fileReader = new FileReader("Lista Pokemons.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		Map<Long, Pokemon> listaPokemonMap = new HashMap<Long, Pokemon>();

		Long id = 0L;
		String nome = "";
		String line = "";

		while (bufferedReader.ready()) {

			line = bufferedReader.readLine();

			String arrayAux[] = new String[2];
			arrayAux = line.split(" ");

			id = Long.parseLong(arrayAux[0]);
			nome = arrayAux[1];

			Pokemon pokemon = new Pokemon();
			pokemon.setId(id);
			pokemon.setNome(nome);
			
			listaPokemonMap.put(id, pokemon);
		}

		bufferedReader.close();
		return listaPokemonMap;
	}

}
