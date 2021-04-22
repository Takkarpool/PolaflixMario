package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

@Embeddable
public class Creador {

	
	public String nombre;
	public String apellido;
	
	public Creador() {}
	
	public Creador(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}
}
