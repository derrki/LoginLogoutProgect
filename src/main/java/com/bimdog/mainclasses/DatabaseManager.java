package com.bimdog.mainclasses;

import java.sql.*;

public class DatabaseManager {

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;


    public static Connection getConnection() {
        return connection;
    }

    public static void insertDataDB(String comandInsert, String columnOneParam) {
        try {
            connection=ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(comandInsert);
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
            connection=ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(comandInsert);
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
        connection=ConnectionFactory.getConnection();
        preparedStatement = connection.prepareStatement(comandQuery);
        preparedStatement.setString(1, parameterOne);
        preparedStatement.setString(2, parameterTwo);
        resultSet = preparedStatement.executeQuery();
//        resultSet.close();
//        preparedStatement.close(); //TODO не працює з закритими ресами
//        disconnectDb();
        return resultSet;
    }

    public static ResultSet query(String comandQuery) throws SQLException {
        connection=ConnectionFactory.getConnection();
        statement = connection.createStatement();
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

