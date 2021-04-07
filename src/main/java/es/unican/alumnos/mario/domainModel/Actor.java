package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

@Embeddable
public class Actor {

	public String nombre;
	public String apellido;
	
	public Actor(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	
}
