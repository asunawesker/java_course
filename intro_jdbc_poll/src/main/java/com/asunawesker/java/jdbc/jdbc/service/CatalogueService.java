package com.asunawesker.java.jdbc.jdbc.service;

import com.asunawesker.java.jdbc.jdbc.model.Category;
import com.asunawesker.java.jdbc.jdbc.model.Product;
import com.asunawesker.java.jdbc.jdbc.repository.CategoryRepository;
import com.asunawesker.java.jdbc.jdbc.repository.ProductRepository;
import com.asunawesker.java.jdbc.jdbc.repository.Repository;
import com.asunawesker.java.jdbc.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogueService implements Service{
    private Repository<Product> productRepository;
    private Repository<Category> categoryRepository;

    public CatalogueService(){
        this.productRepository = new ProductRepository();
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    public List<Product> getAllProducts() {
        try(Connection connection = DatabaseConnection.getConnection()){
            productRepository.setConnection(connection);
            return productRepository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(Long id) {
        try(Connection connection = DatabaseConnection.getConnection()){
            productRepository.setConnection(connection);
            return productRepository.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        try(Connection connection = DatabaseConnection.getConnection()){
            productRepository.setConnection(connection);
            return productRepository.save(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try(Connection connection = DatabaseConnection.getConnection()){
            productRepository.setConnection(connection);
            productRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        try(Connection connection = DatabaseConnection.getConnection()){
            categoryRepository.setConnection(connection);
            return categoryRepository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        try(Connection connection = DatabaseConnection.getConnection()){
            categoryRepository.setConnection(connection);
            return categoryRepository.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category saveCategory(Category category) {
        try(Connection connection = DatabaseConnection.getConnection()){
            categoryRepository.setConnection(connection);
            return categoryRepository.save(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try(Connection connection = DatabaseConnection.getConnection()){
            categoryRepository.setConnection(connection);
            categoryRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
