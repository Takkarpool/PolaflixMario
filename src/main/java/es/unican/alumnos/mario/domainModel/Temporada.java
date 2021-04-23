package es.unican.alumnos.mario.domainModel;


import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Temporada  implements Comparable<Temporada>{

	@Id
	@GeneratedValue
	protected int id;
	public int numTemporada;
	@OneToMany(cascade=CascadeType.ALL)
	public List<Capitulo> capitulos;
	
	public Temporada() {}
	
	public Temporada(int numTemporada, List<Capitulo> capitulos) {
		setNumTemporada(numTemporada);
		setCapitulos(capitulos);
	}
	public int getNumTemporada() {
		return numTemporada;
	}
	public void setNumTemporada(int numTemporada) {
		if (numTemporada <= 0) {
			throw new RuntimeException("Número de temporada menor que 1");
		}
		this.numTemporada = numTemporada;
	}
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(List<Capitulo> capitulos) {
		Collections.sort(capitulos);
		this.capitulos = capitulos;
	}
	public Capitulo verCapitulo(int numCapitulo) {
		return capitulos.get(numCapitulo - 1);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(Temporada u) {
	    if (this.id < 0 || u.getId() < 0) {
	      return 0;
	    }
	    if(this.id < u.getId()) {
	    	return -1;
	    }else if(this.id > u.getId()) {
	    	return 1;
	    }else{
	    	return 0;
	    }
	  }
	
}
