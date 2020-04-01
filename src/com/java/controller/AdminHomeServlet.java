package com.java.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/calculatebill", name = "AdminHomeServlet")
public class AdminHomeServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(AdminHomeServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("NOT SUPPORTED.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer priviledge = (Integer) request.getSession().getAttribute("priviledge");
        if (priviledge == null || priviledge <= 0){
            logger.info("Not enough priviledge to access admin page.");
            response.getWriter().write("<html>You do not have priviledge. <a href='login'>Click here</a> to go back to login page<html>");
            return;
        }

        String htmlResponse = "<html><form action='calculatebill' method='post'>";
        htmlResponse += "Consumer Number:<input type='number' required><br/>";
        htmlResponse += "Last Month Meter Reading:<input type='number' required><br/>";
        htmlResponse += "Current Month Meter Reading:<input type='number' required><br/>";
        htmlResponse += "<input type='submit' value='Calculate Bill'>";
        htmlResponse += "</form></html>";

    }
}
