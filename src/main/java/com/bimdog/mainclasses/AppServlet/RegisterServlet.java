package com.bimdog.mainclasses.AppServlet;


import com.bimdog.mainclasses.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    public static final String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    public static final String USERNAME_MYSQL = "root";
    public static final String PASSWORD_MYSQL = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //дані одержані з форми вводу register.html
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String errorMsg = null;

        //внутрішня валідація одержаних даних
        if(name == null || name.equals("")){
            errorMsg = "Name can't be null or empty.";
        }
        if(surname == null || surname.equals("")){
            errorMsg = "Surname can't be null or empty.";
        }
        if(login == null || login.equals("")){
            errorMsg = "Login can't be null or empty.";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty.";
        }
        if(country == null || country.equals("")){
            errorMsg = "Country can't be null or empty.";
        }

        if(errorMsg!=null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            requestDispatcher.include(request, response);
        }
        else {
            String sqlUsers = "INSERT INTO users(name, surname, login, password) VALUES(?,?,?,?);";
            String sqlCountry = "INSERT INTO user_country(country) VALUES(?);";

            DatabaseManager databaseManager = new DatabaseManager();
            try {
                Connection connection = databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement preparedStatement;
            databaseManager.insertDataDB(sqlUsers, name, surname, login, password);
            databaseManager.insertDataDB(sqlCountry, country);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=green>Registration successful, please login below.</font>");
            rd.include(request, response);
        }

    }
}
