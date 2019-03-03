package com.bimdog.mainclasses;

import java.sql.*;

public class DatabaseManager {

    private Connection connection = null;

    public Connection connectDB(String host_mysql, String username_mysql, String password_mysql) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host_mysql, username_mysql, password_mysql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
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

    public void disconnectDb() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

