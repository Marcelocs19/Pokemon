package br.pokemon.configurar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.pokemon.modelo.Pokemon;

public class LeituraTxt {

	public static Map<Long, Pokemon> leitura() throws NumberFormatException, IOException {
		File fileReader = new File("C:\\Eclipse\\WorkspaceDbServer\\Pokemon\\src\\main\\resources\\Lista Pokemons.txt");
		FileReader fr = new FileReader(fileReader);
		Map<Long, Pokemon> listaPokemonMap = new HashMap<Long, Pokemon>();
		BufferedReader bufferedReader = new BufferedReader(fr);

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
