package es.unican.alumnos.mario.domainModel;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class Capitulo implements Comparable<Capitulo>{

	@Id
	@GeneratedValue
	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	protected int id;

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public int numero;

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public String titulo;

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	public String descripcion;

	@JsonView({Views.DescripcionSerie.class, Views.DescripcionUsuario.class})
	@Embedded
	public Video videoCapitulo;
	
	public Capitulo() {}
	
	public Capitulo(int numero, String titulo, String descripcion, Video videoCapitulo) {
		setNumero(numero);
		setTitulo(titulo);
		setDescripcion(descripcion);
		setVideoCapitulo(videoCapitulo);
	}
	
	
	public Video getVideoCapitulo() {
		return videoCapitulo;
	}
	public void setVideoCapitulo(Video videoCapitulo) {
		this.videoCapitulo = videoCapitulo;
	}
	public void setNumero(int numero) {
		if (numero <= 0) {
			throw new RuntimeException("Número de capitulo menor que 1");
		}
		this.numero = numero;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
	
	public int getNumero() {
		return numero;
	}
	
	@Override
	public int compareTo(Capitulo u) {
	    if (this.id < 0 || u.id < 0) {
	      return 0;
	    }
	    if(this.id < u.id) {
	    	return -1;
	    }else if(this.id > u.id) {
	    	return 1;
	    }else{
	    	return 0;
	    }
	 }
	
}
