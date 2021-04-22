package es.unican.alumnos.mario.domainModel;

import java.util.Collections;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Serie implements Comparable<Serie>{

	@Id
	@GeneratedValue
	protected int idSerie;
	public String nombreSerie;
	public String sinopsis;
	@ElementCollection
	@OrderColumn(name="creadores")
	public Creador[] creadores;
	@ElementCollection
	@OrderColumn(name="actores")
	public Actor[] actores;
	@OneToMany
	public List<Temporada> temporadas;
	@Embedded
	public Categoria categoria;
	
	public Serie() {}
	
	public Serie(String nombreSerie, String sinopsis, Creador[] creadores, Actor[] actores,
			List<Temporada> temporadas, Categoria categoria) {
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