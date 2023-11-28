package com.asunawesker.java.jdbc.jdbc.repository;

import java.sql.Connection;
import java.util.List;

public interface Repository<T>{
    List<T> getAll();
    T getById(Long id);
    T save(T t);
    void delete(Long id);

    void setConnection(Connection connection);
}
