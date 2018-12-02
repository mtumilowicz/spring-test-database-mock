package com.example.spring.databasemock;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    public <S extends T> S save(S entity) {
        return null;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }

    @Override
    public List<T> findAll(Sort sort) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public T getOne(ID id) {
        return null;
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return false;
    }
}
