package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;

@Embeddable
public class Silver extends Categoria {

	public Silver() {
		this.coste = (float) 0.75;
	}
	
}
