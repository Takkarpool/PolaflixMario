package es.unican.alumnos.mario.domainModel;

import javax.persistence.Entity;

@Entity
public class Silver extends Categoria {

	public Silver() {
		this.coste = (float) 0.75;
	}

	public String tipo() {
		return "Silver";
	}

}
