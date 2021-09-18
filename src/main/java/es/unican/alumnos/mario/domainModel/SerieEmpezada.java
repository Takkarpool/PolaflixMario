package es.unican.alumnos.mario.domainModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class SerieEmpezada{


	@Id
	@GeneratedValue
	@JsonView({Views.DescripcionSerie.class,Views.DescripcionUsuario.class})
	protected int idSerieEmpezada;
	
	@JsonView({Views.DescripcionSerie.class,Views.DescripcionUsuario.class})
	public int ultimoCapituloVisto;
	
	@JsonView({Views.DescripcionSerie.class,Views.DescripcionUsuario.class})
	public int ultimaTemporadaVista;
	
	@JsonView({Views.DescripcionSerie.class,Views.DescripcionUsuario.class})
	@OneToOne(cascade = CascadeType.ALL)
	public Serie serie;

	public SerieEmpezada() {}
	
	public SerieEmpezada(Serie serie, int ultimaTemporadaVista, int ultimoCapituloVisto) {
		setSerie(serie);
		setUltimaTemporadaVista(ultimaTemporadaVista);
		setUltimoCapituloVisto(ultimoCapituloVisto);
	}
	
	
	public int getUltimoCapituloVisto() {
		return ultimoCapituloVisto;
	}
	public void setUltimoCapituloVisto(int ultimoCapituloVisto) {
		this.ultimoCapituloVisto = ultimoCapituloVisto;
	}
	public int getUltimaTemporadaVista() {
		return ultimaTemporadaVista;
	}
	public void setUltimaTemporadaVista(int ultimaTemporadaVista) {
		this.ultimaTemporadaVista = ultimaTemporadaVista;
	}

	public Capitulo verCapitulo(Temporada temporada, int numCapitulo) {
		return temporada.verCapitulo(numCapitulo);
	}
	
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public int getIdSerieEmpezada() {
		return idSerieEmpezada;
	}

	public void setIdSerieEmpezada(int idSerieEmpezada) {
		this.idSerieEmpezada = idSerieEmpezada;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + getIdSerieEmpezada();
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SerieEmpezada other = (SerieEmpezada) obj;
		if (getIdSerieEmpezada() != other.getIdSerieEmpezada())
			return false;
		return true;
	}
	
	public int getIdSerie() {
		return this.serie.getIdSerie();
	}
}