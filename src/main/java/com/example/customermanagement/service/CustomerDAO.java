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
        Connection connection = getConnection();
        String getCustomerById = "SELECT * FROM new_schema.new_table WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getCustomerById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                return new Customer(customerId, name, email, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(int id, Customer customer) {
        Connection connection = getConnection();
        String updateCustomer = "UPDATE new_schema.new_table SET name =?, email =?, address =? WHERE id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCustomer);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }
}
