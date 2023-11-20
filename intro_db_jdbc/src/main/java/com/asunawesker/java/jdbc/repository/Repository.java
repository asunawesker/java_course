package com.asunawesker.java.jdbc.repository;

import java.util.List;

public interface Repository<T>{
    List<T> getAll();
    T getById(Long id);
    void save(T t);
    void delete(Long id);
}
