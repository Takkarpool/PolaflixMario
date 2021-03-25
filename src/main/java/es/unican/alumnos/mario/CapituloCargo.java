package es.unican.alumnos.mario;

import java.util.Date;

public class CapituloCargo {

	public Date fechaVisualizado;
	public String nombreSerie;
	public int numTemporada;
	public int numCapitulo;
	public float cargo;


	public CapituloCargo(Date fechaVisualizado, String nombreSerie, int numTemporada, int numCapitulo, float cargo) {
		setFechaVisualizado(fechaVisualizado);
		setNombreSerie(nombreSerie);
		setNumTemporada(numTemporada);
		setNumCapitulo(numCapitulo);
		setCargo(cargo);
	}

	
	public Date getFechaVisualizado() {
		return fechaVisualizado;
	}
	public void setFechaVisualizado(Date fechaVisualizado) {
		this.fechaVisualizado = fechaVisualizado;
	}
	public String getNombreSerie() {
		return nombreSerie;
	}
	public void setNombreSerie(String nombreSerie) {
		this.nombreSerie = nombreSerie;
	}
	public int getNumTemporada() {
		return numTemporada;
	}
	public void setNumTemporada(int numTemporada) {
		this.numTemporada = numTemporada;
	}
	public int getNumCapitulo() {
		return numCapitulo;
	}
	public void setNumCapitulo(int numCapitulo) {
		this.numCapitulo = numCapitulo;
	}
	public float getCargo() {
		return cargo;
	}
	public void setCargo(float cargo) {
		this.cargo = cargo;
	}

	
	
}
