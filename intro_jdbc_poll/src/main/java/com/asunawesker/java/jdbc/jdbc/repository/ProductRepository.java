package com.asunawesker.java.jdbc.jdbc.repository;

import com.asunawesker.java.jdbc.jdbc.model.Category;
import com.asunawesker.java.jdbc.jdbc.model.Product;
import com.asunawesker.java.jdbc.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT p.*, c.name as category FROM products as p INNER JOIN categories as c ON (p.category_id = c.id)")) {
            while(resultSet.next()){
                products.add(createProduct(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product getById(Long id) {
        Product product = null;
        try(PreparedStatement statement = getConnection()
                .prepareStatement("SELECT p.*, c.name as category FROM products as p INNER JOIN categories as c ON (p.category_id = c.id) WHERE id = ?");) {
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery();){
                if(resultSet.next()){
                    product = createProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Product save(Product product) {
        String sql = null;
        if (product.getId() != null && product.getId() > 0) {
            sql = "UPDATE products SET name = ?, price = ?, category_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO products (name, price, category_id, registration_date) VALUES (?, ?, ?, ?)";
        }
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getCategory().getId());
            if (product.getId() != null && product.getId() > 0) {
                statement.setLong(4, product.getId());
            } else {
                statement.setDate(4, new Date(product.getRegistrationDate().getTime()));
            }
            statement.executeUpdate();

            if(product.getId() == null){
                try(ResultSet resultSet = statement.getGeneratedKeys()){
                    if(resultSet.next()){
                        product.setId(resultSet.getLong(1));
                    }
                }
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement statement = getConnection()
                .prepareStatement("DELETE FROM products WHERE id = ?");) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Product createProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getDate("registration_date"),
                new Category(
                        resultSet.getLong("category_id"),
                        resultSet.getString("category")
                ));
    }
}
