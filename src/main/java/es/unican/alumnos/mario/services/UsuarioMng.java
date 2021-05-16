package es.unican.alumnos.mario.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.alumnos.mario.domainModel.Capitulo;
import es.unican.alumnos.mario.domainModel.Serie;
import es.unican.alumnos.mario.domainModel.Temporada;
import es.unican.alumnos.mario.domainModel.Usuario;
import es.unican.mario.repositories.SerieRepository;
import es.unican.mario.repositories.UsuarioRepository;

@Service
public class UsuarioMng {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Autowired
	SerieRepository sr;
	
	@Autowired
	UsuarioRepository ur;
	
	@Transactional
	public Usuario anhadirSeriePendiente(String usuarioId, int serieId) throws ResourceNotFound {
		
		Usuario u = ur.findByNombre(usuarioId).orElseThrow(ResourceNotFound::new);
		Serie s = sr.findById(serieId).orElseThrow(ResourceNotFound::new);
		
		return (u.agregarSerie(s))?u:null;
	}
	
	@Transactional
	public Usuario verCapitulo(String usuarioId, int serieId, int temporadaNum, int capituloNum) throws ResourceNotFound {
		
		Usuario u = ur.findByNombre(usuarioId).orElseThrow(ResourceNotFound::new);
		Serie s = sr.findById(serieId).orElseThrow(ResourceNotFound::new);
		Temporada t = s.mostrarTemporada(temporadaNum);
		Capitulo c = t.verCapitulo(capituloNum);
		return (u.getRepertorioUsuario().verCapitulo(s,t,c))?u:null;
	}
	
}
