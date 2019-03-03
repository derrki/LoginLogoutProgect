package com.bimdog.mainclasses;

import java.sql.*;

public class DatabaseManager {

    private Connection connection;

    public void connect(String host_mysql, String username_mysql, String password_mysql) throws SQLException {
        connection = DriverManager.getConnection(host_mysql, username_mysql, password_mysql);
    }

    public Connection getConnection() {
        return connection;
    }

    public void insert(Statement statement, String comandInsert) throws SQLException{
        statement.execute(comandInsert);
    }

    public ResultSet query(Statement statement, String comandQuery) throws SQLException {
        return statement.executeQuery(comandQuery);
    }

    public void closeConnection() throws SQLException {
       connection.close();
    }
}

