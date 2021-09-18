package es.unican.alumnos.mario.domainModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class RepertorioPersonal {

	@Id
	@GeneratedValue
	@JsonView({Views.DescripcionUsuario.class})
	protected int id;
	
	@JsonView({Views.DescripcionUsuario.class})
	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	public Map<Integer, SerieEmpezada> seriesEmpezadas;

	@JsonView({Views.DescripcionUsuario.class})
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public Map<Integer,SerieEmpezada> seriesFinalizadas;

	@JsonView({Views.DescripcionUsuario.class})
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public Map<Integer,Serie> seriesPendientes;

	@JsonIgnore
	@OneToOne
	public Usuario usuario;

	public RepertorioPersonal() {}

	public RepertorioPersonal(Usuario usuario) {

		setSeriesEmpezadas(new HashMap<Integer,SerieEmpezada>());
		setSeriesFinalizadas(new HashMap<Integer,SerieEmpezada>());
		setSeriesPendientes(new HashMap<Integer,Serie>());
		setUsuario(usuario);

	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@JsonGetter("seriesEmpezadas")
	public List<SerieEmpezada> getSeriesEmpezadasAsList() {
		return seriesEmpezadas.values().stream().collect(Collectors.<SerieEmpezada>toList());
	}
	
	@JsonGetter("seriesFinalizadas")
	public List<SerieEmpezada> getSeriesFinalizadasAsList() {
		return seriesFinalizadas.values().stream().collect(Collectors.<SerieEmpezada>toList());
	}
	
	@JsonGetter("seriesPendientes")
	public List<Serie> getSeriesPendientesAsList() {
		return seriesPendientes.values().stream().collect(Collectors.<Serie>toList());
	}
	
	@JsonSetter("seriesEmpezadas")
	public void setSeriesEmpezadasAsList(List<SerieEmpezada> series) {
		Map<Integer, SerieEmpezada> deserializedSerie = series.stream().collect(Collectors.toMap(SerieEmpezada::getIdSerie, SerieEmpezada -> SerieEmpezada));
        this.seriesEmpezadas = deserializedSerie;
	}
	
	@JsonSetter("seriesFinalizadas")
	public void setSeriesFinalizadasAsList(List<SerieEmpezada> series) {
		Map<Integer, SerieEmpezada> deserializedSerie = series.stream().collect(Collectors.toMap(SerieEmpezada::getIdSerie, SerieEmpezada -> SerieEmpezada));
        this.seriesFinalizadas = deserializedSerie;
	}
	
	@JsonSetter("seriesPendientes")
	public void setSeriesPendientesAsList(List<Serie> series) {
		Map<Integer, Serie> deserializedSerie = series.stream().collect(Collectors.toMap(Serie::getIdSerie, Serie -> Serie));
        this.seriesPendientes = deserializedSerie;
	}
	
	public Map<Integer, SerieEmpezada> getSeriesEmpezadas() {
		return seriesEmpezadas;
	}

	public void setSeriesEmpezadas(HashMap<Integer, SerieEmpezada> seriesEmpezadas) {
		this.seriesEmpezadas = seriesEmpezadas;
	}

	public Map<Integer, SerieEmpezada> getSeriesFinalizadas() {
		return seriesFinalizadas;
	}

	public void setSeriesFinalizadas(HashMap<Integer, SerieEmpezada> seriesFinalizadas) {
		this.seriesFinalizadas = seriesFinalizadas;
	}

	public Map<Integer, Serie> getSeriesPendientes() {
		return seriesPendientes;
	}

	public void setSeriesPendientes(HashMap<Integer, Serie> seriesPendientes) {
		this.seriesPendientes = seriesPendientes;
	}

	public boolean verCapitulo(Serie serie, Temporada temporada, Capitulo capitulo) {


		SerieEmpezada s = seriesEmpezadas.get(serie.getIdSerie());
		if (s!=null) {
			chequearFinalSerie(s, temporada, capitulo);
			return true;
		}

		s = seriesFinalizadas.get(serie.getIdSerie());
		if (s!=null) {
			chequearFinalSerie(s, temporada, capitulo);
			return true;
		}

		Serie se = seriesPendientes.get(serie.getIdSerie());
		if (se!=null) {
			SerieEmpezada serieEmpezada = new SerieEmpezada(se, temporada.getNumTemporada(), capitulo.getNumero());
			seriesEmpezadas.put(serieEmpezada.getSerie().getIdSerie(),serieEmpezada);
			seriesPendientes.remove(se.getIdSerie());
			chequearFinalSerie(serieEmpezada, temporada, capitulo);			
			return true;
		}


		throw new RuntimeException("Ver capitulo de serie no seleccionada por el usuario");
	}

	private void chequearFinalSerie(SerieEmpezada serie, Temporada temporada, Capitulo capitulo) {
		if(serie.getSerie().getTemporadas().size() == temporada.getNumTemporada() && temporada.getCapitulos().size() == capitulo.getNumero()) {
			seriesFinalizadas.put(serie.getSerie().getIdSerie(),serie);
			seriesEmpezadas.remove(serie.getSerie().getIdSerie());
		}
		if(serie.getUltimaTemporadaVista() < temporada.getNumTemporada() || 
				( serie.getUltimaTemporadaVista() == temporada.getNumTemporada() && 
				serie.getUltimoCapituloVisto() < capitulo.getNumero())) {
			serie.setUltimaTemporadaVista(temporada.getNumTemporada());
			serie.setUltimoCapituloVisto(capitulo.getNumero());
		}
	}


	public boolean agregarSerie(Serie serie) {

		SerieEmpezada s = seriesEmpezadas.get(serie.getIdSerie());
		if (s!=null) {
			return false;
		}

		s = seriesFinalizadas.get(serie.getIdSerie());
		if (s!=null) {
			return false;
		}

		Serie se = seriesPendientes.get(serie.getIdSerie());
		if (se!=null) {
			return false;
		}

		seriesPendientes.put(serie.getIdSerie(),serie);
		return true;
	}

}