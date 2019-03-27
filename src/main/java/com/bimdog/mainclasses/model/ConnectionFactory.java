package com.bimdog.mainclasses.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static ConnectionFactory instance = null;

    private static String DB_URL;
    private static String LOGIN;
    private static String PASSWORD;
    private static String DRIVER;

    private ConnectionFactory(){
        getProperties();
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return connection;
    }

   public static ConnectionFactory getInstance(){
        if (instance == null){
            instance = new ConnectionFactory();
        }
        return instance;
    }

    //одержуємо параметри конекту до БД з файлу config.properties
    private void getProperties(){
        ResourceBundle resource = ResourceBundle.getBundle("config");
        DB_URL = resource.getString("DB_URL");
        LOGIN = resource.getString("LOGIN");
        PASSWORD = resource.getString("PASSWORD");
        DRIVER = resource.getString("DRIVER");
    }
}