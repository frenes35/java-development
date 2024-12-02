package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Application needs two arguments to run: java com.pluralsight.Main <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

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
                    displayAllProducts(username, password);
                    break;
                case 2:
                    displayAllCustomers(username, password);
                    break;
                case 3:
                    displayAllCategories(username, password);
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

    private static void displayAllProducts(String username, String password) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                PreparedStatement statement = connection.prepareStatement("SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products");
                ResultSet results = statement.executeQuery()
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

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

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllCustomers(String username, String password) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                PreparedStatement statement = connection.prepareStatement("SELECT ContactName, CompanyName, City, Country, Phone FROM Customers ORDER BY Country");
                ResultSet results = statement.executeQuery()
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

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

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllCategories(String username, String password) {
        Scanner scanner = new Scanner(System.in);

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                PreparedStatement statement = connection.prepareStatement("SELECT CategoryID, CategoryName FROM Categories ORDER BY CategoryID");
                ResultSet results = statement.executeQuery()
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Categories:");
            while (results.next()) {
                int categoryId = results.getInt("CategoryID");
                String categoryName = results.getString("CategoryName");

                System.out.println("Category ID: " + categoryId + ", Category Name: " + categoryName);
            }

            System.out.print("Enter a category ID to see its products: ");
            int selectedCategoryId = scanner.nextInt();

            displayProductsByCategory(selectedCategoryId, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayProductsByCategory(int categoryId, String username, String password) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT ProductID, ProductName, UnitPrice, UnitsInStock " +
                                "FROM Products WHERE CategoryID = ?"
                )
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            statement.setInt(1, categoryId);

            try (ResultSet results = statement.executeQuery()) {
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
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}