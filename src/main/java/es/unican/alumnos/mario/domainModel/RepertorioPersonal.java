package es.unican.alumnos.mario.domainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class RepertorioPersonal {
	
	@Id
	@GeneratedValue
	@JsonView({Views.DescripcionUsuario.class})
	protected int id;
	
	@JsonView({Views.DescripcionUsuario.class})
	@ManyToMany(cascade = CascadeType.ALL)
	public List<SerieEmpezada> seriesEmpezadas;

	@JsonView({Views.DescripcionUsuario.class})
	@ManyToMany(cascade = CascadeType.ALL)
	public List<SerieEmpezada> seriesFinalizadas;

	@JsonView({Views.DescripcionUsuario.class})
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Serie> seriesPendientes;

	@JsonIgnore
	@OneToOne
	public Usuario usuario;
	
	public RepertorioPersonal() {}
	
	public RepertorioPersonal(Usuario usuario) {
		
		setSeriesEmpezadas(new ArrayList<SerieEmpezada>());
		setSeriesFinalizadas(new ArrayList<SerieEmpezada>());
		setSeriesPendientes(new ArrayList<Serie>());
		setUsuario(usuario);
		
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<SerieEmpezada> getSeriesEmpezadas() {
		return seriesEmpezadas;
	}
	public void setSeriesEmpezadas(List<SerieEmpezada> seriesEmpezadas) {
		this.seriesEmpezadas = seriesEmpezadas;
	}
	public List<SerieEmpezada> getSeriesFinalizadas() {
		return seriesFinalizadas;
	}
	public void setSeriesFinalizadas(List<SerieEmpezada> seriesFinalizadas) {
		this.seriesFinalizadas = seriesFinalizadas;
	}
	public List<Serie> getSeriesPendientes() {
		return seriesPendientes;
	}
	public void setSeriesPendientes(List<Serie> seriesPendientes) {
		this.seriesPendientes = seriesPendientes;
	}
	
	public boolean verCapitulo(Serie serie, Temporada temporada, Capitulo capitulo) {
		
		for(SerieEmpezada s : this.seriesEmpezadas) {
			if(s.getSerie().getIdSerie() == serie.getIdSerie()){
				chequearFinalSerie(s, temporada, capitulo);
				return true;
			}
		}
		
		for(SerieEmpezada s : this.seriesFinalizadas) {
			if(s.getSerie().getIdSerie() == serie.getIdSerie()){
				usuario.anhadirCapituloVisto(serie, temporada, capitulo, new Date(System.currentTimeMillis()));
				return true;
			}
		}
		
		for(Serie s : this.seriesPendientes) {
			if(s.getIdSerie() == serie.getIdSerie()){
				SerieEmpezada serieEmpezada = new SerieEmpezada(s, temporada.getNumTemporada(), capitulo.getNumero());
				seriesEmpezadas.add(serieEmpezada);
				seriesPendientes.remove(s);
				chequearFinalSerie(serieEmpezada, temporada, capitulo);			
				return true;
			}
		}
		
		throw new RuntimeException("Ver capitulo de serie no seleccionada por el usuario");
	}
	
	private void chequearFinalSerie(SerieEmpezada serie, Temporada temporada, Capitulo capitulo) {
		if(serie.getSerie().getTemporadas().size() == temporada.getNumTemporada() && temporada.getCapitulos().size() == capitulo.getNumero()) {
			seriesFinalizadas.add(serie);
			seriesEmpezadas.remove(serie);
		}
		if(serie.getUltimaTemporadaVista() < temporada.getNumTemporada() || 
				( serie.getUltimaTemporadaVista() == temporada.getNumTemporada() && 
				serie.getUltimoCapituloVisto() < capitulo.getNumero())) {
			serie.setUltimaTemporadaVista(temporada.getNumTemporada());
			serie.setUltimoCapituloVisto(capitulo.getNumero());
		}
		usuario.anhadirCapituloVisto(serie.getSerie(), temporada, capitulo, new Date(System.currentTimeMillis()));
	}
	
	
	public boolean agregarSerie(Serie serie) {
		
		for(SerieEmpezada s : this.seriesEmpezadas) {
			if(s.getSerie().getIdSerie() == serie.getIdSerie()){
				return false;
			}
		}
		
		for(SerieEmpezada s : this.seriesFinalizadas) {
			if(s.getSerie().getIdSerie() == serie.getIdSerie()){
				return false;
			}
		}
		
		for(Serie s : this.seriesPendientes) {
			if(s.getIdSerie() == serie.getIdSerie()){
				return false;
			}
		}
		
		seriesPendientes.add(serie);
		return true;
	}
	
}