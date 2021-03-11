package es.unican.alumnos.mario;

import java.util.List;

public class Usuario {

	private String nombre;
	private String contraseña;
	private String cuentaBancaria;
	private TipoUsuario tipoUsuario;
	private List<Cargo> cargosMensuales;
	
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
