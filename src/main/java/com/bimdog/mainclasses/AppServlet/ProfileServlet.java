package com.bimdog.mainclasses.AppServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("link.html").include(request, response);

        HttpSession session=request.getSession(false);
        if(session!=null){
            String name=(String)session.getAttribute("name");
            String surname = (String) session.getAttribute("surname");

            out.print("Hello, "+name+ " " + surname + " Welcome to Profile");
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("login0.html").include(request, response);
        }
        out.close();
    }
}
