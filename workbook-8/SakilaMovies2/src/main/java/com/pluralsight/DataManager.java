package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private BasicDataSource dataSource;

    public DataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actor> getActorsByLastName(String lastName) {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor WHERE last_name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int actorId = resultSet.getInt("actor_id");
                    String firstName = resultSet.getString("first_name");
                    String lastNameFromDb = resultSet.getString("last_name");
                    actors.add(new Actor(actorId, firstName, lastNameFromDb));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Film> getFilmsByActor(String firstName, String lastName) {
        List<Film> films = new ArrayList<>();
        String query = "SELECT f.film_id, f.title, f.description, f.release_year, f.length " +
                "FROM film f " +
                "JOIN film_actor fa ON f.film_id = fa.film_id " +
                "JOIN actor a ON fa.actor_id = a.actor_id " +
                "WHERE a.first_name = ? AND a.last_name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int filmId = resultSet.getInt("film_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    int releaseYear = resultSet.getInt("release_year");
                    int length = resultSet.getInt("length");
                    films.add(new Film(filmId, title, description, releaseYear, length));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }
}