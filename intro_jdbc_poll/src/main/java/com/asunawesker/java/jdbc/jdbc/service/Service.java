package com.asunawesker.java.jdbc.jdbc.service;

import com.asunawesker.java.jdbc.jdbc.model.Category;
import com.asunawesker.java.jdbc.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Product> getAllProducts() throws SQLException;

    Product getProductById(Long id) throws SQLException;

    Product saveProduct(Product product) throws SQLException;

    void deleteProduct(Long id) throws SQLException;

    List<Category> getAllCategories() throws SQLException;

    Category getCategoryById(Long id) throws SQLException;

    Category saveCategory(Category category) throws SQLException;

    void deleteCategory(Long id) throws SQLException;
}
