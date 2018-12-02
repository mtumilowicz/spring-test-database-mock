package com.example.spring.databasemock.test

import org.springframework.data.domain.Example
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-12-02.
 */
class InMemoryBasicJpaRepositoryTest extends Specification {

        def "test save - findById"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)

        then:
        repo.findById(1) == Optional.of(be1)
    }

    def "test save - if you put an entity with ID that already exists - exception"() {
        given:
        def repo = new BasicEntityJpaRepo()
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
        def repo = new BasicEntityJpaRepo()
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
        def repo = new BasicEntityJpaRepo()

        expect:
        repo.findById(1).isEmpty()
    }

    def "test save - existsById"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)

        then:
        repo.existsById(1)
    }

    def "test NOT existsById"() {
        given:
        def repo = new BasicEntityJpaRepo()

        expect:
        !repo.existsById(1)
    }

    def "test save - findAll"() {
        given:
        def repo = new BasicEntityJpaRepo()
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
        def repo = new BasicEntityJpaRepo()

        expect:
        repo.findAll().isEmpty()
    }

    def "test findAllById"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")
        def be2 = new BasicEntity(id: 2, name: "2")
        def be3 = new BasicEntity(id: 3, name: "3")

        when:
        repo.saveAll([be1, be2, be3])

        then:
        repo.findAllById([1, 2]) == [be1, be2]
    }

    def "test deleteById - exists"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)
        repo.deleteById(1)

        then:
        repo.count() == 0
    }

    def "test deleteById - not exists"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.deleteById(1)

        then:
        repo.count() == 0
    }

    def "test delete - exists"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)
        repo.delete(be1)

        then:
        repo.count() == 0
    }

    def "test delete not exists"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.delete(be1)

        then:
        repo.count() == 0
    }
    
        def "test deleteAll - empty repo"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.deleteAll()

        then:
        repo.count() == 0
    }

    def "test deleteAll"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)
        repo.deleteAll()

        then:
        repo.count() == 0
    }


    def "test deleteAll(Iterable...) - empty repo"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.deleteAll([])

        then:
        repo.count() == 0
    }
    
    def "test deleteAll(Iterable...)"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)
        repo.deleteAll([be1])

        then:
        repo.count() == 0
    }
    
        def "test deleteAll(Iterable...) - entities not in repo"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.deleteAll([be1])

        then:
        repo.count() == 0
    }

    def "test count - empty"() {
        given:
        def repo = new BasicEntityJpaRepo()

        expect:
        repo.count() == 0
    }

    def "test count"() {
        given:
        def repo = new BasicEntityJpaRepo()
        def be1 = new BasicEntity(id: 1, name: "1")

        when:
        repo.save(be1)

        then:
        repo.count() == 1
    }

    def "test flush"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.flush()

        then:
        thrown(UnsupportedOperationException)
    }

    def "test saveAndFlush"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.saveAndFlush(null)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test deleteInBatch"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.deleteInBatch(Collections.EMPTY_LIST)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test deleteAllInBatch"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.deleteAllInBatch()

        then:
        thrown(UnsupportedOperationException)
    }

    def "test getOne"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.getOne(1)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test findOne"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.findOne((Example) null)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test findAll(Sort sort)"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.findAll((Sort) null)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test findAll(Example<S> example)"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.findAll((Example) null)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test findAll(Example<S> example, Sort sort)"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.findAll((Example) null, (Sort) null)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test findAll(Example<S> example, Pageable pageable)"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.findAll((Example) null, (Pageable) null)

        then:
        thrown(UnsupportedOperationException)
    }

    def "test exists"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.exists((Example) null)
        
        then:
        thrown(UnsupportedOperationException)
    }

    def "test countcount(Example<S> example)"() {
        given:
        def repo = new BasicEntityJpaRepo()

        when:
        repo.count((Example) null)

        then:
        thrown(UnsupportedOperationException)
    }
}
