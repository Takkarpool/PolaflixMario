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
	
	public Capitulo verCapitulo(int idSerie, int numTemporada, int numCapitulo) {
		
		for(SerieEmpezada s : this.seriesEmpezadas) {
			if(s.getIdSerie() == idSerie){
				Capitulo capituloVer = s.verCapitulo(numTemporada, numCapitulo);
				return chequearFinalSerie(numTemporada, numCapitulo, s, capituloVer);
			}
		}
		
		for(SerieEmpezada s : this.seriesFinalizadas) {
			if(s.getIdSerie() == idSerie){
				Capitulo capituloVer = s.verCapitulo(numTemporada, numCapitulo);
				usuario.anhadirCapituloVisto(s, s.getTemporadas().get(numTemporada), capituloVer);
				return capituloVer;
			}
		}
		
		for(Serie s : this.seriesPendientes) {
			if(s.getIdSerie() == idSerie){
				SerieEmpezada serie = new SerieEmpezada(s, numTemporada, numCapitulo);
				seriesEmpezadas.add(serie);
				seriesPendientes.remove(serie);
				Capitulo capituloVer = serie.verCapitulo(numTemporada, numCapitulo);
				return chequearFinalSerie(numTemporada, numCapitulo, serie, capituloVer);			
			}
		}
		
		throw new RuntimeException("Ver capitulo de serie no seleccionada por el usuario");
	}
	
	private Capitulo chequearFinalSerie(int numTemporada, int numCapitulo, SerieEmpezada serie, Capitulo capituloVer) {
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
	
	public boolean capituloVisto(int idSerie, int numTemporada, int numCapitulo) {
		Serie serieCapitulo = seleccionarSerie(idSerie);
		if (serieCapitulo instanceof SerieEmpezada) {
			SerieEmpezada serieEmpezadaCapitulo = (SerieEmpezada) serieCapitulo;
			if (numTemporada == serieEmpezadaCapitulo.getUltimaTemporadaVista() && numCapitulo == serieEmpezadaCapitulo.getUltimoCapituloVisto()) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
}