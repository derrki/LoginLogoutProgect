package com.bimdog.mainclasses.AppServlet;

import com.bimdog.mainclasses.DatabaseManager;
import com.bimdog.mainclasses.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

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

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String errorMsg = null;

        if(login == null || login.equals("")){
            System.out.println(" login =" +login);
            errorMsg = "Login can't be null or empty.";
        }

        if(password == null || password.equals("")){
            System.out.println("login");
            errorMsg = "Password can't be null or empty.";
        }

        if(errorMsg!=null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            requestDispatcher.include(request, response);
        }else{
            String sqlUsers = "SELECT id, name, surname, login, password from users WHERE login=? and password=? limit 1";
            DatabaseManager databaseManager = new DatabaseManager();
            try {
                Connection connection = databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement preparedStatement = null;
            Statement statement = null;
            ResultSet resultSetUsers = null;
            ResultSet resultSetCountry = null;

            try {
                preparedStatement = databaseManager.getConnection().prepareStatement(sqlUsers);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                resultSetUsers = preparedStatement.executeQuery();



                if(resultSetUsers != null && resultSetUsers.next()){

                    preparedStatement.setString(1, resultSetUsers.getString("id"));
                    statement = databaseManager.getConnection().createStatement();

                    String sqlCountry = "SELECT id, country from user_country WHERE id=" + resultSetUsers.getString("id");
                    ResultSet resultSet = databaseManager.query(statement, sqlCountry);
                    User user = null;
                    if(resultSet.next()){
                        String country = resultSet.getString("country");
                        user = new User(resultSetUsers.getString("name"), resultSetUsers.getString("surname"), resultSetUsers.getString("login"),  resultSetUsers.getString("password"), country);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("User", user);
                    response.sendRedirect("home.jsp");
                    System.out.println(user);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out= response.getWriter();
                    out.println("<font color=red>No user found with given email id, please register first.</font>");
                    rd.include(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
