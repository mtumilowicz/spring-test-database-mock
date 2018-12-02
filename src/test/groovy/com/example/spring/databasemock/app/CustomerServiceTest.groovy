package com.example.spring.databasemock.app

import com.example.spring.databasemock.test.customer.InMemoryCustomerRepository
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-12-01.
 */
class CustomerServiceTest extends Specification {

    def "save - findById"() {
        given:
        def customerService = new CustomerService(new InMemoryCustomerRepository())

        and:
        def customer = Customer.builder().id(1).name("name").build()

        when:
        customerService.save(customer)
        
        then:
        customerService.findById(1) == Optional.of(customer)
    }

    def "save - deleteById - findById"() {
        given:
        def customerService = new CustomerService(new InMemoryCustomerRepository())

        and:
        def customer = Customer.builder().id(1).name("name").build()

        when:
        customerService.save(customer)

        then:
        customerService.findById(1) == Optional.of(customer)
        
        when:
        customerService.deleteById(1)
        
        then:
        customerService.findById(1) == Optional.empty()
    }
    
    def "save - findAll"() {
        given:
        def customerService = new CustomerService(new InMemoryCustomerRepository())

        and:
        def customer1 = Customer.builder().id(1).name("name").build()
        def customer2 = Customer.builder().id(2).name("name2").build()

        and:
        customerService.save(customer1)
        customerService.save(customer2)

        expect:
        customerService.findAll() == [customer1, customer2]
    }
    
    def "findAll - empty"() {
        given:
        def customerService = new CustomerService(new InMemoryCustomerRepository())
        
        expect:
        customerService.findAll() == []
    }
}