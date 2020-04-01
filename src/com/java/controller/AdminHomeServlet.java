package com.java.controller;

import com.java.core.Bill;
import com.java.exception.DatabaseException;
import com.java.exception.NoAccountFoundException;
import com.java.service.EBillService;
import com.java.service.EBillServiceImpl;
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

    static EBillService eBillService;

    static {
        eBillService = new EBillServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer priviledge = (Integer) request.getSession().getAttribute("priviledge");
        if (priviledge == null || priviledge <= 0){
            logger.info("Not enough priviledge to access admin page.");
            request.getSession().invalidate();
            response.getWriter().write("<html>You do not have priviledge. <a href='/SessionManagement'>Click here</a> to go back to login page<html>");
            return;
        }
        long cNum = Long.parseLong(request.getParameter("cNum"));
        double prevRead = Double.parseDouble(request.getParameter("prevRead"));
        double currRead = Double.parseDouble(request.getParameter("currRead"));

        Bill bill = new Bill();
        bill.setCustomerNum(cNum);
        bill.setPrevReading(prevRead);
        bill.setCurrReading(currRead);

        String htmlResponse = "<html>";
        try {
            bill = eBillService.calculateBill(bill);
            htmlResponse += "<h4>Welcome " + request.getSession().getAttribute("username") + "</h4><br/><br/>";
            htmlResponse += "<h1>Electricity Bill for Consumer Number - " + bill.getCustomerNum() + " is</h1><br/><br/>";
            htmlResponse += "<h2>Unit Consumed:: " + bill.getUnitConsumed() + "</h2><br/>";
            htmlResponse += "<h2>Net Amount:: Rs." + bill.getNetAmount() + "</h2><br/><br/>";
        } catch (DatabaseException e) {
            e.printStackTrace();
            htmlResponse += "An error happened on our end. Please try again later.";
        } catch (NoAccountFoundException e) {
            htmlResponse += "Invalid Consumer Number.";
        }

        htmlResponse += "</html>";

        response.getWriter().print(htmlResponse);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer priviledge = (Integer) request.getSession().getAttribute("priviledge");
        if (priviledge == null || priviledge <= 0){
            logger.info("Not enough priviledge to access admin page.");
            response.getWriter().write("<html>You do not have priviledge. <a href='/SessionManagement'>Click here</a> to go back to login page<html>");
            return;
        }

        String htmlResponse = "<html><form action='calculatebill' method='post'>";
        htmlResponse += "Consumer Number:<input type='text' pattern='[1-9]\\d{5}' required name='cNum'><br/>";
        htmlResponse += "Last Month Meter Reading:<input type='number' min=\"0.00\" max=\"10000.00\" step=\"0.01\" required name='prevRead'><br/>";
        htmlResponse += "Current Month Meter Reading:<input type='number' min=\"0.00\" max=\"10000.00\" step=\"0.01\" required name='currRead'><br/>";
        htmlResponse += "<input type='submit' value='Calculate Bill'>";
        htmlResponse += "</form></html>";

        response.getWriter().println(htmlResponse);
    }
}
