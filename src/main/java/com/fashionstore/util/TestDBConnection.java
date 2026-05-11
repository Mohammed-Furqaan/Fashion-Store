package com.fashionstore.util;

import java.sql.Connection;

public class TestDBConnection {

    public static void main(String[] args) {

        try {

            Connection connection = DBConnection.getConnection();

            if (connection != null) {

                System.out.println("Connection Test Successful");

                DBConnection.closeConnection(connection);

            } else {

                System.out.println("Connection Test Failed");
            }

        } catch (Exception e) {

            System.out.println("Error While Testing Database Connection");
            e.printStackTrace();
        }
    }
}