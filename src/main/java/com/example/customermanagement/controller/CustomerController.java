package com.example.customermanagement.controller;

import com.example.customermanagement.model.Customer;
import com.example.customermanagement.service.CustomerService;
import com.example.customermanagement.service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                createCustomer(req, resp);
            default:
                showCustomers(req, resp);
        }
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) {

    }

    private static void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICustomerService customerService = new CustomerService();
        List<Customer> customers = customerService.findAll();

        req.setAttribute("customers", customers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/customers/list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
