package com.asunawesker.java.jdbc;

import com.asunawesker.java.jdbc.model.Category;
import com.asunawesker.java.jdbc.model.Product;
import com.asunawesker.java.jdbc.repository.ProductRepository;
import com.asunawesker.java.jdbc.repository.Repository;
import com.asunawesker.java.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.Date;

public class Main {
    static Logger LOGGER = Logger.getLogger(Main.class.getName());

    /*
    The try-with-resources statement is a try statement that declares one or more resources. A resource is an object
    that must be closed after the program is finished with it. The try-with-resources statement ensures that each
    resource is closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which includes
    all objects which implement java.io.Closeable, can be used as a resource.
     */
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance();){
            Repository<Product> repository = new ProductRepository();
//            repository.getAll().forEach(product -> LOGGER.info("Name: " + product.getName() + " Price: " + product.getPrice()));
//            Product product = new Product();
//            Category category = new Category();
//            category.setId(1L);
//            product.setId(4L);
//            product.setName("Keyboard with mouse");
//            product.setPrice(45.0);
//            product.setRegistrationDate(new Date());
//            product.setCategory(category);
//            repository.save(product);
//            LOGGER.info("Name: " + repository.getById(1L).getName() + " Price: " + repository.getById(1L).getPrice());
//            repository.delete(5L);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}