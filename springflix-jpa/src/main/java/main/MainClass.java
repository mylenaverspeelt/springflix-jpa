package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Serie;
import model.Temporada;
import service.ConsumoApi;
import service.ConverteDados;

public class MainClass {
	  private Scanner leitura = new Scanner(System.in);
	    private ConsumoApi consumo = new ConsumoApi();
	    private ConverteDados conversor = new ConverteDados();
	    private final String ENDERECO = "https://www.omdbapi.com/?t=";
	    private final String API_KEY = "&apikey=6585022c";

	    public void exibeMenu() {
	        var menu = """
	                1 - Buscar séries
	                2 - Buscar episódios
	                
	                0 - Sair                                 
	                """;

	        System.out.println(menu);
	        var opcao = leitura.nextInt();
	        leitura.nextLine();

	        switch (opcao) {
	            case 1:
	                buscarSerieWeb();
	                break;
	            case 2:
	                buscarEpisodioPorSerie();
	                break;
	            case 0:
	                System.out.println("Saindo...");
	                break;
	            default:
	                System.out.println("Opção inválida");
	        }
	    }

	    private void buscarSerieWeb() {
	        Serie dados = getSerie();
	        System.out.println(dados);
	    }

	    private Serie getSerie() {
	        System.out.println("Digite o nome da série para busca");
	        var nomeSerie = leitura.nextLine();
	        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
	        Serie dados = conversor.obterDados(json, Serie.class);
	        return dados;
	    }

	    private void buscarEpisodioPorSerie(){
	        Serie Serie = getSerie();
	        List<Temporada> temporadas = new ArrayList<>();

	        for (int i = 1; i <= Serie.totalTemporadas(); i++) {
	            var json = consumo.obterDados(ENDERECO + Serie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
	            Temporada temporada = conversor.obterDados(json, Temporada.class);
	            temporadas.add(temporada);
	        }
	        temporadas.forEach(System.out::println);
	    }
	}