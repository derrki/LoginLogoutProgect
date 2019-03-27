package com.bimdog.mainclasses;

import com.bimdog.mainclasses.model.ConnectionFactory;

import java.sql.*;

public class DatabaseManager {

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    public static Connection getConnection() {
        return connection;
    }

    public ResultSet query(String comandQuery, String parameterOne, String parameterTwo) throws SQLException {
        connection=connectionFactory.getConnection();
        preparedStatement = connection.prepareStatement(comandQuery);
        preparedStatement.setString(1, parameterOne);
        preparedStatement.setString(2, parameterTwo);
        resultSet = preparedStatement.executeQuery();
//        resultSet.close();
//        preparedStatement.close(); //TODO не працює з закритими ресами
//        disconnectDb();
        return resultSet;
    }

    public ResultSet query(String comandQuery) throws SQLException {
        connection=connectionFactory.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(comandQuery);
//        statement.close();
//        resultSet.close();//TODO не працює з закритими ресами
//        disconnectDb();
        return resultSet;
    }
}

