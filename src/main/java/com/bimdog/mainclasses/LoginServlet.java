package com.bimdog.mainclasses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("link.html").include(request, response);

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

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
