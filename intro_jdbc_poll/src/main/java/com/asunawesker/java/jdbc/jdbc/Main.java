package com.asunawesker.java.jdbc.jdbc;


import com.asunawesker.java.jdbc.jdbc.model.Category;
import com.asunawesker.java.jdbc.jdbc.model.Product;
import com.asunawesker.java.jdbc.jdbc.repository.CategoryRepository;
import com.asunawesker.java.jdbc.jdbc.repository.ProductRepository;
import com.asunawesker.java.jdbc.jdbc.repository.Repository;
import com.asunawesker.java.jdbc.jdbc.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class Main {
    static Logger LOGGER = Logger.getLogger(Main.class.getName());

    /* try-with-resources
    The try-with-resources statement is a try statement that declares one or more resources. A resource is an object
    that must be closed after the program is finished with it. The try-with-resources statement ensures that each
    resource is closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which includes
    all objects which implement java.io.Closeable, can be used as a resource.
     */

    /* Transactions
    * Enable ypu to control if and when changes area applied to the database. It treats a single SQL statement or a group
    * of SQL statements as one logical unit, and if any statement fails, the whole transaction fails.
    *   - Turn off auto-commit as line 33.
    *   - Once you are done with your changes, and you want to commit the changes then call commit() method on connection
    *   object as line 48.
    *   - Otherwise, to roll back updates to the database made using a Connection object as line 51.
    * */
    public static void main(String[] args) {
        products();
        categories();
    }

    private static void products(){
        try (Connection connection = DatabaseConnection.getConnection();){
            if(connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }
            try{
                Repository<Product> repository = new ProductRepository();
                repository.getAll().forEach(product -> LOGGER.info("Name: " + product.getName() + " Price: " + product.getPrice()));
                Product product = new Product();
                Category category = new Category();
                category.setId(1L);
                product.setId(4L);
                product.setName("Keyboard with mouse");
                product.setPrice(45.0);
                product.setRegistrationDate(new Date());
                product.setCategory(category);
                repository.save(product);
                LOGGER.info("Name: " + repository.getById(1L).getName() + " Price: " + repository.getById(1L).getPrice());
                repository.delete(5L);
                connection.commit();
            } catch (SQLException e){
                DatabaseConnection.getConnection().rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void categories(){
        try (Connection connection = DatabaseConnection.getConnection()) {
            if(connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }
            try{
                Repository<Category> categoryRepository = new CategoryRepository();
                categoryRepository.getAll().forEach(c -> LOGGER.info("Name: "+c.getName()));
                Category category = categoryRepository.getById(2L);
                LOGGER.info("Name: " + category.getName());
                Category categorySave = new Category();
                categorySave.setName("Furniture");
                LOGGER.info("New category: " + categoryRepository.save(categorySave).getName());
                categoryRepository.save(new Category(2L, "Furnishings"));
                categoryRepository.delete(2L);
                connection.commit();
            } catch (SQLException e){
                DatabaseConnection.getConnection().rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}