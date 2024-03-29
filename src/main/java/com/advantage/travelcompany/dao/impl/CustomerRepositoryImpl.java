package com.advantage.travelcompany.dao.impl;

import com.advantage.travelcompany.dao.DaoRepository;
import com.advantage.travelcompany.model.Customer;

import java.sql.*;
import java.util.List;

public class CustomerRepositoryImpl implements DaoRepository<Customer> {

    private Connection conn;

    public CustomerRepositoryImpl(Connection conn){
        this.conn=conn;
    }



    public void createTableCustomers() throws SQLException {

        String query = "create table Customer ( id IDENTITY NOT NULL PRIMARY KEY , firstName varchar(100), LastName varchar(100), email varchar(100) ) ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.executeUpdate();
        statement.close();
    }


    @Override
    public int addtoDb(Customer customer) throws SQLException {


        String query = "insert into Customer ( firstName, LastName, email ) values(?,?,?)";

        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getEmail());
        statement.executeUpdate();

        int last_inserted_id = -1;
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }

        statement.close();
        return last_inserted_id;
    }

    @Override
    public Customer getfromDb(int id) throws SQLException {
        String query = "select firstName, LastName, email from Customer  ";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        rs.next();
        Customer customer = new Customer();
        customer.setFirstName(rs.getString(1));
        customer.setLastName(rs.getString(2));
        customer.setEmail(rs.getString(3));
        statement.close();
        return customer;

    }

    @Override
    public Customer updatetoDb(int id, String newEmail) {
        return null;
    }

    @Override
    public boolean deleteFromDb(int id) {
        return false;
    }

    @Override
    public List<Customer> getfromDb() {
        return null;
    }
}
