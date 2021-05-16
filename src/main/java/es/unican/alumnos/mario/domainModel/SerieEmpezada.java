package es.unican.alumnos.mario.domainModel;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class SerieEmpezada extends Serie{


	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public int ultimoCapituloVisto;
	
	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public int ultimaTemporadaVista;
	
	public SerieEmpezada() {}
	
	public SerieEmpezada(Serie serie, int ultimaTemporadaVista, int ultimoCapituloVisto) {
		super(serie.getNombreSerie(), serie.getSinopsis(), serie.getCreadores(), serie.getActores(), 
				serie.getTemporadas(), serie.getCategoria());
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + idSerie;
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
		if (idSerie != other.getIdSerie())
			return false;
		return true;
	}
	
	
	
}