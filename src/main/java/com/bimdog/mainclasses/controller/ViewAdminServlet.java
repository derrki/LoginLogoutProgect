package com.bimdog.mainclasses.controller;

import com.bimdog.mainclasses.DatabaseManager;
import com.bimdog.mainclasses.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", urlPatterns = { "/AdminServlet" })
public class ViewAdminServlet extends HttpServlet {

DatabaseManager databaseManager = new DatabaseManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        ResultSet resultSetUsers = null;
        ResultSet resultSetCountry = null;
        ArrayList<User> listUser = null;


        String query = "SELECT * from users";
        ResultSet resultSet = null;


        try {
            resultSet = databaseManager.query(query);
            listUser = listAllUser(resultSet);

            User user = null;
                String country = "ua";
                user = new User(resultSetUsers.getString("name"), resultSetUsers.getString("surname"), resultSetUsers.getString("login"),  resultSetUsers.getString("password"), country);


            //вивід прочитаних даних на jsp
            HttpSession session = request.getSession();
            session.setAttribute("ListUser", listUser);
            session.setAttribute("User", user);
            response.sendRedirect("homeAll.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static ArrayList<User> listAllUser(ResultSet resultSet) throws SQLException {

        ArrayList<User> listUser = new ArrayList<>();
        User user;
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String country = "UA";
            user = new User(name, surname, login, password, country);
            listUser.add(user);
        }
        return listUser;
    }
}
