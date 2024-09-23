package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
        private static final String url = "jdbc:postgresql://localhost:2345/bitcuisine";
    private static final String user = "postgres";
    private static final String password = "1230";

    private static Connection connection;
    private DatabaseConnection(){}
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DatabaseConnection.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(url, user, password);
                }
            }
        }
        return connection;
    }
}
