package org.openapitools.api;

import org.openapitools.dao.SerieDAO;
import org.openapitools.model.Episode;
import org.openapitools.model.Serie;
import org.openapitools.model.SeriesSerieIDEpisodesPostRequest;
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
public class SeriesApiController implements SeriesApi {

    private final NativeWebRequest request;
    private final SerieDAO serieDAO;

    public SeriesApiController(NativeWebRequest request, SerieDAO serieDAO) {
        this.request = request;
        this.serieDAO = serieDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Serie> seriesIdGet(@PathVariable("id") Integer id) {
        Serie serie = serieDAO.getSerieById(id);

        if (serie != null) {
            return ResponseEntity.ok(serie);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<List<Serie>> seriesGet() {
        List<Serie> series = serieDAO.getAllSeries();

        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(series);  // Devuelve la lista de actores si hay contenido
    }

    @Override 
    public ResponseEntity<Void> seriesIdDelete(@PathVariable("id") Integer id) {
        boolean  result = serieDAO.deleteSerieById(id);
        if (result) {
            return ResponseEntity.ok().build();  // 200 OK si se ha eliminado correctamente
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

    @Override
    public ResponseEntity<Serie> seriesPost(@RequestBody Serie serie) {
        boolean result = serieDAO.insertSerie(serie);
        
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body(serie);  // 201 Created y devuelve el actor en el cuerpo
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si hubo un problema en la inserción
    }

    @Override
    public ResponseEntity<Serie> seriesIdPut(@PathVariable("id") Integer id, @RequestBody Serie serie) {
        boolean result = serieDAO.updateSerieById(id, serie);
        
        if (result) {
            return ResponseEntity.ok(serie);  // 200 OK y devuelve el actor actualizado
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

    @Override
    public ResponseEntity<Episode> seriesSerieIDEpisodesEpisodeIDGet(@PathVariable("serieID") Integer serieID, 
    @PathVariable("episodeID")Integer episodeID) {
        Episode episode = serieDAO.getEpisodeById(episodeID);
        if (episode != null && episode.getSerieID() ==  serieID) {
            return ResponseEntity.ok(episode);  // Devuelve el episodio si existe
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
        }
    }

    @Override
    public ResponseEntity<List<Episode>> seriesSerieIDEpisodesGet(@PathVariable("serieID") Integer serieID) {
        List<Episode> episodes = serieDAO.getAllEpisodes(serieID);
        
        if (episodes.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(episodes);  // Devuelve la lista de actores si hay contenido
    }

    @Override
    public ResponseEntity<Void> seriesSerieIDEpisodesPost(@PathVariable("serieID") Integer serieID, @RequestBody SeriesSerieIDEpisodesPostRequest seriesSerieIDEpisodesPostRequest) {
        Episode episode = serieDAO.getEpisodeById(seriesSerieIDEpisodesPostRequest.getEpisodeID());
        if (episode != null && episode.getSerieID() ==  serieID) {
            boolean result = serieDAO.insertEpisode(episode);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 Created
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si hubo un
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
    }

    @Override
    public ResponseEntity<Void> seriesSerieIDEpisodesEpisodeIDPut(@PathVariable("serieID") Integer serieID, 
    @PathVariable("episodeID") Integer episodeID, @RequestBody Episode episode) {
        if (episode.getSerieID() == serieID) {
            boolean result =  serieDAO.updateEpisodeById(episodeID, episode);
            if (result) {
                return ResponseEntity.ok().build();  // 200 OK
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si el episod
    }

    @Override
    public ResponseEntity<Void> seriesSerieIDEpisodesEpisodeIDDelete(@PathVariable("serieID") Integer serieID,
    @PathVariable("episodeID") Integer episodeID) {
        Episode episode =  serieDAO.getEpisodeById(episodeID);
        if (episode != null &&  episode.getSerieID() == serieID) {
            boolean result =  serieDAO.deleteEpisodeById(episodeID);
            if (result) {
                return ResponseEntity.ok().build();  // 200 OK
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el episod
    }

    @Override
    public ResponseEntity<List<Serie>> seriesGenreGenreIDGet(Integer genreId) {
        List<Serie> series = serieDAO.getSerieByGenre(genreId);

        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(series);  // Devuelve la lista de actores si hay contenido
    }

    @Override
    public ResponseEntity<List<Serie>> seriesActorActorIDGet(Integer actorId) {
        List<Serie> series = serieDAO.getSerieByActorID(actorId);

        if (series.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(series);  // Devuelve la lista de actores si hay contenido
    }

    @Override
    public ResponseEntity<Serie> seriesTitleTitleGet(String title) {
        Serie serie = serieDAO.getSerieBytitle(title);

        if (serie == null) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(serie);  // Devuelve la lista de actores si hay contenido
    }
}
