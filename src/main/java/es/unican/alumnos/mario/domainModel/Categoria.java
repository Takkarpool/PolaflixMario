package es.unican.alumnos.mario.domainModel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@AttributeOverrides({
	  @AttributeOverride( name = "coste", column = @Column(name = "coste_por_categoria")),})
public abstract class Categoria {
	public float coste;

	public float getCoste() {
		return coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}
}

