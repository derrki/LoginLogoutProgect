package com.bimdog.mainclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory instance;

    private static String DB_URL;
    private static String LOGIN;
    private static String PASSWORD;

    private ConnectionFactory(){
        getProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection(){
        return getInstance().createConnection();
    }

    private static ConnectionFactory getInstance(){
        if (instance == null){
            return new ConnectionFactory();
        }
        return instance;
    }

    private static void getProperties(){
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream("src/main/resources/data_base_settings.properties");
                properties.load(fileInputStream);
                DB_URL = properties.getProperty("DB_URL");
                LOGIN = properties.getProperty("USERNAME_MYSQL");
                PASSWORD = properties.getProperty("PASSWORD_MYSQL");

        } catch (IOException e) {
            System.out.println("ERROR: The properties file does not exist.");
            e.printStackTrace();
        }
    }
}