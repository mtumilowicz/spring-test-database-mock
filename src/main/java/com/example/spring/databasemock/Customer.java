package com.example.spring.databasemock;

import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
@Entity
@Value
class Customer {
    @Id
    Integer id;
    
    String name;
}
