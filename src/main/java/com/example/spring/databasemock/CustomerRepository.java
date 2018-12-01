package com.example.spring.databasemock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
@Repository
interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
