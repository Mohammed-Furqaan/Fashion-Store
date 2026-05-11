package com.fashionstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/fashion_store1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBConnection() {
    }

    public static Connection getConnection() {

        Connection connection = null;

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully");

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver Not Found");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Failed to Connect Database");
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {

        if (connection != null) {

            try {

                connection.close();
                System.out.println("Database Connection Closed");

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}
