package com.bimdog.mainclasses.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public void insertUser(String name, String surname, String login, String password, String country) throws DAOException {

        String sqlUsers = "INSERT INTO users(name, surname, login, password) VALUES(?,?,?,?);";
        String sqlCountry = "INSERT INTO user_country(country) VALUES(?);";

        try (Connection connection = conFactory.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(sqlUsers);
             PreparedStatement pStatementCountry = connection.prepareStatement(sqlCountry);)
        {
            pStatement.setString(1, name);
            pStatement.setString(2, surname);
            pStatement.setString(3, login);
            pStatement.setString(4, password);
            pStatement.execute();

            pStatementCountry.setString(1, country);
            pStatementCountry.execute();

        } catch (SQLException e) {
            throw new DAOException("Can not insert User data", e);
        }
    }
}
