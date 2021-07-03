package es.unican.alumnos.mario.domainModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.services.api.*;

@Entity
public class Usuario {

	@Id
	@JsonView({Views.DescripcionUsuario.class})
	public String nombre;
	
	@JsonView({Views.DescripcionUsuario.class})
	public String contrasena;
	
	@JsonView({Views.DescripcionUsuario.class})
	public String cuentaBancaria;
	
	@JsonView({Views.DescripcionUsuario.class})
	public float cuotaFija;
	
	@OneToMany(mappedBy="usuario",cascade = CascadeType.ALL)
	@JsonView({Views.DescripcionUsuario.class})
	public List<Cargo> cargos;
	
	@OneToOne(mappedBy="usuario", cascade = CascadeType.ALL)
	@JsonView({Views.DescripcionUsuario.class})
	public RepertorioPersonal repertorioUsuario;

	public Usuario() {}
	
	public Usuario(String nombre, String contrasena, String cuentaBancaria, float cuotaFija) {

		setNombre(nombre);
		setcontrasena(contrasena);
		setCuentaBancaria(cuentaBancaria);
		setCuotaFija(cuotaFija);
		setRepertorioUsuario(new RepertorioPersonal(this));
		setCargos(new ArrayList<Cargo>());
	}
	
	
	public float getCuotaFija() {
		return cuotaFija;
	}
	public void setCuotaFija(float cuotaFija) {
		this.cuotaFija = cuotaFija;
	}
	public List<Cargo> getCargos() {
		return cargos;
	}
	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	public RepertorioPersonal getRepertorioUsuario() {
		return repertorioUsuario;
	}
	public void setRepertorioUsuario(RepertorioPersonal repertorioUsuario) {
		this.repertorioUsuario = repertorioUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getcontrasena() {
		return contrasena;
	}
	public void setcontrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	
	public void anhadirCapituloVisto(Serie serie, Temporada temporada, Capitulo capitulo, Date fechaCargo) {
		Cargo c  = verCargo(fechaCargo);
		if (c == null) {
			cargos.add(new Cargo(this, fechaCargo));
			cargos.get(cargos.size()-1).anhadirCargo(serie, temporada, capitulo);
		}else {
			c.anhadirCargo(serie, temporada, capitulo);
		}
	}
	
	public Cargo verCargo(Date fechaCargo) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(fechaCargo);
		for (Cargo c : cargos) {
			cal2.setTime(c.getFechaCargo());
			if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean agregarSerie(Serie serie) {
		return repertorioUsuario.agregarSerie(serie);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Usuario other = (Usuario) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.getNombre()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
