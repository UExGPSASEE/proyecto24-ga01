package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SeriesSerieIDEpisodesPostRequest
 */

@JsonTypeName("_series__serieID__episodes_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T13:49:26.874841500+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class SeriesSerieIDEpisodesPostRequest {

  private Integer episodeID;

  private Integer numTemporada;

  private String titulo;

  private String photoURL;

  public SeriesSerieIDEpisodesPostRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SeriesSerieIDEpisodesPostRequest(Integer episodeID, Integer numTemporada, String titulo) {
    this.episodeID = episodeID;
    this.numTemporada = numTemporada;
    this.titulo = titulo;
  }

  public SeriesSerieIDEpisodesPostRequest episodeID(Integer episodeID) {
    this.episodeID = episodeID;
    return this;
  }

  /**
   * Get episodeID
   * @return episodeID
   */
  @NotNull 
  @Schema(name = "episodeID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("episodeID")
  public Integer getEpisodeID() {
    return episodeID;
  }

  public void setEpisodeID(Integer episodeID) {
    this.episodeID = episodeID;
  }

  public SeriesSerieIDEpisodesPostRequest numTemporada(Integer numTemporada) {
    this.numTemporada = numTemporada;
    return this;
  }

  /**
   * Get numTemporada
   * @return numTemporada
   */
  @NotNull 
  @Schema(name = "numTemporada", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("numTemporada")
  public Integer getNumTemporada() {
    return numTemporada;
  }

  public void setNumTemporada(Integer numTemporada) {
    this.numTemporada = numTemporada;
  }

  public SeriesSerieIDEpisodesPostRequest titulo(String titulo) {
    this.titulo = titulo;
    return this;
  }

  /**
   * Get titulo
   * @return titulo
   */
  @NotNull 
  @Schema(name = "titulo", example = "Título del episodio", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("titulo")
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public SeriesSerieIDEpisodesPostRequest photoURL(String photoURL) {
    this.photoURL = photoURL;
    return this;
  }

  /**
   * Get photoURL
   * @return photoURL
   */
  
  @Schema(name = "photoURL", example = "https://example.com/episodio.jpg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("photoURL")
  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeriesSerieIDEpisodesPostRequest seriesSerieIDEpisodesPostRequest = (SeriesSerieIDEpisodesPostRequest) o;
    return Objects.equals(this.episodeID, seriesSerieIDEpisodesPostRequest.episodeID) &&
        Objects.equals(this.numTemporada, seriesSerieIDEpisodesPostRequest.numTemporada) &&
        Objects.equals(this.titulo, seriesSerieIDEpisodesPostRequest.titulo) &&
        Objects.equals(this.photoURL, seriesSerieIDEpisodesPostRequest.photoURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(episodeID, numTemporada, titulo, photoURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeriesSerieIDEpisodesPostRequest {\n");
    sb.append("    episodeID: ").append(toIndentedString(episodeID)).append("\n");
    sb.append("    numTemporada: ").append(toIndentedString(numTemporada)).append("\n");
    sb.append("    titulo: ").append(toIndentedString(titulo)).append("\n");
    sb.append("    photoURL: ").append(toIndentedString(photoURL)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

