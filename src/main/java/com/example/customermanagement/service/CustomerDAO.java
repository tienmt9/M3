package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDAO implements ICustomerService {

    private static Connection getConnection() {
        Connection connection = null;
        String username = "root";
        String password = "123456@abc";
        String url = "jdbc:mysql://localhost:3306/new_schema";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public List<Customer> findAll() {
        Connection connection = getConnection();
        String getCustomer = "SELECT * FROM new_schema.new_table";
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getCustomer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Customer customer = new Customer(id, name, email, address);
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void remove(int id) {

    }
}
