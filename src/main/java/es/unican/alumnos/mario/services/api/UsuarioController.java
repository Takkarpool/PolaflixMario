package es.unican.alumnos.mario.services.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.domainModel.Cargo;
import es.unican.alumnos.mario.domainModel.Usuario;
import es.unican.alumnos.mario.services.ResourceNotFound;
import es.unican.alumnos.mario.services.UsuarioMng;
import es.unican.mario.repositories.UsuarioRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	UsuarioMng um;
	
	@GetMapping(value="/usuarios/{nombre}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("nombre") String userName) {
		
		Optional<Usuario> u = ur.findByNombre(userName);
		ResponseEntity<Usuario> result;
		
		if (u.isPresent()) {
			result = ResponseEntity.ok(u.get());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
	@GetMapping(value="/usuarios/{nombre}/ver-cargos")
	@JsonView(Views.DescripcionCargos.class)
	public ResponseEntity<List<Cargo>> obtenerCargos(@PathVariable("nombre") String userName) {
		
		Optional<Usuario> u = ur.findByNombre(userName);
		ResponseEntity<List<Cargo>> result;
		
		if (u.isPresent()) {
			result = ResponseEntity.ok(u.get().cargos);
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
	@PutMapping(value="usuarios/{usuarioId}/agregar-serie")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> anhadirSeriePendiente(@PathVariable String usuarioId, @RequestParam("id") int serieId){
		
		ResponseEntity<Usuario> result = null;
		
		try{
			Usuario usu = um.anhadirSeriePendiente(usuarioId, serieId);
			
			if (usu != null) {
				result = ResponseEntity.ok(usu);
			}else {
				result = new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
			}
			
		}catch (ResourceNotFound e) {
			result = ResponseEntity.notFound().build();
		}
		return result;
		
	}
	
	
	@PutMapping(value="usuarios/{usuarioId}/ver-capitulo")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> verCapitulo(@PathVariable String usuarioId, @RequestParam("serie") int serieId,
												@RequestParam("temporada") int temporadaNum, @RequestParam("capitulo") int capituloNum){
		
		ResponseEntity<Usuario> result = null;
		
		try{
			Usuario usu = um.verCapitulo(usuarioId, serieId,temporadaNum,capituloNum);
			
			if (usu != null) {
				result = ResponseEntity.ok(usu);
			}else {
				result = new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
			}
			
		}catch (ResourceNotFound e) {
			result = ResponseEntity.notFound().build();
		}
		return result;
		
	}
	
}
