package com.example.spring.databasemock.test

import com.example.spring.databasemock.app.BaseEntity
import groovy.transform.Immutable
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-12-02.
 */
class InMemoryCrudRepositoryTest extends Specification {
    def "test save - findById"() {
        given:
        def repo = new BasicEntityRepo()
        def basicEntity = new BasicEntity(1)

        when:
        repo.save(basicEntity)

        then:
        repo.findById(1) == basicEntity
    }

    def "test save - if you put an entity with ID that already exists it is omitted"() {
        given:
        def repo = new BasicEntityRepo()
        def first = new BasicEntity(1)
        def second = new BasicEntity(1)

        when:
        repo.save(basicEntity)

        then:
        repo.findById(1) == first
        repo.findById(1) == second
    }

    def "test saveAll"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(1)
        def be2 = new BasicEntity(2)
        
        when:
        repo.save([be1, be2])
        
        then:
        repo.findById(1).isPresent()
        repo.findById(2).isPresent()
    }

    def "test findById"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test existsById"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test findAll"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test findAllById"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test count"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test deleteById"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test delete"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test deleteAll"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }

    def "test deleteAll1"() {
        given:

        when:
        // TODO implement stimulus
        then:
        // TODO implement assertions
    }
}

@Immutable
class BasicEntity implements BaseEntity<Integer> {
    Integer id
}

class BasicEntityRepo extends InMemoryCrudRepository<BasicEntity, Integer> {

}