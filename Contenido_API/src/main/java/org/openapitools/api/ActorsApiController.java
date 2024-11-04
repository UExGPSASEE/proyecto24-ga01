package org.openapitools.api;

import org.openapitools.dao.ActorDAO;
import org.openapitools.model.Actor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T13:49:26.874841500+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeContenido.base-path:/v1/content}")
public class ActorsApiController implements ActorsApi {

    private final NativeWebRequest request;
    private final ActorDAO actorDAO;

    public ActorsApiController(NativeWebRequest request, ActorDAO actorDAO) {
        this.request = request;
        this.actorDAO = actorDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Actor> actorsIdGet(@PathVariable("id") Integer id) {
        Actor actor = actorDAO.getActorById(id);

        if (actor != null) {
            return ResponseEntity.ok(actor);  // Devuelve la película si existe
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe
        }
    }

    @Override
    public ResponseEntity<List<Actor>> actorsGet() {
        List<Actor> actors = actorDAO.getAllActors();

        if (actors.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si la lista está vacía
        }
        return ResponseEntity.ok(actors);  // Devuelve la lista de actores si hay contenido
    }

    @Override 
    public ResponseEntity<Void> actorsIdDelete(@PathVariable("id") Integer id) {
        boolean  result = actorDAO.deleteActorById(id);
        if (result) {
            return ResponseEntity.ok().build();  // 200 OK si se ha eliminado correctamente
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }

    @Override
    public ResponseEntity<Actor> actorsPost(@RequestBody Actor actor) {
        boolean result = actorDAO.insertActor(actor);
        
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED).body(actor);  // 201 Created y devuelve el actor en el cuerpo
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si hubo un problema en la inserción
    }

    @Override
    public ResponseEntity<Actor> actorsIdPut(@PathVariable("id") Integer id, @RequestBody Actor actor) {
        boolean result = actorDAO.updateActorById(id, actor);
        
        if (result) {
            return ResponseEntity.ok(actor);  // 200 OK y devuelve el actor actualizado
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 si no existe el actor
    }



}
