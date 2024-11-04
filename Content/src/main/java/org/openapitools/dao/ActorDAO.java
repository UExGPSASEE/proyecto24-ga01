package org.openapitools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.openapitools.model.Actor;
import org.springframework.stereotype.Repository;

@Repository
public class ActorDAO {

    private final DataSource dataSource;


    public ActorDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Actor getActorById(Integer id) {
        Actor actor = null;
        String sql = "SELECT * FROM actor WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                actor = new Actor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("birthday_Date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    public  List<Actor> getAllActors() {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM actor";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Actor actor = new Actor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("birthday_Date")
                );
                actors.add(actor); 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public boolean deleteActorById(Integer id) {
        String sql = "DELETE FROM actor WHERE id = ?";

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

    public boolean insertActor(Actor actor) {
        String sql = "INSERT INTO actor (id, name, birthday_Date) VALUES (?, ?, ?)";
    
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, actor.getId());  
            preparedStatement.setString(2, actor.getName()); 
            
            // BLOQUE AÑADIDO PARA MANEJAR EL CAMPO BIRTHDAY_DATE
            if (actor.getBirthdayDate().isPresent()) {
                Object dateObject = actor.getBirthdayDate().get();
                java.sql.Date sqlDate;
    
                if (dateObject instanceof java.util.Date) {
                    sqlDate = new java.sql.Date(((java.util.Date) dateObject).getTime());
                } else if (dateObject instanceof String) {
                    sqlDate = java.sql.Date.valueOf((String) dateObject);
                } else {
                    throw new IllegalArgumentException("Formato de fecha no compatible");
                }
                preparedStatement.setDate(3, sqlDate);
            } else {
                preparedStatement.setNull(3, java.sql.Types.DATE);
            }
            // FIN DEL BLOQUE AÑADIDO
    
            int rowsAffected = preparedStatement.executeUpdate();  
    
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  
        }
    }
    

    public boolean updateActorById(Integer id, Actor actor) {
        String sql = "UPDATE actor SET name = ?, birthday_Date = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, actor.getName());
            
            // BLOQUE AÑADIDO PARA MANEJAR EL CAMPO BIRTHDAY_DATE
            if (actor.getBirthdayDate().isPresent()) {
                Object dateObject = actor.getBirthdayDate().get();
                java.sql.Date sqlDate;

                if (dateObject instanceof java.util.Date) {
                    sqlDate = new java.sql.Date(((java.util.Date) dateObject).getTime());
                } else if (dateObject instanceof String) {
                    sqlDate = java.sql.Date.valueOf((String) dateObject);
                } else {
                    throw new IllegalArgumentException("Formato de fecha no compatible");
                }
                preparedStatement.setDate(2, sqlDate);
            } else {
                preparedStatement.setNull(2, java.sql.Types.DATE);
            }
            // FIN DEL BLOQUE AÑADIDO

            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
}