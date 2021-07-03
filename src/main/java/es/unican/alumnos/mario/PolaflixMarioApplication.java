package es.unican.alumnos.mario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.unican.alumnos.mario.domainModel.*;
import es.unican.mario.repositories.SerieRepository;
import es.unican.mario.repositories.UsuarioRepository;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories("es.unican.mario.repositories")
public class PolaflixMarioApplication {

	private static final Logger log = LoggerFactory.getLogger(PolaflixMarioApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PolaflixMarioApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioRepository usuarioRepositorio, SerieRepository serieRepositorio) {
		return (args) -> {

			Video v = new Video();
			Capitulo cap1_1_1 = new Capitulo(1, "AAA", "aaa", v);
			Capitulo cap1_1_2 = new Capitulo(2, "BBB", "bbb", v);
			Capitulo cap1_2_1 = new Capitulo(1, "CCC", "ccc", v);
			Capitulo cap1_2_2 = new Capitulo(2, "DDD", "ddd", v);
			Capitulo cap2_1_1 = new Capitulo(1, "EEE", "eee", v);
			Capitulo cap2_1_2 = new Capitulo(2, "FFF", "fff", v);
			Capitulo cap2_2_1 = new Capitulo(1, "GGG", "ggg", v);
			Capitulo cap2_2_2 = new Capitulo(2, "HHH", "hhh", v);
			
			List<Capitulo> l_1_1 = new ArrayList<Capitulo>();
			l_1_1.add(cap1_1_1);
			l_1_1.add(cap1_1_2);
			List<Capitulo> l_1_2 = new ArrayList<Capitulo>();
			l_1_2.add(cap1_2_1);
			l_1_2.add(cap1_2_2);
			List<Capitulo> l_2_1 = new ArrayList<Capitulo>();
			l_2_1.add(cap2_1_1);
			l_2_1.add(cap2_1_2);
			List<Capitulo> l_2_2 = new ArrayList<Capitulo>();
			l_2_2.add(cap2_2_1);
			l_2_2.add(cap2_2_2);
			
			Temporada t_1_1 = new Temporada(1, l_1_1);
			Temporada t_1_2 = new Temporada(2, l_1_2);
			Temporada t_2_1 = new Temporada(1, l_2_1);
			Temporada t_2_2 = new Temporada(2, l_2_2);
			
			List<Temporada> l_1 = new ArrayList<Temporada>();
			l_1.add(t_1_1);
			l_1.add(t_1_2);
			List<Temporada> l_2 = new ArrayList<Temporada>();
			l_2.add(t_2_1);
			l_2.add(t_2_2);
			
			Creador c1 = new Creador("AAA", "aaa");
			Creador c2 = new Creador("BBB", "bbb");
			Creador c3 = new Creador("CCC", "ccc");
			Creador c4 = new Creador("DDD", "ddd");
			
			Actor a1 = new Actor("AAA", "aaa");
			Actor a2 = new Actor("BBB", "bbb");
			Actor a3 = new Actor("CCC", "ccc");
			Actor a4 = new Actor("DDD", "ddd");
			
			List<Creador> l_c_1 = new ArrayList<>();
			List<Creador> l_c_2 = new ArrayList<>();
			l_c_1.add(c1);
			l_c_1.add(c2);
			l_c_2.add(c3);
			l_c_2.add(c4);
			
			List<Actor> l_a_1 = new ArrayList<>();
			List<Actor> l_a_2 = new ArrayList<>();
			l_a_1.add(a1);
			l_a_1.add(a2);
			l_a_2.add(a3);
			l_a_2.add(a4);
			
			
			Categoria cat1 = new Silver();
			Categoria cat2 = new Estandar();
			
			Serie s1 = new Serie("A3", "AAA", l_c_1, l_a_1, l_1, cat1);
			Serie s2 = new Serie("A2", "BBB", l_c_2, l_a_2, l_2, cat2);

			Usuario u1 = new Usuario("aaa", "AAA", "111", 0);
			Date date = new Date();
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.MONTH, calendar.get (Calendar.MONTH)-1);
	        date = calendar.getTime();
	        u1.anhadirCapituloVisto(s2, t_2_2, cap2_2_2, date);
	        u1.anhadirCapituloVisto(s2, t_2_2, cap2_2_2, new Date(System.currentTimeMillis()));
	        u1.anhadirCapituloVisto(s1, t_1_2, cap1_2_2, new Date(System.currentTimeMillis()));
			usuarioRepositorio.save(u1);
			Usuario u2 = new Usuario("bbb", "BBB", "222", 0);
			usuarioRepositorio.save(u2);
			
			serieRepositorio.save(s1);
			serieRepositorio.save(s2);
			
			for (Usuario customer : usuarioRepositorio.findAll()) {
		        log.info(customer.toString());
		    }
			
			for (Serie serie : serieRepositorio.findAll()) {
		        log.info(serie.toString());
		    }
			
			for (Serie serie : serieRepositorio.findByLetraInicialYOrdenada('A')) {
		        log.info(serie.toString());
		    }

		    
		    
		};
	}
}