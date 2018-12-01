package com.example.spring.databasemock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
@Repository
interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();
}
