package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EpisodioPersonalizado {
	private Integer numeroTemporada;
	private String titulo;
	private Integer numeroEpisodio;
	private Double avaliacao;
	private LocalDate dataLancamento;
		
	public EpisodioPersonalizado(Integer numeroTemporada, EpisodioBase episodio) {
		this.numeroTemporada = numeroTemporada;
		this.titulo = episodio.titulo();
		this.numeroEpisodio = episodio.numero();
		try{
			this.avaliacao = Double.valueOf(episodio.avaliacao());
		}catch(NumberFormatException e){
			this.avaliacao = 0.0;
		}
		try {
			this.dataLancamento = LocalDate.parse(episodio.dataLancamento());
				
		}catch(DateTimeParseException e) {
			this.dataLancamento = null;
		}
		
		}
	
	public Integer getnumeroTemporada() {
		return numeroTemporada;
	}
	public void setnumeroTemporada(Integer numeroTemporada) {
		this.numeroTemporada = numeroTemporada;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getNumeroEpisodio() {
		return numeroEpisodio;
	}
	public void setNumeroEpisodio(Integer numeroEpisodio) {
		this.numeroEpisodio = numeroEpisodio;
	}
	public Double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
	public String toString() {
		return "EpisodioPersonalizado [numeroTemporada=" + numeroTemporada + ", titulo=" + titulo + ", numeroEpisodio="
				+ numeroEpisodio + ", avaliacao=" + avaliacao + ", dataLancamento=" + dataLancamento + "]";
	}
	
	
}
