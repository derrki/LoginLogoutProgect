package com.bimdog.mainclasses;

import java.sql.*;

public class DatabaseManager {

    private Connection connection = null;
    PreparedStatement preparedStatement = null;

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

    public void insertDataDB(String comandInsert, String columnOneParam) {
        try {
            preparedStatement = getConnection().prepareStatement(comandInsert);
            preparedStatement.setString(1, columnOneParam);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataDB(String comandInsert, String columnOneParam, String columnTwoParam, String columnThreeParam, String columnFourParam){
        try {
            preparedStatement = getConnection().prepareStatement(comandInsert);
            preparedStatement.setString(1, columnOneParam);
            preparedStatement.setString(2, columnTwoParam);
            preparedStatement.setString(3, columnThreeParam);
            preparedStatement.setString(4, columnFourParam);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

