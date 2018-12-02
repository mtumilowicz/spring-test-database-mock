package com.example.spring.databasemock;

import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mtumilowicz on 2018-12-02.
 */
public class InMemoryCrudRepository<T extends BaseEntity<ID>, ID> implements CrudRepository<T, ID> {
    private final ConcurrentHashMap<ID, T> storage = new ConcurrentHashMap<>();

    @Override
    public <S extends T> S save(S entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> storage.put(entity.getId(), entity));

        return entities;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }

    @Override
    public Iterable<T> findAll() {
        return storage.values();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return findAll().
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public void deleteById(ID id) {
        storage.remove(id);
    }

    @Override
    public void delete(T entity) {
        storage.remove(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(entity -> storage.remove(entity.getId()));
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
