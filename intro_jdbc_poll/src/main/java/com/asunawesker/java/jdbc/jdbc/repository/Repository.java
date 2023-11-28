package com.asunawesker.java.jdbc.jdbc.repository;

import java.util.List;

public interface Repository<T>{
    List<T> getAll();
    T getById(Long id);
    T save(T t);
    void delete(Long id);
}
