package main;

import java.util.OptionalDouble;

import model.SerieBase;

public class SeriePersonalizada {

	private String titulo;
	private Integer totalTemporadas;
	private Double avaliacao;
	//enum personalizado pois sempre vai ser uma lista fixa de opções
	private GenerosEnum genero;
	private String atores;
	private String poster;
	private String sinopse;
	
	
	public SeriePersonalizada(SerieBase serie) {
		super();
		this.titulo = serie.titulo();
		this.totalTemporadas = serie.totalTemporadas();
		// em vez do try catch poderia ser: OptionalDouble.of(Double.valueOf(serie.avaliacao())).orElse(0);
		try{
			this.avaliacao = Double.valueOf(serie.avaliacao());
		}catch(NumberFormatException e){
			this.avaliacao = 0.0;
		}
		//pega o primeiro genero que vem da api
		this.genero = GenerosEnum.fromString(serie.genero().split(",")[0].trim());
		this.atores = serie.atores();
		this.poster = serie.poster();
		this.sinopse = serie.sinopse();
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
				+ avaliacao + ", atores=" + atores + ", poster=" + poster + ", sinopse="
				+ sinopse;
	}
	
	

}
