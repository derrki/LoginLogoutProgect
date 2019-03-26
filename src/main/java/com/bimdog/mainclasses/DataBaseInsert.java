package com.bimdog.mainclasses;

import com.bimdog.mainclasses.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseInsert {

    private ConnectionFactory conFactory = ConnectionFactory.getInstance();

    public void insert(String name, String surname, String login, String password, String country) {

        Connection connection = null;
        PreparedStatement pStatement = null;

        //sql запити для запису в базу даних
        String sqlUsers = "INSERT INTO users(name, surname, login, password) VALUES(?,?,?,?);";
        String sqlCountry = "INSERT INTO user_country(country) VALUES(?);";

        try {
            connection = conFactory.getConnection();
            pStatement = connection.prepareStatement(sqlUsers);
            pStatement.setString(1, name);
            pStatement.setString(2, surname);
            pStatement.setString(3, login);
            pStatement.setString(4, password);
            pStatement.execute();

            pStatement = connection.prepareStatement(sqlCountry);
            pStatement.setString(1, country);
            pStatement.execute();
            pStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
