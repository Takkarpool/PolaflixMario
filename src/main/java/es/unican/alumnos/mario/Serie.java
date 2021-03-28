package es.unican.alumnos.mario;

import java.util.Collections;
import java.util.List;

public class Serie implements Comparable<Serie>{


	protected int idSerie;
	public String nombreSerie;
	public String sinopsis;
	public Creador[] creadores;
	public Actor[] actores;
	public List<Temporada> temporadas;
	public Categoria categoria;
	
	
	public Serie(int idSerie, String nombreSerie, String sinopsis, Creador[] creadores, Actor[] actores,
			List<Temporada> temporadas, Categoria categoria) {
		setIdSerie(idSerie);
		setNombreSerie(nombreSerie);
		setSinopsis(sinopsis);
		setCreadores(creadores);
		setActores(actores);
		setTemporadas(temporadas);
		setCategoria(categoria);
	}
	public List<Temporada> getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(List<Temporada> temporadas) {
		Collections.sort(temporadas);
		this.temporadas = temporadas;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getIdSerie() {
		return idSerie;
	}
	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}
	public String getNombreSerie() {
		return nombreSerie;
	}
	public void setNombreSerie(String nombreSerie) {
		this.nombreSerie = nombreSerie;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	
	public Creador[] getCreadores() {
		return creadores;
	}
	public void setCreadores(Creador[] creadores) {
		this.creadores = creadores;
	}
	public Actor[] getActores() {
		return actores;
	}
	public void setActores(Actor[] actores) {
		this.actores = actores;
	}
	public Temporada mostrarTemporada(int numTemporada) {
		return this.temporadas.get(numTemporada - 1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSerie;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		if (idSerie != other.getIdSerie())
			return false;
		return true;
	}
	@Override
	public int compareTo(Serie o) {
		return nombreSerie.compareTo(o.getNombreSerie());
	}
	
}