package com.bimdog.mainclasses;

import java.sql.*;

public class DatabaseManager {

    private static final String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static final String USERNAME_MYSQL = "root";
    private static final String PASSWORD_MYSQL = "root";


    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static Connection connectDB(String host_mysql, String username_mysql, String password_mysql) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host_mysql, username_mysql, password_mysql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void insertDataDB(String comandInsert, String columnOneParam) {
        try {
            connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            preparedStatement = getConnection().prepareStatement(comandInsert);
            preparedStatement.setString(1, columnOneParam);
            preparedStatement.execute();
            preparedStatement.close();
            disconnectDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataDB(String comandInsert, String columnOneParam, String columnTwoParam, String columnThreeParam, String columnFourParam) {
        try {
            connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            preparedStatement = getConnection().prepareStatement(comandInsert);
            preparedStatement.setString(1, columnOneParam);
            preparedStatement.setString(2, columnTwoParam);
            preparedStatement.setString(3, columnThreeParam);
            preparedStatement.setString(4, columnFourParam);
            preparedStatement.execute();
            preparedStatement.close();
            disconnectDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String comandQuery, String parameterOne, String parameterTwo) throws SQLException {
        connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
        preparedStatement = DatabaseManager.getConnection().prepareStatement(comandQuery);
        preparedStatement.setString(1, parameterOne);
        preparedStatement.setString(2, parameterTwo);
        resultSet = preparedStatement.executeQuery();
//        resultSet.close();
//        preparedStatement.close(); //TODO не працює з закритими ресами
//        disconnectDb();
        return resultSet;
    }

    public static ResultSet query(String comandQuery) throws SQLException {
        connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
        statement = DatabaseManager.getConnection().createStatement();
        resultSet = statement.executeQuery(comandQuery);
//        statement.close();
//        resultSet.close();//TODO не працює з закритими ресами
//        disconnectDb();
        return resultSet;
    }

    public static void disconnectDb() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

