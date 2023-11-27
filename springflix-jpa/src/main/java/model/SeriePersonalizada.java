package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import main.GenerosEnum;

@Entity
@Table(name = "series")
public class SeriePersonalizada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String titulo;
	private Integer totalTemporadas;
	private Double avaliacao;
	
	// enum personalizado pois sempre vai ser uma lista fixa de opções / informa pro banco pra salvar a string do enum
	@Enumerated(EnumType.STRING)
	private GenerosEnum genero;
	private String atores;
	private String poster;
	private String sinopse;

	public SeriePersonalizada(SerieBase serie) {
		super();
		this.titulo = serie.titulo();
		this.totalTemporadas = serie.totalTemporadas();
		// em vez do try catch poderia ser:
		// OptionalDouble.of(Double.valueOf(serie.avaliacao())).orElse(0);
		try {
			this.avaliacao = Double.valueOf(serie.avaliacao());
		} catch (NumberFormatException e) {
			this.avaliacao = 0.0;
		}
		// pega o primeiro genero que vem da api
		this.genero = GenerosEnum.fromString(serie.genero().split(",")[0].trim());
		this.atores = serie.atores();
		this.poster = serie.poster();
		this.sinopse = serie.sinopse();
	}

	@Transient
	private List<EpisodioPersonalizado> listaEpisodios = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<EpisodioPersonalizado> getListaEpisodios() {
		return listaEpisodios;
	}

	public void setListaEpisodios(List<EpisodioPersonalizado> listaEpisodios) {
		this.listaEpisodios = listaEpisodios;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public GenerosEnum getGenero() {
		return genero;
	}

	public void setGenero(GenerosEnum genero) {
		this.genero = genero;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	@Override
	public String toString() {
		return "genero=" + genero + ", titulo=" + titulo + ", totalTemporadas=" + totalTemporadas + ", avaliacao="
				+ avaliacao + ", atores=" + atores + ", poster=" + poster + ", sinopse=" + sinopse;
	}

}
