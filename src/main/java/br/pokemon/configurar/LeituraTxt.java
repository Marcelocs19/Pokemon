package br.pokemon.configurar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pokemon.modelo.Pokemon;
import br.pokemon.tipo.Tipo;


public class LeituraTxt {
	
	private LeituraTxt() {}

	public static List<Pokemon> leitura() throws IOException {
		String path = "src/main/resources/Lista Pokemons.txt";

		File fileReader = new File(path);
		FileReader fr = new FileReader(fileReader);
		List<Pokemon> listaPokemonMap = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(fr)) {

			Long id = 0L;
			String nome = "";
			String tipo1 = "";
			String tipo2 = "";
			String descricao = "";
			String line = "";

			while (bufferedReader.ready()) {

				line = bufferedReader.readLine();

				String[] arrayAux = new String[4];
				arrayAux = line.split(";");

				id = Long.parseLong(arrayAux[0].substring(3));
				StringBuilder aux = new StringBuilder();

				for (int i = 0; i < arrayAux.length; i++) {
					aux.append(arrayAux[i]);
				}

				nome = arrayAux[1].replace("nome:", "");
				tipo1 = arrayAux[2].replace("tipo1:", "");
				if (aux.toString().contains("tipo2:")) {
					tipo2 = arrayAux[3].replace("tipo2:", "");
					descricao = arrayAux[4].replace("descricao", "");
				} else {
					tipo2 = tipo1;
					descricao = arrayAux[3].replace("descricao", "");
				}

				Pokemon pokemon = new Pokemon();
				pokemon.setId(id);
				pokemon.setNome(nome);
				pokemon.setDescricao(descricao);
				pokemon.setTipo1(tipoPokemon(tipo1));
				pokemon.setTipo2(tipoPokemon(tipo2));
				listaPokemonMap.add(pokemon);
			}
		}

		return listaPokemonMap;

	}

	private static Tipo tipoPokemon(String tipoPokemon) {
		List<Tipo> listaTipos = new ArrayList<>();
		Tipo aux = null;
		listaTipos.addAll(Arrays.asList(Tipo.ACO, Tipo.AGUA, Tipo.DRAGAO, Tipo.ELETRICO, Tipo.FADA, Tipo.FANTASMA, Tipo.FOGO,
				Tipo.GELO, Tipo.GRAMA, Tipo.INSETO, Tipo.LUTADOR, Tipo.NORMAL, Tipo.NOTURNO, Tipo.PEDRA, Tipo.PEDRA,
				Tipo.PSIQUICOS, Tipo.TERRA, Tipo.VENENOSO, Tipo.VOADOR));
		for (int i = 0; i < listaTipos.size(); i++) {
			if (listaTipos.get(i).getDescricao().equals(tipoPokemon)) {
				aux = listaTipos.get(i);
			}
		}
		return aux;
	}

}
