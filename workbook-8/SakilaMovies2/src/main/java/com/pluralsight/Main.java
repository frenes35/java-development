package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a BasicDataSource
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssw0rd");

        // Create the DataManager
        DataManager dataManager = new DataManager(dataSource);

        try (Scanner scanner = new Scanner(System.in)) {
            // Ask for the last name of an actor
            System.out.print("Enter the last name of an actor: ");
            String lastName = scanner.nextLine();

            // Retrieve and display actors with the given last name
            List<Actor> actors = dataManager.getActorsByLastName(lastName);
            if (!actors.isEmpty()) {
                System.out.println("Actors found:");
                for (Actor actor : actors) {
                    System.out.println(actor);
                }
            } else {
                System.out.println("No actors found with that last name.");
            }

            // Ask for the first and last name to get films by actor
            System.out.print("\nEnter the first name of an actor: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter the last name of an actor: ");
            lastName = scanner.nextLine();

            // Retrieve and display films by actor's name
            List<Film> films = dataManager.getFilmsByActor(firstName, lastName);
            if (!films.isEmpty()) {
                System.out.println("\nFilms the actor has been in:");
                for (Film film : films) {
                    System.out.println(film);
                }
            } else {
                System.out.println("No films found for the actor.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}