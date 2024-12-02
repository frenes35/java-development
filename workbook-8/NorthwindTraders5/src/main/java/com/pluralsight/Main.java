package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;
import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        // Check for command line arguments for username and password
        if (args.length != 2) {
            System.out.println("Application needs two arguments to run: " +
                    "java com.pluralsight.Main <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        // Create a DataSource instance using BasicDataSource
        DataSource dataSource = createDataSource(username, password);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1) Display all products");
            System.out.println("2) Display all customers");
            System.out.println("3) Display all categories");
            System.out.println("0) Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllProducts(dataSource);
                    break;
                case 2:
                    displayAllCustomers(dataSource);
                    break;
                case 3:
                    displayAllCategories(dataSource);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    // Create a DataSource instance
    private static DataSource createDataSource(String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        return dataSource;
    }

    private static void displayAllCategories(DataSource dataSource) {
        String query = "SELECT CategoryID, CategoryName FROM Categories ORDER BY CategoryID";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Categories:");
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("CategoryID");
                String categoryName = resultSet.getString("CategoryName");
                System.out.println(categoryId + ": " + categoryName);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the category ID: ");
            int categoryId = scanner.nextInt();

            displayProductsInCategory(dataSource, categoryId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayProductsInCategory(DataSource dataSource, int categoryId) {
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products WHERE CategoryID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, categoryId);
            try (ResultSet productResultSet = preparedStatement.executeQuery()) {
                System.out.println("\nProducts in the selected category:");
                while (productResultSet.next()) {
                    int productId = productResultSet.getInt("ProductID");
                    String productName = productResultSet.getString("ProductName");
                    double unitPrice = productResultSet.getDouble("UnitPrice");
                    int unitsInStock = productResultSet.getInt("UnitsInStock");

                    System.out.println("Product ID: " + productId);
                    System.out.println("Product Name: " + productName);
                    System.out.println("Unit Price: " + unitPrice);
                    System.out.println("Units In Stock: " + unitsInStock);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllProducts(DataSource dataSource) {
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                int productId = results.getInt("ProductID");
                String productName = results.getString("ProductName");
                double unitPrice = results.getDouble("UnitPrice");
                int unitsInStock = results.getInt("UnitsInStock");

                System.out.println("Product ID: " + productId);
                System.out.println("Product Name: " + productName);
                System.out.println("Unit Price: " + unitPrice);
                System.out.println("Units In Stock: " + unitsInStock);
                System.out.println("-----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllCustomers(DataSource dataSource) {
        String query = "SELECT ContactName, CompanyName, City, Country, Phone FROM Customers ORDER BY Country";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                String contactName = results.getString("ContactName");
                String companyName = results.getString("CompanyName");
                String city = results.getString("City");
                String country = results.getString("Country");
                String phone = results.getString("Phone");

                System.out.println("Contact Name: " + contactName);
                System.out.println("Company Name: " + companyName);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);
                System.out.println("Phone: " + phone);
                System.out.println("-----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}