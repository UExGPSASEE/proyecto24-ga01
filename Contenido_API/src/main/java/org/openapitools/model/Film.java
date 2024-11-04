package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Film
 */

@JsonTypeName("film")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T13:49:26.874841500+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Film {

  private Integer id;

  private String title;

  private Integer genreID;

  private Integer releaseYear;

  private Integer duration;

  private String description;

  @Valid
  private List<String> photoUrls = new ArrayList<>();

  @Valid
  private List<Integer> arrayActors = new ArrayList<>();

  public Film() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Film(Integer id, String title, Integer genreID, Integer releaseYear, Integer duration) {
    this.id = id;
    this.title = title;
    this.genreID = genreID;
    this.releaseYear = releaseYear;
    this.duration = duration;
  }

  public Film(Integer id, String title, Integer genreID, Integer releaseYear, Integer duration, String description, List<String> photoUrls, List<Integer> arrayActors) {
    this.id = id;
    this.title = title;
    this.genreID = genreID;
    this.releaseYear = releaseYear;
    this.duration = duration;
    this.description = description;
    this.photoUrls = photoUrls != null ? new ArrayList<>(photoUrls) : new ArrayList<>();
    this.arrayActors = arrayActors != null ? new ArrayList<>(arrayActors) : new ArrayList<>();
  }

  public Film id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull 
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Film title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "Film Title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Film genreID(Integer genreID) {
    this.genreID = genreID;
    return this;
  }

  /**
   * Get genreID
   * @return genreID
   */
  @NotNull 
  @Schema(name = "genreID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("genreID")
  public Integer getGenreID() {
    return genreID;
  }

  public void setGenreID(Integer genreID) {
    this.genreID = genreID;
  }

  public Film releaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
    return this;
  }

  /**
   * Get releaseYear
   * @return releaseYear
   */
  @NotNull 
  @Schema(name = "release_year", example = "2023", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("release_year")
  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Film duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Get duration
   * @return duration
   */
  @NotNull 
  @Schema(name = "duration", example = "120", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("duration")
  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Film description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", example = "Film Sinopsis", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Film photoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  public Film addPhotoUrlsItem(String photoUrlsItem) {
    if (this.photoUrls == null) {
      this.photoUrls = new ArrayList<>();
    }
    this.photoUrls.add(photoUrlsItem);
    return this;
  }

  /**
   * Get photoUrls
   * @return photoUrls
   */
  
  @Schema(name = "photoUrls", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("photoUrls")
  public List<String> getPhotoUrls() {
    return photoUrls;
  }

  public void setPhotoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
  }

  public Film arrayActors(List<Integer> arrayActors) {
    this.arrayActors = arrayActors;
    return this;
  }

  public Film addArrayActorsItem(Integer arrayActorsItem) {
    if (this.arrayActors == null) {
      this.arrayActors = new ArrayList<>();
    }
    this.arrayActors.add(arrayActorsItem);
    return this;
  }

  /**
   * Get arrayActors
   * @return arrayActors
   */
  
  @Schema(name = "ArrayActors", example = "[101,102,103]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ArrayActors")
  public List<Integer> getArrayActors() {
    return arrayActors;
  }

  public void setArrayActors(List<Integer> arrayActors) {
    this.arrayActors = arrayActors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Film film = (Film) o;
    return Objects.equals(this.id, film.id) &&
        Objects.equals(this.title, film.title) &&
        Objects.equals(this.genreID, film.genreID) &&
        Objects.equals(this.releaseYear, film.releaseYear) &&
        Objects.equals(this.duration, film.duration) &&
        Objects.equals(this.description, film.description) &&
        Objects.equals(this.photoUrls, film.photoUrls) &&
        Objects.equals(this.arrayActors, film.arrayActors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, genreID, releaseYear, duration, description, photoUrls, arrayActors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Film {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    genreID: ").append(toIndentedString(genreID)).append("\n");
    sb.append("    releaseYear: ").append(toIndentedString(releaseYear)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    arrayActors: ").append(toIndentedString(arrayActors)).append("\n");
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

