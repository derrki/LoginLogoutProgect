package com.bimdog.mainclasses;

import com.bimdog.mainclasses.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseInsert {

    private static String name;
    private static String surname;
    private static String login;
    private static String password;
    private static String country;
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    //sql запити для запису в базу даних
    static String sqlUsers = "INSERT INTO users(name, surname, login, password) VALUES(?,?,?,?);";
    static String sqlCountry = "INSERT INTO user_country(country) VALUES(?);";


    public static void insert(User user) {
        getDataUser(user);
        connection = ConnectionFactory.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sqlUsers);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sqlCountry);
            preparedStatement.setString(1, country);
            preparedStatement.execute();
            preparedStatement.close();

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

    private static void getDataUser(User user) {
        name = user.getName();
        surname = user.getSurname();
        login = user.getLogin();
        password = user.getPassword();
        country = user.getCountry();
    }
}
