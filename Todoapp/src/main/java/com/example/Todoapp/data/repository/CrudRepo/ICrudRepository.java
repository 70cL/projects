package com.example.Todoapp.data.repository.CrudRepo;

import java.util.Optional;

public interface ICrudRepository<T, ID> {
    long count();

    void delete(T entity);

    void deleteAll();

    void deleteAll(Iterable<? extends T> entities);

    void deleteById(ID id);

    boolean exitsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> save(Iterable<S> entities);
}
