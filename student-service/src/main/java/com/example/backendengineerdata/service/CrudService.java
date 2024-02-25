package com.example.backendengineerdata.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T,ID> {
    long count() throws Exception;

    String save(T entity) throws Exception;

    void update(T entity) throws Exception;

    boolean deleteById(ID pk)throws Exception;

    Optional<T> findById(ID pk) throws Exception;
    List<T> findAll() throws Exception;

    boolean existsById(ID pk) throws Exception;
}
