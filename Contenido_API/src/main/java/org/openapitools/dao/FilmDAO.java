package org.openapitools.dao;

import org.openapitools.model.Film;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class FilmDAO {
	private final DataSource dataSource;

    public FilmDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Film getFilmById(Integer id) {
        Film film = null;
        String sql = "SELECT * FROM film WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	
            	Integer[] actorArray = (Integer[]) resultSet.getArray("actors").getArray();
                List<Integer> arrayActors = new ArrayList<>();
                for (Integer actorId : actorArray) {
                    arrayActors.add(actorId);
                }
                
                String[] photoUrlArray = (String[]) resultSet.getArray("photo_urls").getArray();
                List<String> photoUrls = new ArrayList<>();
                for (String photoUrl : photoUrlArray) {
                    photoUrls.add(photoUrl);
                }
                
                film = new Film(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getInt("genre_id"),
                    resultSet.getInt("release_year"),
                    resultSet.getInt("duration"),
                    resultSet.getString("description"),
                    photoUrls,arrayActors
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;
    }
    
    public Film putFilmById(Integer id, Film film) {
        Film newFilm = null;
        String sql = "UPDATE film SET title=?, genre_id=?, release_year=?, duration=?, description=?, photo_urls=?, actors=? WHERE id = ?";
        
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, film.getTitle());
                preparedStatement.setInt(2, film.getGenreID());
                preparedStatement.setInt(3, film.getReleaseYear());
                preparedStatement.setInt(4, film.getDuration());
                preparedStatement.setString(5, film.getDescription());
                java.sql.Array photoUrlsArray = connection.createArrayOf("text", film.getPhotoUrls().toArray(new String[0]));
                preparedStatement.setArray(6, photoUrlsArray);
                java.sql.Array actorsArray = connection.createArrayOf("integer", film.getArrayActors().toArray(new Integer[0]));
                preparedStatement.setArray(7, actorsArray);
                preparedStatement.setInt(8, id);
                int filas = preparedStatement.executeUpdate();
                if (filas > 0) {
                	newFilm = film;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return newFilm;
    }
    
    public boolean deleteFilmById(Integer id) {
    	boolean resultado = false;
        String sql = "DELETE FROM film WHERE id=?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int filas = preparedStatement.executeUpdate();
            
            if(filas > 0) {
            	resultado = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public List<Film> getFilms() {
    	List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film";
        
        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

               // Iterar sobre el ResultSet y construir los objetos Film
               while (resultSet.next()) {
                   Film film = new Film();
                   film.setId(resultSet.getInt("id"));
                   film.setTitle(resultSet.getString("title"));
                   film.setGenreID(resultSet.getInt("genre_id"));
                   film.setReleaseYear(resultSet.getInt("release_year"));
                   film.setDuration(resultSet.getInt("duration"));
                   film.setDescription(resultSet.getString("description"));
                   
                   // Convertir arrays SQL a Listas de Java
                   java.sql.Array photoUrlsArray = resultSet.getArray("photo_urls");
                   if (photoUrlsArray != null) {
                       String[] photoUrls = (String[]) photoUrlsArray.getArray();
                       film.setPhotoUrls(Collections.unmodifiableList(Arrays.asList(photoUrls)));

                   }

                   java.sql.Array actorsArray = resultSet.getArray("actors");
                   if (actorsArray != null) {
                       Integer[] actors = (Integer[]) actorsArray.getArray();
                       film.setArrayActors(Collections.unmodifiableList(Arrays.asList(actors)));
                   }

                   // Agregar la película a la lista
                   films.add(film);
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
        
    	return films;
    }
    
    public Film postFilm(Film film) {
        String sql = "INSERT INTO film (title, genre_id, release_year, duration, description, photo_urls, actors) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros para la consulta
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setInt(2, film.getGenreID());
            preparedStatement.setInt(3, film.getReleaseYear());
            preparedStatement.setInt(4, film.getDuration());
            preparedStatement.setString(5, film.getDescription());

            // Convertir las listas a arrays SQL
            java.sql.Array photoUrlsArray = connection.createArrayOf("text", film.getPhotoUrls().toArray(new String[0]));
            preparedStatement.setArray(6, photoUrlsArray);

            java.sql.Array actorsArray = connection.createArrayOf("integer", film.getArrayActors().toArray(new Integer[0]));
            preparedStatement.setArray(7, actorsArray);

            // Ejecutar la consulta
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // Obtener el ID generado automáticamente
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        film.setId(generatedKeys.getInt(1)); // Establecer el ID en el objeto Film
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar la película", e);
        }

        return film;  // Devolver el objeto Film con el ID generado
    }
    
    public Film getFilmByTitle(String title) {
        Film film = null;
        String sql = "SELECT * FROM film WHERE title = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Establecer el título como parámetro en la consulta
            preparedStatement.setString(1, title);

            // Ejecutar la consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Crear un nuevo objeto Film a partir de los datos de la fila
                    film = new Film();
                    film.setId(resultSet.getInt("id"));
                    film.setTitle(resultSet.getString("title"));
                    film.setGenreID(resultSet.getInt("genre_id"));
                    film.setReleaseYear(resultSet.getInt("release_year"));
                    film.setDuration(resultSet.getInt("duration"));
                    film.setDescription(resultSet.getString("description"));

                    // Convertir los arrays SQL a listas de Java
                    java.sql.Array photoUrlsArray = resultSet.getArray("photo_urls");
                    if (photoUrlsArray != null) {
                        String[] photoUrls = (String[]) photoUrlsArray.getArray();
                        film.setPhotoUrls(Collections.unmodifiableList(Arrays.asList(photoUrls)));
                    }

                    java.sql.Array actorsArray = resultSet.getArray("actors");
                    if (actorsArray != null) {
                        Integer[] actors = (Integer[]) actorsArray.getArray();
                        film.setArrayActors(Collections.unmodifiableList(Arrays.asList(actors)));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;  // Retorna el objeto Film o null si no se encontró
    }
    
    public List<Film> getFilmsByActorID(Integer actorId) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, title, genre_id, release_year, duration, description, photo_urls, actors " +
                     "FROM film WHERE ? = ANY(actors)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Establecer el ID del actor como parámetro en la consulta
            preparedStatement.setInt(1, actorId);

            // Ejecutar la consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Crear un nuevo objeto Film a partir de los datos de cada fila
                    Film film = new Film();
                    film.setId(resultSet.getInt("id"));
                    film.setTitle(resultSet.getString("title"));
                    film.setGenreID(resultSet.getInt("genre_id"));
                    film.setReleaseYear(resultSet.getInt("release_year"));
                    film.setDuration(resultSet.getInt("duration"));
                    film.setDescription(resultSet.getString("description"));

                    // Convertir los arrays SQL a listas de Java
                    java.sql.Array photoUrlsArray = resultSet.getArray("photo_urls");
                    if (photoUrlsArray != null) {
                        String[] photoUrls = (String[]) photoUrlsArray.getArray();
                        film.setPhotoUrls(Collections.unmodifiableList(Arrays.asList(photoUrls)));
                    }

                    java.sql.Array actorsArray = resultSet.getArray("actors");
                    if (actorsArray != null) {
                        Integer[] actors = (Integer[]) actorsArray.getArray();
                        film.setArrayActors(Collections.unmodifiableList(Arrays.asList(actors)));
                    }

                    // Añadir el film a la lista de películas
                    films.add(film);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;  // Devuelve la lista de películas encontradas
    }
    
    public List<Film> getFilmsByGenreID(Integer genreId) {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, title, genre_id, release_year, duration, description, photo_urls, actors " +
                     "FROM film WHERE genre_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Establecer el ID del género como parámetro en la consulta
            preparedStatement.setInt(1, genreId);

            // Ejecutar la consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Crear un nuevo objeto Film a partir de los datos de cada fila
                    Film film = new Film();
                    film.setId(resultSet.getInt("id"));
                    film.setTitle(resultSet.getString("title"));
                    film.setGenreID(resultSet.getInt("genre_id"));
                    film.setReleaseYear(resultSet.getInt("release_year"));
                    film.setDuration(resultSet.getInt("duration"));
                    film.setDescription(resultSet.getString("description"));

                    // Convertir los arrays SQL a listas de Java
                    java.sql.Array photoUrlsArray = resultSet.getArray("photo_urls");
                    if (photoUrlsArray != null) {
                        String[] photoUrls = (String[]) photoUrlsArray.getArray();
                        film.setPhotoUrls(Collections.unmodifiableList(Arrays.asList(photoUrls)));
                    }

                    java.sql.Array actorsArray = resultSet.getArray("actors");
                    if (actorsArray != null) {
                        Integer[] actors = (Integer[]) actorsArray.getArray();
                        film.setArrayActors(Collections.unmodifiableList(Arrays.asList(actors)));
                    }

                    // Añadir el film a la lista de películas
                    films.add(film);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;  // Devuelve la lista de películas encontradas
    }
    
    
}
