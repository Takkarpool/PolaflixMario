package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Embeddable
public class Actor {

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public String nombre;

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public String apellido;
	
	public Actor() {}
	
	public Actor(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	
}
