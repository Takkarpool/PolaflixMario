package es.unican.alumnos.mario.domainModel;

import javax.persistence.Entity;

@Entity
public class Gold extends Categoria {

	public Gold() {
		this.coste = (float) 1.5;
	}
	
	public String tipo() {
		return "Gold";
	}
}
