package com.asunawesker.java.jdbc.repository;

import com.asunawesker.java.jdbc.model.Product;
import com.asunawesker.java.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
            while(resultSet.next()){
                products.add(new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("registration_date")));
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
                .prepareStatement("SELECT * FROM products WHERE id = ?");) {
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery();){
                if(resultSet.next()){
                    product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getDate("registration_date"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void save(Product product) {
        String sql = null;
        if (product.getId() != null && product.getId() > 0) {
            sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO products (name, price, registration_date) VALUES (?, ?, ?)";
        }
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            if (product.getId() != null && product.getId() > 0) {
                statement.setLong(3, product.getId());
            } else {
                statement.setDate(3, new Date(product.getRegistrationDate().getTime()));
            }
            statement.executeUpdate();
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
}
