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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String comand = "SELECT id, name, surname, login, password from users WHERE login=? and password=? limit 1";
            DatabaseManager databaseManager = new DatabaseManager();
            try {
                Connection connection = databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            try {
                preparedStatement = databaseManager.getConnection().prepareStatement(comand);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                rs = preparedStatement.executeQuery();
                if(rs != null && rs.next()){
                    User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("login"),  rs.getString("password"), null);
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
