package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Embeddable
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Categoria {
	public float coste;

	public float getCoste() {
		return coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}
}

