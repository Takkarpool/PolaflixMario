package es.unican.alumnos.mario.domainModel;

import javax.persistence.Entity;

@Entity
public class Estandar extends Categoria {

	public Estandar() {
		this.coste = (float) 0.5;
	}
	
	public String tipo() {
		return "Estandar";
	}
	
}
