package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Application needs two arguments to run: java com.hca.jdbc.UsingDriverManager <username> <password>");
                System.exit(1);
            }

            String username = args[0];
            String password = args[1];

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password);

            String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet results = statement.executeQuery();

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

            results.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}