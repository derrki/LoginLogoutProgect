package com.bimdog.mainclasses;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    private static final String USERNAME_MYSQL = "root";
    private static final String PASSWORD_MYSQL = "root";

    public static void main(String[] args) {

        Connection connection;
        DriverManager driverManager;

        try {
            connection = DriverManager.getConnection(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            if(!connection.isClosed()) {
                System.out.println("Приєднано до mysql");
            }
            connection.close();
            if(connection.isClosed()){
                System.out.println("Зєднання з mysql закрито");
            }
        } catch (SQLException e) {
            System.err.println("Приєднання до бази не відбулось, по причині - " + e);
        }
  }
}
