package es.unican.alumnos.mario.domainModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RepertorioPersonal {
	@OneToMany
	public List<SerieEmpezada> seriesEmpezadas;
	@OneToMany
	public List<SerieEmpezada> seriesFinalizadas;
	@OneToMany
	public List<Serie> seriesPendientes;
	@OneToOne(mappedBy = "usuario")
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
	
	public Capitulo verCapitulo(Serie serie, Temporada temporada, int numCapitulo) {
		
		for(SerieEmpezada s : this.seriesEmpezadas) {
			if(s.getIdSerie() == serie.getIdSerie()){
				Capitulo capituloVer = s.verCapitulo(temporada, numCapitulo);
				chequearFinalSerie(s, temporada, capituloVer);
				return capituloVer;
				
			}
		}
		
		for(SerieEmpezada s : this.seriesFinalizadas) {
			if(s.getIdSerie() == serie.getIdSerie()){
				Capitulo capituloVer = s.verCapitulo(temporada, numCapitulo);
				usuario.anhadirCapituloVisto(s, s.getTemporadas().get(temporada.getNumTemporada()), capituloVer);
				return capituloVer;
			}
		}
		
		for(Serie s : this.seriesPendientes) {
			if(s.getIdSerie() == serie.getIdSerie()){
				SerieEmpezada serieEmpezada = new SerieEmpezada(s, temporada.getNumTemporada(), numCapitulo);
				seriesEmpezadas.add(serieEmpezada);
				seriesPendientes.remove(s);
				Capitulo capituloVer = serieEmpezada.verCapitulo(temporada, numCapitulo);
				chequearFinalSerie(serieEmpezada, temporada, capituloVer);			
				return capituloVer;
			}
		}
		
		throw new RuntimeException("Ver capitulo de serie no seleccionada por el usuario");
	}
	
	private void chequearFinalSerie(SerieEmpezada serie, Temporada temporada, Capitulo capitulo) {
		if(serie.getTemporadas().size()-1 == temporada.getNumTemporada() && temporada.getCapitulos().size()-1 == capitulo.getNumero()) {
			seriesFinalizadas.add(serie);
			seriesEmpezadas.remove(serie);
		}
		usuario.anhadirCapituloVisto(serie, temporada, capitulo);
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
	
	public void agregarSerie(Serie serie) {
		seriesPendientes.add(serie);
	}
	
	public boolean capituloVisto(Serie serie, Temporada temporada, Capitulo capitulo) {
		if (serie instanceof SerieEmpezada) {
			SerieEmpezada serieEmpezadaCapitulo = (SerieEmpezada) serie;
			if (temporada.getNumTemporada() == serieEmpezadaCapitulo.getUltimaTemporadaVista() && capitulo.getNumero() == serieEmpezadaCapitulo.getUltimoCapituloVisto()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public List<Serie> verSeriesOrdenadas(char letraInicial){
		List<Serie> seriesLetra = new LinkedList<Serie>();
		for(Serie s: usuario.getSeriesNoMarcadas()) {
			if (s.getNombreSerie().charAt(0) == letraInicial) {
				seriesLetra.add(s);
			}
		}
		
		Collections.sort(seriesLetra);
		return seriesLetra;
		
	}
	
	public Serie buscarSerie(String nombreSerie){
		for(Serie s: usuario.getSeriesNoMarcadas()) {
			if (s.getNombreSerie().equals(nombreSerie)) {
				return s;
			}
		}
		
		return null;
		
	}
	
}