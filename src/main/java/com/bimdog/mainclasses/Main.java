package com.bimdog.mainclasses;

import java.sql.*;
import java.util.LinkedList;

public class Main {
//  public static final String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db"+
//            "?verifyServerCertificate=false"+
//            "&useSSL=false"+
//            "&requireSSL=false"+
//            "&useLegacyDatetimeCode=false"+
//            "&amp"+
//            "&serverTimezone=UTC";
//  public static final String USERNAME_MYSQL = "root";
//  public static final String PASSWORD_MYSQL = "root";
//
//    public static void main(String[] args) throws SQLException {
//
////
////
////        //connectDB
//        String comand = "INSERT INTO users(name, surname, login, password) VALUES('Vasa9', 'vas9', 'log9', 'qwerty9');";
//        DatabaseManager databaseManager = new DatabaseManager();
//        databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
//
////        //insert
//       Statement statement = databaseManager.getConnection().createStatement();
//   //    databaseManager.insert(statement, comand);
//
//
//        //query
//        String query = "SELECT * from users";
//        ResultSet resultSet = databaseManager.query(statement, query);
//
//        if(!resultSet.next()){
//            System.out.println("Таблиця порожня");
//        }
//
//        LinkedList<User> listUser = new LinkedList<>();
//        User user;
//
//        while (resultSet.next()){
//
//           String name = resultSet.getString("name");
//           String surname = resultSet.getString("surname");
//           String login = resultSet.getString("login");
//           String password = resultSet.getString("password");
//           String country = "UA";
//           user = new User(name, surname, login, password, country);
//           listUser.add(user);
//        }
//
//        for (User userOne: listUser) {
//
//            System.out.println(userOne);
//        }
//    }
}
