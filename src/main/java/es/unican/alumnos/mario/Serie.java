package es.unican.alumnos.mario;

import java.util.List;

public class Serie {


	protected int idSerie;
	public String nombreSerie;
	public String sinopsis;
	public String[] creadores;
	public String[] actores;
	public List<Temporada> temporadas;
	public Categoria categoria;
	
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
	public String[] getCreadores() {
		return creadores;
	}
	public void setCreadores(String[] creadores) {
		this.creadores = creadores;
	}
	public String[] getActores() {
		return actores;
	}
	public void setActores(String[] actores) {
		this.actores = actores;
	}
	
}