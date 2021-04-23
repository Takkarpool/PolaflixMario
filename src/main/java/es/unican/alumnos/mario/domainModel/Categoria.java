package es.unican.alumnos.mario.domainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public abstract class Categoria {
	
	@Id
	@GeneratedValue
	protected int id;
	public float coste;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public float getCoste() {
		return coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}
}

