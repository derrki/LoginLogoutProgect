package com.bimdog.mainclasses.model;

import java.sql.*;

public class RoleDAO {

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    String sqlQuery = "select login,password,role from users";

    public String getUserRole(String login, String password) throws DAOException {

        String loginDB = "";
        String passwordDB = "";
        String roleDB = "";
        String userRole = null;

        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery);)
        {
            while (resultSet.next()){
                loginDB = resultSet.getString("login");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if(login.equals(loginDB) && password.equals(passwordDB) && roleDB.equals("admin"))
                    userRole = "admin";
                else if(login.equals(loginDB) && password.equals(passwordDB) && roleDB.equals("user"))
                    userRole = "user";
            }
        } catch (SQLException e) {
            throw new DAOException("Can not validate user role", e);
        }
        System.out.println(userRole);
        return userRole;
    }
}
