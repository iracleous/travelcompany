package com.advantage.travelcompany;

import com.advantage.travelcompany.dao.impl.CustomerRepositoryImpl;
import com.advantage.travelcompany.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);



    public static void main(String[] args) throws SQLException {


        org.h2.Driver.load();


        Connection conn = DriverManager.getConnection(
                "jdbc:h2:file:~/test;USER=sa;PASSWORD=password",
                "sa",
                "password");




        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(conn);

 //     customerRepository.createTableCustomers();


        Customer customer = new Customer();
        customer.setFirstName("Theofilos"); customer.setLastName("X"); customer.setEmail("x@gmail.com");

        int id = customerRepository.addtoDb(customer);
        logger.info("Customer id ={}", id);


        Customer anotherCustomer = customerRepository.getfromDb(2);
        logger.info("Customer = {} {} {}", anotherCustomer.getFirstName(), anotherCustomer.getLastName(), anotherCustomer.getEmail());


        conn.close();

    }
}
