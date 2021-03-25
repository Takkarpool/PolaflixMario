package es.unican.alumnos.mario;

public class SerieEmpezada extends Serie{

	public int ultimoCapituloVisto;
	public int ultimaTemporadaVista;
	
	public SerieEmpezada(Serie serie, int ultimaTemporadaVista, int ultimoCapituloVisto) {
		super(serie.getIdSerie(), serie.getNombreSerie(), serie.getSinopsis(), serie.getCreadores(), serie.getActores(), 
				serie.getTemporadas(), serie.getCategoria());
		setUltimaTemporadaVista(ultimaTemporadaVista);
		setUltimoCapituloVisto(ultimoCapituloVisto);
	}
	
	
	public int getUltimoCapituloVisto() {
		return ultimoCapituloVisto;
	}
	public void setUltimoCapituloVisto(int ultimoCapituloVisto) {
		this.ultimoCapituloVisto = ultimoCapituloVisto;
	}
	public int getUltimaTemporadaVista() {
		return ultimaTemporadaVista;
	}
	public void setUltimaTemporadaVista(int ultimaTemporadaVista) {
		this.ultimaTemporadaVista = ultimaTemporadaVista;
	}

	public Capitulo verCapitulo(int numTemporada, int numCapitulo) {
		return temporadas.get(numTemporada).verCapitulo(numCapitulo);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + idSerie;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SerieEmpezada other = (SerieEmpezada) obj;
		if (idSerie != other.getIdSerie())
			return false;
		return true;
	}
	
	
	
}