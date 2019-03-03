package com.bimdog.mainclasses.AppServlet;

import com.bimdog.mainclasses.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
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
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("link.html").include(request, response);

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

      // String comand = "INSERT INTO users(name, surname, login, password) VALUES('Vasa8', 'vas8', 'log8', 'qwerty8');";
      String comand = "INSERT INTO users(name, surname, login, password) VALUES(?,?,?,?);";

      DatabaseManager databaseManager = new DatabaseManager();
      PreparedStatement preparedStatement;

      try {
          databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
            preparedStatement = databaseManager.getConnection().prepareStatement(comand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.execute();

      } catch (SQLException e) {
          e.printStackTrace();
      }

      if(password.equals("root")){
            out.print("Welcome, " + name + " " + surname);
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("surname", surname);
        }
        else{
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.close();
    }

}
