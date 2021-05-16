package es.unican.alumnos.mario.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.alumnos.mario.domainModel.Usuario;
import es.unican.alumnos.mario.services.ResourceNotFound;
import es.unican.alumnos.mario.services.UsuarioMng;
import es.unican.mario.repositories.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	UsuarioMng um;
	
	@GetMapping(value="/usuario/{nombre}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("nombre") String userName) throws ResourceNotFound {
		
		Usuario u = ur.findByNombre(userName).orElseThrow(ResourceNotFound::new);
		ResponseEntity<Usuario> result;
		
		if (u != null) {
			result = ResponseEntity.ok(u);
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
	@PutMapping(value="agregarSerie/usuario/{usuarioId}/serie/{serieId}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> anhadirSeriePendiente(@PathVariable String usuarioId, @PathVariable int serieId){
		
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
	
	
	@PutMapping(value="verSerie/{serieId}/usuario/{usuarioId}/temporada/{temporadaNum}/capitulo/{capituloNum}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> verCapitulo(@PathVariable int serieId, @PathVariable String usuarioId, 
											   @PathVariable int temporadaNum, @PathVariable int capituloNum){
		
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
