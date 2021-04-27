package es.unican.mario.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.alumnos.mario.domainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Usuario findByNombre(String nombre);

}
