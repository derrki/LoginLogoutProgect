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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //дані одержані з форми вводу login.html
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String errorMsg = null;

        //внутрішня валідація одержаних даних
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

            //sql запит для вибірки з таблиці з параметрами
            String sqlUsers = "SELECT id, name, surname, login, password from users WHERE login=? and password=? limit 1";

            ResultSet resultSetUsers = null;
            ResultSet resultSetCountry = null;

            try {
                //вибірка даних з таблиці
                resultSetUsers = DatabaseManager.query(sqlUsers, login, password);

                if(resultSetUsers != null && resultSetUsers.next()){

                    int parameterSearchCountry = resultSetUsers.getInt("id");

                    if(parameterSearchCountry==1){
                        queryAll();
                    }

                    String sqlCountry = "SELECT id, country from user_country WHERE id=" + parameterSearchCountry;
                    resultSetCountry = DatabaseManager.query(sqlCountry);

                    //об'єкт user
                    User user = null;
                    if(resultSetCountry.next()){
                        String country = resultSetCountry.getString("country");
                        user = new User(resultSetUsers.getString("name"), resultSetUsers.getString("surname"), resultSetUsers.getString("login"),  resultSetUsers.getString("password"), country);
                    }

                    //вивід прочитаних даних на jsp
                    HttpSession session = request.getSession();
                    session.setAttribute("User", user);
                    response.sendRedirect("home.jsp");

                } else {
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    request.setCharacterEncoding("UTF-8");
                    PrintWriter out= response.getWriter();
                    out.println("<link rel=\"stylesheet\" href=\"style.css\">");
                    out.println("<font color=red>Не знайдено користувача з таким логіном чи паролем</font>");
                    out.println("<p><a href=\"login.html\">спробувати ще</a></p>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static LinkedList<User> queryAll() throws SQLException {

//            //query
            String query = "SELECT * from users";
            ResultSet resultSet =DatabaseManager.query(query);

            LinkedList<User> listUser = new LinkedList<>();
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

            for (User userOne: listUser) {

                System.out.println(userOne);
            }
            return listUser;
    }
}
