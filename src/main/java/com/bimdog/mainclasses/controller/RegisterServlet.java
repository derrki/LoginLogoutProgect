package com.bimdog.mainclasses.controller;

import com.bimdog.mainclasses.model.DAOException;
import com.bimdog.mainclasses.model.UserDao;
import com.bimdog.mainclasses.util.Validation;

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

        //внутрішня валідація одержаних даних
        String errorMsg = Validation.validationFormData(name, surname, login, password, country);


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
            UserDao userDao = new UserDao();
            try {
                userDao.insertUser(name, surname, login, password, country);
            } catch (DAOException e) {
                e.printStackTrace();
            }

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
