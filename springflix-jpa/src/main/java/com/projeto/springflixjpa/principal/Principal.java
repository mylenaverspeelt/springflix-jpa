package com.projeto.springflixjpa.principal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    private final Scanner scan = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=6585022c";
    private List<SeriePersonalizada> listaSeriesBanco = new ArrayList<>();
    private SerieRepository repositorio;
    private Optional<SeriePersonalizada> serieBuscada;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {

        var opcaoEscolhida = -1;

        while (opcaoEscolhida != 0) {
            var menu = """
                    *****************************************************
                    Digite o número da opção desejada:
                                        
                    1 - Salvar nova série no banco
                    2 - Listar séries salvas
                    3 - Listar temporadas
                    4 - Procurar série por trecho do nome
                    5 - Buscar série pelo nome da atriz/ator
                    6 - Mostrar séries com avaliação acima da média
                    7 - Top 5 séries
                    8 - Buscar séries por gênero
                    9 - Top 5 episodios
                    10 - Buscar episódios a partir de uma data
                                        
                    0 - Sair
                    *****************************************************
                    """;

            System.out.println(menu);
            var opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    salvarSerieBanco();
                    break;
                case 2:
                    listarSeriesBanco();
                    break;
                case 3:
                    listarTemporadas();
                    break;
                case 4:
                    buscarSeriePorParteDoTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarSeriesAcimaDaMedia();
                    break;
                case 7:
                    buscarTop5Series();
                    break;
                case 8:
                    buscarSeriesPorCategoria();
                    break;
                case 9:
                    buscarTop5Episodios();
                    break;
                case 10:
                    buscarEpisodiosDepoisDeUmaData();
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

    private void buscarEpisodiosDepoisDeUmaData() {
        buscarSeriePorParteDoTitulo();
        if (serieBuscada.isPresent()) {
            System.out.println("Digite o ano limite de lançamento: ");
            var anoLancamento = scan.nextInt();
            scan.nextLine();

            List<EpisodioPersonalizado> espisodiosRecebidos = repositorio.episodiosPorSerieEAno(serieBuscada, anoLancamento);
            espisodiosRecebidos.forEach(item -> System.out.println(item.getTitulo()));
        }
    }

    private void listarTemporadas() {
        listarSeriesBanco();
        System.out.println("Dentre as séries mostradas acima, digite qual delas você deseja ver as temporadas:");
        String nomeSerie = scan.nextLine();
        buscarTemporadasDaSerie(nomeSerie);
    }

    private void buscarTop5Episodios() {
        buscarSeriePorParteDoTitulo();
        if (serieBuscada.isPresent()) {
            SeriePersonalizada serie = serieBuscada.get();
            List<EpisodioPersonalizado> top5episodios = repositorio.top5EpisodiosPorSerie(serie);
            System.out.println("Top 5 episódios: ");
            top5episodios.forEach(e -> System.out.println(e.getTitulo() + " / avaliação: " + e.getAvaliacao()));
        }
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Digite o gênero:");
        var genero = scan.nextLine();
        GenerosEnum generoEnum = GenerosEnum.fromPortugues(genero);
        List<SeriePersonalizada> seriesEncontradas = repositorio.findByGenero(generoEnum);
        System.out.println("Séries de " + genero + ": ");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo()));

    }

    private void buscarTop5Series() {
        List<SeriePersonalizada> seriesEncontradas = repositorio.findTop5ByOrderByAvaliacaoDesc();
        System.out.println("Top 5 séries encontradas: ");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo() + " / avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesAcimaDaMedia() {
        System.out.println("Séries com avaliações superiores a 7.0: ");

        List<SeriePersonalizada> seriesEncontradas = repositorio.findByAvaliacaoGreaterThanEqual(7.0);
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo()));
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Digite um nome de atriz/ator para busca: ");
        var nomeAtor = scan.nextLine();
        List<SeriePersonalizada> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCase(nomeAtor);
        System.out.println("Séries realizadas por " + nomeAtor + " :");
        seriesEncontradas.forEach(s -> System.out.println(s.getTitulo()));

    }

    private void buscarSeriePorParteDoTitulo() {
        System.out.println("Digite o nome da série para busca:");
        var nomeSerie = scan.nextLine();
        serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Série encontrada: " + serieBuscada.get().getTitulo());
        } else {
            System.out.println("Série não encontrada. Adicione uma nova série (opção 1)!");
        }
    }

    private void listarSeriesBanco() {
        listaSeriesBanco = repositorio.findAll();
        System.out.println("Séries salvas no banco: ");
        listaSeriesBanco.stream().sorted(Comparator.comparing(SeriePersonalizada::getGenero)).forEach(item -> System.out.println(item.getTitulo()));
    }

    private void salvarSerieBanco() {
        SerieBase serieBuscadaAPI = buscarSerieWeb();
        SeriePersonalizada novaSerie = new SeriePersonalizada(serieBuscadaAPI);
        repositorio.save(novaSerie);
        buscarTemporadasDaSerie(novaSerie.getTitulo());
        System.out.println("Série salva com sucesso: " + serieBuscadaAPI.titulo());
    }

    private SerieBase buscarSerieWeb() {
        System.out.println("Digite o nome da série que deseja cadastrar:");
        var nomeSerie = scan.nextLine();

        String regex = "^[a-zA-Z0-9\\s]+$";

        if (!nomeSerie.matches(regex)) {
            System.out.println("O nome da série deve conter apenas caracteres alfa-numéricos.");
            return buscarSerieWeb();
        } else {

            Optional<SeriePersonalizada> serieEscolhida = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
            if (serieEscolhida.isPresent()) {
                System.out.println("Série já salva no banco de dados!");
                return buscarSerieWeb();
            }

            String json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
            SerieBase dadosSerie = conversor.obterDados(json, SerieBase.class);

            if (dadosSerie.titulo() == null && dadosSerie.sinopse() == null) {
                System.out.println("Série não encontrada. Tente novamente.");
                return buscarSerieWeb();
            } else {
                return dadosSerie;
            }

        }
    }

    private void buscarTemporadasDaSerie(String novaSerie) {

        Optional<SeriePersonalizada> serieEncontradaBanco = repositorio.findByTituloContainingIgnoreCase(novaSerie);

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