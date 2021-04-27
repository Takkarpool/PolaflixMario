package es.unican.alumnos.mario.services.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.domainModel.Usuario;
import es.unican.mario.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository ur;
	
	@GetMapping(value="/{nombre}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("nombre") String userName) {
		
		Usuario u = ur.findByNombre(userName);
		ResponseEntity<Usuario> result;
		
		if (u.isPresent()) {
			result = ResponseEntity.ok(u.get());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
}
