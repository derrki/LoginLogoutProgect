package com.bimdog.mainclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Connection connection;

    public void connect(String host_mysql, String username_mysql, String password_mysql) throws SQLException {
        connection = DriverManager.getConnection(host_mysql, username_mysql, password_mysql);
    }

    public Connection getConnection() {
        return connection;
    }

    public void insert(Statement statement, String comand) throws SQLException{
        statement.execute(comand);
    }

    public void closeConnection() throws SQLException {
       connection.close();
    }
}

