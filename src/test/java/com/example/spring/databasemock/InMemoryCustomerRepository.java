package com.example.spring.databasemock;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
public class InMemoryCustomerRepository implements CustomerRepository {
    private final ConcurrentHashMap<Integer, Customer> map = new ConcurrentHashMap<>();


    @Override
    public <S extends Customer> S save(S entity) {
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Customer> findById(Integer integer) {
        return Optional.ofNullable(map.get(integer));
    }

    @Override
    public Iterable<Customer> findAll() {
        return map.values();
    }

    @Override
    public void deleteById(Integer integer) {
        map.remove(integer);
    }
    
    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
