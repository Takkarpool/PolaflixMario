package es.unican.alumnos.mario.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.alumnos.mario.domainModel.Serie;
import es.unican.mario.repositories.SerieRepository;
import es.unican.mario.repositories.UsuarioRepository;

@Service
public class SeriesMng {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Autowired
	SerieRepository sr;
	
	@Autowired
	UsuarioRepository ur;
	
	public List<Serie> findAll() {
		return sr.findAll();
	}
	
	public List<Serie> findByNombreSerie(String nombreSerie) {
		return sr.findByNombreSerie(nombreSerie);
	}
	
}
