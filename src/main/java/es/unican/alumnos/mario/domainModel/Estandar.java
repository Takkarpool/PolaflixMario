package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

@Embeddable
public class Estandar extends Categoria {

	public Estandar() {
		this.coste = (float) 0.5;
	}
	
}
