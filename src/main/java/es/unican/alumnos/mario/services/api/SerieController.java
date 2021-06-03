package es.unican.alumnos.mario.services.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import es.unican.alumnos.mario.domainModel.Serie;
import es.unican.mario.repositories.SerieRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/series")
public class SerieController {

	@Autowired
	SerieRepository sr;

	@GetMapping
	@JsonView(Views.DescripcionSerie.class)
	public ResponseEntity<List<Serie>> getSeries() {
		
		return ResponseEntity.ok(sr.findAll());
		
	}
	
	@GetMapping(value = "/{nombreSerie}")
	@JsonView(Views.DescripcionSerie.class)
	public ResponseEntity<List<Serie>> getSerie(@PathVariable String nombreSerie) {
		
		return ResponseEntity.ok(sr.findByNombreSerie(nombreSerie)); 
		
	}
	

}
