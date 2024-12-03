package com.pluralsight;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username = "root";
        String password = "p@swOrd";

        DataSource dataSource = createDataSource(username, password);

        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter the last name of the actor you like:");
        String lastName = scanner.nextLine();
        displayActorsByLastName(dataSource, lastName);


        System.out.println("\nPlease enter the first name of the actor:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter the last name of the actor:");
        lastName = scanner.nextLine();
        displayMoviesByActor(dataSource, firstName, lastName);
    }


    private static DataSource createDataSource(String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        return dataSource;
    }


    private static void displayActorsByLastName(DataSource dataSource, String lastName) {
        String query = "SELECT actor.first_name, actor.last_name " +
                "FROM actor " +
                "WHERE actor.last_name LIKE ? " +
                "ORDER BY actor.first_name";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + lastName + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No actors found with the last name: " + lastName);
                    return;
                }

                System.out.println("Actors with the last name '" + lastName + "':");
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastNameFound = resultSet.getString("last_name");
                    System.out.println("First Name: " + firstName + ", Last Name: " + lastNameFound);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void displayMoviesByActor(DataSource dataSource, String firstName, String lastName) {
        String query = "SELECT film.title " +
                "FROM film " +
                "JOIN film_actor ON film.film_id = film_actor.film_id " +
                "JOIN actor ON film_actor.actor_id = actor.actor_id " +
                "WHERE actor.first_name = ? AND actor.last_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No movies found for the actor: " + firstName + " " + lastName);
                    return;
                }

                System.out.println("\nMovies starring " + firstName + " " + lastName + ":");
                while (resultSet.next()) {
                    String movieTitle = resultSet.getString("title");
                    System.out.println("Movie Title: " + movieTitle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}