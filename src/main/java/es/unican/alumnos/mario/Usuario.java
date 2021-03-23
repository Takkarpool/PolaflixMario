package es.unican.alumnos.mario;

import java.util.List;

public class Usuario {

	public String nombre;
	public String contraseña;
	public String cuentaBancaria;
	public float cuotaFija;
	public List<Cargo> cargos;
	public List<Usuario> amigos;
	public RepertorioPersonal repertorioUsuario;
	public List<Serie> seriesNoMarcadas;
	
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
	public List<Usuario> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}
	public RepertorioPersonal getRepertorioUsuario() {
		return repertorioUsuario;
	}
	public void setRepertorioUsuario(RepertorioPersonal repertorioUsuario) {
		this.repertorioUsuario = repertorioUsuario;
	}
	public List<Serie> getSeriesNoMarcadas() {
		return seriesNoMarcadas;
	}
	public void setSeriesNoMarcadas(List<Serie> seriesNoMarcadas) {
		this.seriesNoMarcadas = seriesNoMarcadas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getCuentaBancaria() {
		return cuentaBancaria;
	}
	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	
	
}
