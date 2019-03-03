package com.bimdog.mainclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

  public static final String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
  public static final String USERNAME_MYSQL = "root";
  public static final String PASSWORD_MYSQL = "root";

    public static void main(String[] args) throws SQLException {

        String comand = "INSERT INTO users(name, surname, login, password) VALUES('Vasa7', 'vas7', 'log7', 'qwerty7');";
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);

        //Connection connection = DriverManager.getConnection(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
        Statement statement = databaseManager.getConnection().createStatement();
        databaseManager.insert(statement, comand);
    }
}
