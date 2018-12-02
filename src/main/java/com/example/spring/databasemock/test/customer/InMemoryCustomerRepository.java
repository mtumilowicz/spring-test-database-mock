package com.example.spring.databasemock.test.customer;

import com.example.spring.databasemock.app.Customer;
import com.example.spring.databasemock.app.CustomerRepository;
import com.example.spring.databasemock.test.InMemoryCrudRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
public class InMemoryCustomerRepository extends InMemoryCrudRepository<Customer, Integer> 
        implements CustomerRepository {
    @Override
    public List<Customer> findAll() {
        var customers = new LinkedList<Customer>();

        super.findAll().forEach(customers::add);
        
        return customers;
    }
}
