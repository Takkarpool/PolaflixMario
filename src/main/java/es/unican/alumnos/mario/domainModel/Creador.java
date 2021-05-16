package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Embeddable
public class Creador {

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public String nombre;
	
	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public String apellido;
	
	public Creador() {}
	
	public Creador(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}
}
