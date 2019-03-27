package com.bimdog.mainclasses.model;

import com.bimdog.mainclasses.User;

import java.sql.*;

public class UserDao {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public void insertUser(String name, String surname, String login, String password, String country, String role) throws DAOException {

        String sqlUsers = "INSERT INTO users(name, surname, login, password, role) VALUES(?,?,?,?,?);";
        String sqlCountry = "INSERT INTO user_country(country) VALUES(?);";

        try (Connection connection = conFactory.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(sqlUsers);
             PreparedStatement pStatementCountry = connection.prepareStatement(sqlCountry);) {
            pStatement.setString(1, name);
            pStatement.setString(2, surname);
            pStatement.setString(3, login);
            pStatement.setString(4, password);
            pStatement.setString(5, role);
            pStatement.execute();

            pStatementCountry.setString(1, country);
            pStatementCountry.execute();

        } catch (SQLException e) {
            throw new DAOException("Can not insert User data", e);
        }
    }

    public User readData() throws DAOException {

        User user = null;
        String sqlQuery = "SELECT * from users";
        ResultSet resultSet = null;

        try (Connection connection = conFactory.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(sqlQuery);) {
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
            }
        } catch (SQLException e) {
            throw new DAOException("Can not insert User data", e);
        }
        return user;
    }

}
