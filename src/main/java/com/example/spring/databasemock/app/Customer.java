package com.example.spring.databasemock.app;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
@Entity
@Value
@Builder
public class Customer implements BaseEntity<Integer> {
    @Id
    Integer id;
    
    String name;
}
