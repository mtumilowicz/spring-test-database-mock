package com.example.spring.databasemock.test;

import com.example.spring.databasemock.app.BaseEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by mtumilowicz on 2018-12-02.
 */
public class InMemoryBasicJpaRepository<T extends BaseEntity<ID>, ID>
        implements JpaRepository<T, ID> {

    private final ConcurrentHashMap<ID, T> storage = new ConcurrentHashMap<>();

    @Override
    public List<T> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(), pageable, storage.size());
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        var idsSet = new HashSet<ID>();
        ids.forEach(idsSet::add);

        return findAll().stream()
                .filter(entity -> idsSet.contains(entity.getId()))
                .collect(Collectors.toList());
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

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean existsById(ID id) {
        return storage.containsKey(id);
    }

    @Override
    public <S extends T> S save(S entity) {
        if (storage.containsKey(entity.getId())) {
            throw new IllegalStateException();
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);

        var toReturn = new LinkedList<S>();
        entities.forEach(toReturn::add);
        return toReturn;
    }

    @Override
    public List<T> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getOne(ID id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }
}
