package com.example.spring.databasemock;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class CustomerService {
    CustomerRepository customerRepository;

    Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }
    
    void delete(Integer id) {
        customerRepository.deleteById(id);
    }
    
    Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
}
