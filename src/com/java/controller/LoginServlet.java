package com.java.controller;

import com.java.core.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(LoginServlet.class);
//    private static UserAuthentication authenticator = UserAuth.getUserAuthenticator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        logger.info("Logging in user " + user.getUsername());

        if (user.getUsername().equals("user") && user.getPassword().equals("password")){
            logger.info("Log in user successfull.");
            request.getSession().setAttribute("priviledge", 0);
            request.getSession().setAttribute("username", user.getUsername());
        } else if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
            logger.info("Log in admin successfull.");
            request.getSession().setAttribute("priviledge", 5);
            request.getSession().setAttribute("username", user.getUsername());
        } else {
            logger.info("Could not match password.");
            String  respond = "<html>Invalid username/password combination! <a href=\"login.html\">Click here</a> to try again</html>";
            response.getWriter().write(respond);
        }

        response.sendRedirect("calculatebill");
    }
}
