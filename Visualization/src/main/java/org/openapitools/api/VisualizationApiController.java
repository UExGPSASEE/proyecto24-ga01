package org.openapitools.api;

import org.openapitools.dao.VisualizationDAO;
import org.openapitools.model.OpcionesVisualizacion;
import org.openapitools.model.Visualizacion;
import org.openapitools.model.VisualizationUserIDFilmFilmIDDownloadPost200Response;
import org.openapitools.model.VisualizationUserIDFilmFilmIDLanguagePostRequest;
import org.openapitools.model.VisualizationUserIDFilmFilmIDSubtitlesPostRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T22:57:10.884527400+01:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.aPIDeVisualizacionesYRecomendaciones.base-path:/views}")
public class VisualizationApiController implements VisualizationApi {

    private final NativeWebRequest request;
    private final VisualizationDAO visualizationDAO;

    @Autowired
    public VisualizationApiController(NativeWebRequest request, VisualizationDAO visualizationDAO) {
        this.request = request;
        this.visualizationDAO = visualizationDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    
    
    @Override
    public ResponseEntity<OpcionesVisualizacion> visualizationUserIDFilmFilmIDPlayPost (@PathVariable("userID") Integer userID,@PathVariable("filmID") Integer filmID) { 
    	OpcionesVisualizacion opciones = visualizationDAO.playFilm(userID,filmID);
    	
    	if (opciones != null) {
    		return ResponseEntity.ok(opciones);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}

    }
    
    @Override
    public ResponseEntity<OpcionesVisualizacion> visualizationUserIDSerieSerieIDPlayPost (@PathVariable("userID") Integer userID,@PathVariable("serieID") Integer serieID) { 
    	OpcionesVisualizacion opciones = visualizationDAO.playSerie(userID,serieID);
    	
    	if (opciones != null) {
    		return ResponseEntity.ok(opciones);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}

    }

    @Override
    public ResponseEntity<OpcionesVisualizacion> visualizationUserIDFilmFilmIDPausePost (@PathVariable("userID") Integer userID,@PathVariable("filmID") Integer filmID) {
    	String pause = "Pausado"; 
    	OpcionesVisualizacion opciones = visualizationDAO.changePauseFilm(userID,filmID,pause);
    	
    	if (opciones != null) {
    		return ResponseEntity.ok(opciones);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}

    }

    @Override
    public ResponseEntity<OpcionesVisualizacion> visualizationUserIDSerieSerieIDPausePost (@PathVariable("userID") Integer userID,@PathVariable("serieID") Integer serieID) {
    	String pause = "Pausado"; 
    	OpcionesVisualizacion opciones = visualizationDAO.changePauseSerie(userID,serieID,pause);
    	
    	if (opciones != null) {
    		return ResponseEntity.ok(opciones);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}

    }

}
