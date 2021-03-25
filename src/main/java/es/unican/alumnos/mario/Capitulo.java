package es.unican.alumnos.mario;

public class Capitulo implements Comparable<Capitulo>{

	public int numero;
	public String titulo;
	public String descripcion;
	public Video videoCapitulo;
	
	
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
	    if (this.numero < 0 || u.numero < 0) {
	      return 0;
	    }
	    if(this.numero < u.numero) {
	    	return -1;
	    }else if(this.numero > u.numero) {
	    	return 1;
	    }else{
	    	return 0;
	    }
	  }
	
}
