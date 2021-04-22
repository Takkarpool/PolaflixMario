package es.unican.mario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import es.unican.alumnos.mario.domainModel.Serie;

public interface SerieRepository extends JpaRepository<Serie, Integer>{
	
	List<Serie> findByNombreSerie(String nombreSerie);
	
	List<Serie> findByIdSerie(String idSerie);
	
	@Query("SELECT s FROM Serie WHERE s.nombreserie LIKE ':letraInicial%' ORDER BY s.nombreserie")
	List<Serie> findByLetraInicialYOrdenada(@Param("letraInicial") char letraInicial);
	
}
