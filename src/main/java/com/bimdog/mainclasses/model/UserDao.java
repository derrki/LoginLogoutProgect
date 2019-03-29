package com.bimdog.mainclasses.model;

import com.bimdog.mainclasses.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public boolean checkTableEmpty(){
        boolean emptyValue = true;
        String sqlQuery = "SELECT * from users";
        try(Connection connection = conFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery))
        {
          emptyValue = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emptyValue;
    }


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

    public ArrayList<User> readData() throws DAOException {

        User user = null;
        ArrayList<User> listUser = new ArrayList<>();
        String sqlQuery = "SELECT * from users";
        ResultSet resultSet = null;
        try (Connection connection = conFactory.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(sqlQuery);) {
            resultSet = pStatement.executeQuery();
            if (resultSet != null) {

                while (resultSet.next()){
                    user = new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("login"),  resultSet.getString("password"), "zz", resultSet.getString("role"));
                    listUser.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not insert User data", e);
        }
        return listUser;
    }



    public User readParameterUserData(String login, String password) throws DAOException {
        User user = null;
        String sqlUsers = "SELECT id, name, surname, login, password from users WHERE login=? and password=? limit 1";
        ResultSet resultSet = null;

        try (Connection connection = conFactory.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(sqlUsers);) {
            pStatement.setString(1, login);
            pStatement.setString(2, password);
            resultSet = pStatement.executeQuery();

            if(resultSet != null && resultSet.next()){

                //TODO

            }

        } catch (SQLException e) {
            throw new DAOException("Can not insert User data", e);
        }
        return user;
    }
}