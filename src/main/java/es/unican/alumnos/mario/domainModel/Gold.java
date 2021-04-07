package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

@Embeddable
public class Gold extends Categoria {

	public Gold() {
		this.coste = (float) 1.5;
	}
	
}
