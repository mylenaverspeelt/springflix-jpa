package com.projeto.springflixjpa.principal;

import java.util.*;
import java.util.stream.Collectors;

import com.projeto.springflixjpa.model.EpisodioPersonalizado;
import com.projeto.springflixjpa.model.SerieBase;
import com.projeto.springflixjpa.model.SeriePersonalizada;
import com.projeto.springflixjpa.model.Temporada;
import com.projeto.springflixjpa.repository.SerieRepository;
import com.projeto.springflixjpa.service.ConsumoApi;
import com.projeto.springflixjpa.service.ConverteDados;

public class Principal {
	private Scanner leitura = new Scanner(System.in);
	private ConsumoApi consumo = new ConsumoApi();
	private ConverteDados conversor = new ConverteDados();
	private static final String ENDERECO = "https://www.omdbapi.com/?t=";
	private static final String API_KEY = "&apikey=6585022c";
	private List<SeriePersonalizada> listaSeriesBanco = new ArrayList<>();
	private SerieRepository repositorio;

	public Principal(SerieRepository repositorio) {
		this.repositorio = repositorio;
	}

	public void exibeMenu() {

		var opcaoEscolhida = -1;

		while (opcaoEscolhida != 0) {
			var menu = """
                    1 - Cadastrar nova série no banco
                    2 - Listar séries salvas
                    3 - Buscar temporadas 
                    4 - Procurar série por trecho do nome
                    0 - Sair
                    """;

			System.out.println(menu);
			var opcao = leitura.nextInt();
			leitura.nextLine();

			switch (opcao) {
				case 1:
					salvarSerieBanco();
					break;
				case 2:
					listarSeriesBuscadas();
					break;
				case 3:
					buscarTemporadasDaSerie();
					break;
				case 4:
					buscarSeriePorParteDoTitulo();
					break;
				case 0:
					System.out.println("Programa finalizado com sucesso!");
					System.exit(0);
					break;
				default:
					System.out.println("Opção inválida");
			}
		}
	}

	private void buscarSeriePorParteDoTitulo() {
		System.out.println("Digite o nome da série para busca:");
		var nomeSerie = leitura.nextLine();
		Optional<SeriePersonalizada> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

		if(serieBuscada.isPresent()){
			System.out.println("Série encontrada: " + serieBuscada.get().getTitulo());
		}else{
			System.out.println("Série não encontrada. Adicione uma nova série (opção 1)!");
		}
	}

	private void listarSeriesBuscadas() {
		listaSeriesBanco = repositorio.findAll();

		listaSeriesBanco.stream().sorted(Comparator.comparing(SeriePersonalizada::getGenero))
				.forEach(item -> System.out.println(item.getTitulo()));
	}

	private void salvarSerieBanco() {
		SerieBase serieBuscadaAPI = buscarSerieWeb();
		SeriePersonalizada novaSerie = new SeriePersonalizada(serieBuscadaAPI);
		repositorio.save(novaSerie);
		System.out.println("Série salva com sucesso: " + serieBuscadaAPI.titulo());
	}

	private SerieBase buscarSerieWeb() {
		System.out.println("Digite o nome da série para busca:");
		var nomeSerie = leitura.nextLine();
		var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		SerieBase dadosSerie = conversor.obterDados(json, SerieBase.class);
		return dadosSerie;
	}

	private void buscarTemporadasDaSerie() {

		listarSeriesBuscadas();
		System.out.println("Dentre as séries mostradas acima, digite qual delas você deseja ver as temporadas:");
		String nomeSerie = leitura.nextLine();

		Optional<SeriePersonalizada> serieEncontradaBanco = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

		if (serieEncontradaBanco.isPresent()) {

			var serieEncontrada = serieEncontradaBanco.get();

			List<Temporada> temporadas = new ArrayList<>();

			for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
				var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
				Temporada temporada = conversor.obterDados(json, Temporada.class);
				temporadas.add(temporada);
			}
			temporadas.forEach(System.out::println);

			List<EpisodioPersonalizado> episodiosPorTemporada = temporadas.stream().flatMap(d -> d.episodios().stream().map(e -> new EpisodioPersonalizado(d.numero(), e))).collect(Collectors.toList());

			serieEncontrada.setListaEpisodios(episodiosPorTemporada);
			repositorio.save(serieEncontrada);
		} else {
			System.out.println("Série não encontrada!");
		}
	}
}