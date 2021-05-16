package es.unican.mario.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.alumnos.mario.domainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Optional<Usuario> findByNombre(String nombre);

}
