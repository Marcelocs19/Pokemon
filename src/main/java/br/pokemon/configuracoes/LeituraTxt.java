package br.pokemon.configuracoes;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;

import br.pokemon.constantes.Tipo;
import br.pokemon.modelos.Pokemon;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {
	
	private static String caminho = "src/main/resources/Lista Pokemons.txt";
	
	private List<Tipo> listaTipos = Arrays.asList(Tipo.ACO, Tipo.AGUA, Tipo.DRAGAO, Tipo.ELETRICO, Tipo.FADA, Tipo.FANTASMA, Tipo.FOGO,
			Tipo.GELO, Tipo.GRAMA, Tipo.INSETO, Tipo.LUTADOR, Tipo.NORMAL, Tipo.NOTURNO, Tipo.PEDRA, Tipo.PEDRA,
			Tipo.PSIQUICO, Tipo.TERRA, Tipo.VENENOSO, Tipo.VOADOR);
	
	private String[] cabecalho = {"CD_POKEMON", "NOME", "TIPO_UM", "TIPO_DOIS", "DESCRICAO"};

	private List<String[]> linhas = new ArrayList<>();
	
	public List<Pokemon> leitura() throws IOException {		
		
		List<Pokemon> listaPokemonMap = new ArrayList<>();
		
        Path path = Paths.get(caminho);
		
        Files.lines(path).forEach(linha -> {
        	var tipo1 = "";
    		var tipo2 = "";
    		var descricao = "";
    		
        	String[] arrayAux = new String[4];
			arrayAux = linha.split(";");

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

			var pokemon = new Pokemon();
			
			pokemon.setId(Long.parseLong(arrayAux[0].substring(3)));
			pokemon.setNome(arrayAux[1].replace("nome:", ""));
			pokemon.setDescricao(descricao);
			pokemon.setTipo1(tipoPokemon(tipo1));
			pokemon.setTipo2(tipoPokemon(tipo2));
			
			linhas.add(new String[]{Long.toString(pokemon.getId()), pokemon.getNome(), pokemon.getTipo1().getDescricao(), pokemon.getTipo2().getDescricao(), pokemon.getDescricao()});

			listaPokemonMap.add(pokemon);
		
        });
                
		return listaPokemonMap;

	}
	
	private void criaCsv() throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get("Lista.csv"));
        CSVWriter csvWriter = new CSVWriter(writer);

        csvWriter.writeNext(cabecalho);
        csvWriter.writeAll(linhas);

        csvWriter.flush();
        writer.close();
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
