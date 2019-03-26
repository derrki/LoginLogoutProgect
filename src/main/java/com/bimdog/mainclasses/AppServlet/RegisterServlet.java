package com.bimdog.mainclasses.AppServlet;

import com.bimdog.mainclasses.DataBaseInsert;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServletNew", urlPatterns = {"/RegisterServletNew"})
public class RegisterServlet extends HttpServlet {

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
        if (name == null || name.equals("")) {
            errorMsg = "Name can't be null or empty.";
        }
        if (surname == null || surname.equals("")) {
            errorMsg = "Surname can't be null or empty.";
        }
        if (login == null || login.equals("")) {
            errorMsg = "Login can't be null or empty.";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty.";
        }
        if (country == null || country.equals("")) {
            errorMsg = "Country can't be null or empty.";
        }

        //вивід повідомлення про невірний ввід даних
        if (errorMsg != null) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("<font color=red>" + errorMsg + "</font>");
            out.println("<p><a href=\"register.html\">Повторити</a></p>");

        } else {

            //запис даних в базу
            DataBaseInsert dataBaseInsert = new DataBaseInsert();
            dataBaseInsert.insert(name, surname, login, password, country);

            //вивід повідомлення про успішний ввід даних
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.println("<link rel=\"stylesheet\" href=\"style.css\">");
            out.println("<font color=green>Реєстрація успішна, будь ласка, увійдіть.</font>");
            out.println("<p><a href=\"login.html\">увійти</a></p>");
        }
    }
}
