package es.unican.alumnos.mario.domainModel;

import java.util.Date;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;;

@Embeddable
public class CapituloCargo {


	@JsonView({Views.DescripcionUsuario.class})
	public Date fechaVisualizado;

	@JsonView({Views.DescripcionUsuario.class})
	public String nombreSerie;

	@JsonView({Views.DescripcionUsuario.class})
	public int numTemporada;

	@JsonView({Views.DescripcionUsuario.class})
	public int numCapitulo;

	@JsonView({Views.DescripcionUsuario.class})
	public float cargo;

	public CapituloCargo() {}

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
