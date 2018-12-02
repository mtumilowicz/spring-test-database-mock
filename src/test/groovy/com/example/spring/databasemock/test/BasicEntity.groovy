package com.example.spring.databasemock.test

import groovy.transform.Immutable
import com.example.spring.databasemock.app.BaseEntity

/**
 * Created by mtumilowicz on 2018-12-02.
 */
@Immutable
class BasicEntity implements BaseEntity<Integer> {
    Integer id
    String name
}
