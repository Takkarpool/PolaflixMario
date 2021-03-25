package es.unican.alumnos.mario;

import java.util.ArrayList;
import java.util.List;

public class RepertorioPersonal {
	
	public List<SerieEmpezada> seriesEmpezadas;
	public List<SerieEmpezada> seriesFinalizadas;
	public List<Serie> seriesPendientes;
	public Usuario usuario;
	
	public RepertorioPersonal(Usuario usuario) {
		
		setSeriesEmpezadas(new ArrayList<SerieEmpezada>());
		setSeriesFinalizadas(new ArrayList<SerieEmpezada>());
		setSeriesPendientes(new ArrayList<Serie>());
		this.usuario = usuario;
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
	
	public Capitulo verCapitulo(int idSerie, int numTemporada, int numCapitulo) {
		SerieEmpezada serie = null;
		for(Serie s : this.seriesPendientes) {
			if(s.getIdSerie() == idSerie){
				serie = new SerieEmpezada(s, numTemporada, numCapitulo);
				seriesEmpezadas.add(serie);
			}
		}
		
		Capitulo capituloVer = null;
		
		if (serie == null) {
			for(SerieEmpezada s : this.seriesEmpezadas) {
				if(s.getIdSerie() == idSerie){
					serie = s;
					capituloVer = serie.verCapitulo(numTemporada, numCapitulo);
				}
			}			
		}else {
			seriesPendientes.remove(serie);
			capituloVer = serie.verCapitulo(numTemporada, numCapitulo);
		}
		
		if(serie.getTemporadas().size()-1 == numTemporada && serie.getTemporadas().get(numTemporada).getCapitulos().size()-1 == numCapitulo) {
			seriesFinalizadas.add(serie);
			seriesEmpezadas.remove(serie);
		}
		
		usuario.anhadirCapituloVisto(serie, serie.getTemporadas().get(numTemporada), capituloVer);
		
		return capituloVer;
		
	}
	
	public Serie seleccionarSerie (int idSerie) {
		for (Serie s : seriesEmpezadas) {
			if (s.getIdSerie()==idSerie) {
				return s;
			}
		}
		
		for (Serie s : seriesPendientes) {
			if (s.getIdSerie()==idSerie) {
				return s;
			}
		}
		
		for (Serie s : seriesFinalizadas) {
			if (s.getIdSerie()==idSerie) {
				return s;
			}
		}
		
		return null;
	}
	
	public void agregarSerie(int idSerie) {
		for (Serie s: usuario.getSeriesNoMarcadas()) {
			if (s.getIdSerie() == idSerie) {
				seriesPendientes.add(s);
			}
		}
	}
	
	
	
}