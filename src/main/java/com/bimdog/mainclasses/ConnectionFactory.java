package com.bimdog.mainclasses;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {

    private static ConnectionFactory instance;

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
            instance = new ConnectionFactory();
            return instance;
        }
        return instance;
    }

    //одержуємо параметри конекту до БД з файлу config.properties
    private void getProperties(){
        InputStream inputStream = null;
        Properties properties = new Properties();
        String propFileName = "config.properties";
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);
                DB_URL = properties.getProperty("DB_URL");
                LOGIN = properties.getProperty("LOGIN");
                PASSWORD = properties.getProperty("PASSWORD");
                DRIVER = properties.getProperty("DRIVER");
        } catch (IOException e) {
            System.out.println("ERROR: The properties file does not exist.");
            e.printStackTrace();
        }
        finally {
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}