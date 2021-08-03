package es.unican.alumnos.mario.domainModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class Cargo {

	@Id
	@GeneratedValue
	@JsonView({Views.DescripcionUsuario.class})
	protected int id;
	
	@JsonIgnore
	@ManyToOne
	public Usuario usuario;
	
	@JsonView({Views.DescripcionUsuario.class})
	public double cuotaFinal;

	@JsonView({Views.DescripcionUsuario.class})
	public Date fechaCargo;

	@JsonView({Views.DescripcionUsuario.class})
	@ElementCollection
	@CollectionTable(name = "listaCapitulosVistos", joinColumns = @JoinColumn(name = "cargo_id"))
	@AttributeOverrides({
        @AttributeOverride(name = "fechaVisualizado", column = @Column(name = "fechaVisualizado")),
        @AttributeOverride(name = "nombreSerie", column = @Column(name = "nombreSerie")),
        @AttributeOverride(name = "numTemporada", column = @Column(name = "numTemporada")),
        @AttributeOverride(name = "numCapitulo", column = @Column(name = "numCapitulo")),
        @AttributeOverride(name = "cargo", column = @Column(name = "cargo")),
	})
	public List<CapituloCargo>listaCapitulosVistos;
	
	public Cargo() {}
	
	public Cargo(Usuario usuario, Date fechaCargo) {
		setUsuario(usuario);
		setFechaCargo(fechaCargo);
		setListaCapitulosVistos(new ArrayList<CapituloCargo>());
	}
	
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
		boolean encontrado = false;
		for(CapituloCargo c : listaCapitulosVistos) {
			if (c.getNombreSerie().equals(serie.getNombreSerie()) 
					&& c.getNumCapitulo() == capitulo.numero 
					&& c.getNumTemporada() == temporada.getNumTemporada()) {
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			listaCapitulosVistos.add(new CapituloCargo(new Date(), serie.getNombreSerie(), temporada.getNumTemporada(),
					capitulo.getNumero(), serie.getCategoria().getCoste()));
			this.cuotaFinal += serie.getCategoria().getCoste();
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaCargo == null) ? 0 : fechaCargo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		if (fechaCargo == null) {
			if (other.getFechaCargo() != null)
				return false;
		} else {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(fechaCargo);
			cal2.setTime(other.getFechaCargo());
			if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				return false;
			}
			if (usuario == null) {
				if (other.getUsuario() != null)
					return false;
			} else if (!usuario.equals(other.usuario))
				return false;
		}
		return true;
			
	}

	
	
	
}
