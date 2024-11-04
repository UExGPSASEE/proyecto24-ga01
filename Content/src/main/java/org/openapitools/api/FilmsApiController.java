package org.openapitools.api;

import org.openapitools.model.Film;
import org.openapitools.dao.FilmDAO;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;


import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T13:49:26.874841500+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeContenido.base-path:/v1/content}")
public class FilmsApiController implements FilmsApi {

    private final NativeWebRequest request;
    private final FilmDAO filmDAO;

    public FilmsApiController(NativeWebRequest request, FilmDAO filmDAO) {
        this.request = request;
        this.filmDAO = filmDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    @Override
    public ResponseEntity<Film> filmsIdGet(@PathVariable("id") Integer id) {
        Film film = filmDAO.getFilmById(id);

        if (film != null) {
            return ResponseEntity.ok(film);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    @Override
    public ResponseEntity<Film> filmsIdPut(@PathVariable("id") Integer id, @RequestBody Film film) {
        Film newFilm = filmDAO.putFilmById(id,film);

        if (newFilm != null) {
            return ResponseEntity.ok(newFilm);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    @Override
    public ResponseEntity<Void> filmsIdDelete(@PathVariable("id") Integer id) {
        boolean resultado = filmDAO.deleteFilmById(id);

        if (resultado) {
        	return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    @Override
    public ResponseEntity<List<Film>> filmsGet () {
    	List<Film> films = filmDAO.getFilms();
    	
    	if (!films.isEmpty()) {
    		return ResponseEntity.ok(films);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    }
    
    @Override
    public ResponseEntity<Film> filmsPost(@RequestBody Film film) {
        Film newFilm = filmDAO.postFilm(film);

        if (newFilm != null) {
            return ResponseEntity.ok(newFilm);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    @Override
    public ResponseEntity<Film> filmsTitleTitleGet(@PathVariable String title) {
        Film film = filmDAO.getFilmByTitle(title);

        if (film != null) {
            return ResponseEntity.ok(film);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    @Override
    public ResponseEntity<List<Film>> filmsActorActorIDGet(@PathVariable("actorID") Integer actorID) {
        List<Film> films = filmDAO.getFilmsByActorID(actorID);

        if (!films.isEmpty()) {
            return ResponseEntity.ok(films);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    @Override
    public ResponseEntity<List<Film>> filmsGenreGenreIDGet(@PathVariable("genreID") Integer genreID) {
        List<Film> films = filmDAO.getFilmsByGenreID(genreID);

        if (!films.isEmpty()) {
            return ResponseEntity.ok(films);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    
    }
    
    
    
    

}