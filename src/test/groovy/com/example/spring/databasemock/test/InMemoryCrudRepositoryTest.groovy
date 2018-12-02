package com.example.spring.databasemock.test

import groovy.transform.Immutable
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-12-02.
 */
class InMemoryCrudRepositoryTest extends Specification {
    def "test save - findById"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)

        then:
        repo.findById(1) == Optional.of(be1)
    }

    def "test save - if you put an entity with ID that already exists - exception"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(id: 1, name: "1")
        def be2 = new BasicEntity(id: 1, name: "2")

        when:
        repo.save(be1)
        repo.save(be2)

        then:
        thrown(Exception)
    }

    def "test saveAll"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(id: 1, name: "1")
        def be2 = new BasicEntity(id: 2, name: "2")

        when:
        repo.saveAll([be1, be2])

        then:
        repo.findById(1).isPresent()
        repo.findById(2).isPresent()
    }

    def "test findById - not found"() {
        given:
        def repo = new BasicEntityRepo()

        expect:
        repo.findById(1).isEmpty()
    }

    def "test save - existsById"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)

        then:
        repo.existsById(1)
    }

    def "test NOT existsById"() {
        given:
        def repo = new BasicEntityRepo()

        expect:
        !repo.existsById(1)
    }

    def "test save - findAll"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(id: 1, name: "1")
        def be2 = new BasicEntity(id: 2, name: "2")

        when:
        repo.saveAll([be1, be2])

        then:
        repo.findAll().size() == 2
        repo.findAll().containsAll([be1, be2])
    }

    def "test save - findAll no entities"() {
        given:
        def repo = new BasicEntityRepo()

        expect:
        repo.findAll().isEmpty()
    }

    def "test findAllById"() {
        given:
        def repo = new BasicEntityRepo()
        def be1 = new BasicEntity(id: 1, name: "1")
        def be2 = new BasicEntity(id: 2, name: "2")
        def be3 = new BasicEntity(id: 3, name: "3")

        when:
        repo.saveAll([be1, be2, be3])

        then:
        repo.findAllById([1, 2]) == [be1, be2]
    }
//
//    def "test count"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test deleteById"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test delete"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test deleteAll"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
//
//    def "test deleteAll1"() {
//        given:
//
//        when:
//        // TODO implement stimulus
//        then:
//        // TODO implement assertions
//    }
}