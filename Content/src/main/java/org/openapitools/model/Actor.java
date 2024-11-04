package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Actor
 */

@JsonTypeName("actor")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-16T13:49:26.874841500+02:00[Europe/Madrid]", comments = "Generator version: 7.9.0")
public class Actor {

  private Integer id;

  private String name;

  private JsonNullable<Object> birthdayDate = JsonNullable.<Object>undefined();

  @Valid
  private List<String> photoUrls = new ArrayList<>();

  public Actor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Actor(Integer id, String name, Object birthdayDate) {
    this.id = id;
    this.name = name;
    this.birthdayDate = JsonNullable.of(birthdayDate);
  }

  public Actor id(Integer id) {
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

  public Actor name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Actor Name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Actor birthdayDate(Object birthdayDate) {
    this.birthdayDate = JsonNullable.of(birthdayDate);
    return this;
  }

  /**
   * Get birthdayDate
   * @return birthdayDate
   */
  @NotNull 
  @Schema(name = "birthday_date", example = "2003-07-16", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("birthday_date")
  public JsonNullable<Object> getBirthdayDate() {
    return birthdayDate;
  }

  public void setBirthdayDate(JsonNullable<Object> birthdayDate) {
    this.birthdayDate = birthdayDate;
  }

  public Actor photoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }

  public Actor addPhotoUrlsItem(String photoUrlsItem) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Actor actor = (Actor) o;
    return Objects.equals(this.id, actor.id) &&
        Objects.equals(this.name, actor.name) &&
        Objects.equals(this.birthdayDate, actor.birthdayDate) &&
        Objects.equals(this.photoUrls, actor.photoUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, birthdayDate, photoUrls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Actor {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    birthdayDate: ").append(toIndentedString(birthdayDate)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
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

