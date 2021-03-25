package es.unican.alumnos.mario;

import java.util.Date;
import java.util.List;

public class Cargo {

	public Usuario usuario;
	public double cuotaFinal;
	public Date fechaCargo;
	public List<CapituloCargo>listaCapitulosVistos;
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public double getCuotaFinal() {
		return cuotaFinal;
	}
	public void setCuotaFinal(double cuotaFinal) {
		this.cuotaFinal = cuotaFinal;
	}
	public Date getFechaCargo() {
		return fechaCargo;
	}
	public void setFechaCargo(Date fechaCargo) {
		this.fechaCargo = fechaCargo;
	}
	public List<CapituloCargo> getListaCapitulosVistos() {
		return listaCapitulosVistos;
	}
	public void setListaCapitulosVistos(List<CapituloCargo> listaCapitulosVistos) {
		this.listaCapitulosVistos = listaCapitulosVistos;
	}	
	
	
	public void anhadirCargo(Serie serie, Temporada temporada, Capitulo capitulo) {
		listaCapitulosVistos.add(new CapituloCargo(new Date(), serie.getNombreSerie(), temporada.getNumTemporada(),
				capitulo.getNumero(), serie.getCategoria().getCoste()));
	}
	
}
