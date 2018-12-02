package com.example.spring.databasemock.test;

import com.example.spring.databasemock.app.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

/**
 * Created by mtumilowicz on 2018-12-02.
 */
public class InMemoryCrudRepository<T extends BaseEntity<ID>, ID> implements CrudRepository<T, ID> {
    private final ConcurrentHashMap<ID, T> storage = new ConcurrentHashMap<>();

    @Override
    public <S extends T> S save(S entity) {
        if (storage.containsKey(entity.getId())) {
            throw new IllegalStateException();
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);

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
        var idsSet = new HashSet<ID>();
        ids.forEach(idsSet::add);

        return storage.entrySet()
                .stream()
                .filter(entry -> idsSet.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(toList());
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
