package org.openapitools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.openapitools.model.Episode;
import org.openapitools.model.Serie;
import org.springframework.stereotype.Repository;

@Repository
public class SerieDAO {
    
    private final DataSource dataSource;


    public SerieDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Serie getSerieById(Integer id) {
        Serie serie = null;
        String  query = "SELECT * FROM serie WHERE id = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("seasons"),
                    rs.getInt("genre_id")
                );
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return serie;
    }

    public  List<Serie> getAllSeries() {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM serie";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("seasons"),
                    rs.getInt("genre_id")
                );
                series.add(serie); 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    public boolean deleteSerieById(Integer id) {
        String sql = "DELETE FROM serie WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.clearParameters();
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertSerie(Serie serie) {
        String sql = "INSERT INTO serie (id, title, seasons, genre_id) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, serie.getId());  
            preparedStatement.setString(2, serie.getTitle()); 
            preparedStatement.setInt(3, serie.getSeasons());
            preparedStatement.setInt(4, serie.getGenreID());
    
            int rowsAffected = preparedStatement.executeUpdate();  
    
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  
        }
    }

    public boolean updateSerieById(Integer id, Serie serie) {
        String sql = "UPDATE serie SET title = ?, seasons = ?,  genre_id = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, serie.getTitle());
            preparedStatement.setInt(2, serie.getSeasons());
            preparedStatement.setInt(3, serie.getGenreID());
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Episode> getAllEpisodes(Integer serieID) {
        List<Episode> episodes = new ArrayList<>();
        String sql = "SELECT *  FROM episode WHERE serieid = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.clearParameters();
                preparedStatement.setInt(1, serieID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Episode episode = new Episode(
                        resultSet.getInt("episodeid"),
                        resultSet.getInt("serieid"), 
                        resultSet.getInt("numtemporada"),
                        resultSet.getString("titulo"));

                    episodes.add(episode);
                }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return episodes;
    }

    public Episode getEpisodeById(Integer id) {
        Episode episode = null;
        String  query = "SELECT * FROM episode WHERE episodeid = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                episode = new Episode(
                    rs.getInt("episodeid"),
                    rs.getInt("serieid"),
                    rs.getInt("numtemporada"),
                    rs.getString("titulo")
                );
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return episode;
    }

    public boolean insertEpisode(Episode episode) {
        String sql = "INSERT INTO episode (episodeid, serieid, numtemporada, titulo) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, episode.getEpisodeID());  
            preparedStatement.setInt(2, episode.getSerieID()); 
            preparedStatement.setInt(3, episode.getNumTemporada());
            preparedStatement.setString(4, episode.getTitulo());
    
            int rowsAffected = preparedStatement.executeUpdate();  
    
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  
        }
    }

    public boolean updateEpisodeById(Integer id, Episode episode) {
        String sql = "UPDATE episode SET episodeid = ?, serieid = ?,  numtemporada = ?, titulo = ? WHERE episodeid = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, episode.getEpisodeID());
            preparedStatement.setInt(2, episode.getSerieID());
            preparedStatement.setInt(3, episode.getNumTemporada());
            preparedStatement.setString(4, episode.getTitulo());
            preparedStatement.setInt(5, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEpisodeById(Integer id) {
        String sql = "DELETE FROM episode WHERE episodeid = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.clearParameters();
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Serie> getSerieByGenre(Integer genre_id) {
        List <Serie> series = new ArrayList<>();
        String  query = "SELECT * FROM serie WHERE genre_id = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setInt(1, genre_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Serie serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("seasons"),
                    rs.getInt("genre_id")
                );
                series.add(serie);
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return series;
    }

    public Serie getSerieBytitle(String title) {
        Serie serie = null;
        String  query = "SELECT * FROM serie WHERE title = ?";

        try  (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.clearParameters();
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                serie = new Serie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("seasons"),
                    rs.getInt("genre_id")
                );
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return serie;
    }

    public List<Serie> getSerieByActorID(Integer actorId) {
    List<Serie> series = new ArrayList<>();
    String query = "SELECT * FROM serie WHERE ? = ANY(actors)";

    try (Connection connection = dataSource.getConnection();
    PreparedStatement ps = connection.prepareStatement(query)) {
        ps.clearParameters();
        ps.setInt(1, actorId);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Serie serie = new Serie(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getInt("seasons"),
                rs.getInt("genre_id")
            );
            series.add(serie);
        } 
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return series;
}

}
