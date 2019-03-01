package com.bimdog.mainclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection;

    public boolean connect(String host_mysql, String username_mysql, String password_mysql) throws SQLException {
        connection = DriverManager.getConnection(host_mysql, username_mysql, password_mysql);
        return !connection.isClosed();
    }
}

