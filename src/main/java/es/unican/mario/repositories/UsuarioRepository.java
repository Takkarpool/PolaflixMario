package es.unican.mario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.alumnos.mario.domainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	List<Usuario> findByNombre(String nombre);

}
