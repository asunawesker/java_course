package com.asunawesker;

import java.sql.*;
import java.util.logging.Logger;

public class Main {
    static Logger LOGGER = Logger.getLogger(Main.class.getName());

    /*
    The try-with-resources statement is a try statement that declares one or more resources. A resource is an object
    that must be closed after the program is finished with it. The try-with-resources statement ensures that each
    resource is closed at the end of the statement. Any object that implements java.lang.AutoCloseable, which includes
    all objects which implement java.io.Closeable, can be used as a resource.
     */
    public static void main(String[] args) {
        String url = "jdbc:postgresql://172.17.0.2:5432/java_course?currentSchema=intro_db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products");){

            while (resultSet.next()){
                LOGGER.info("NAME: " + resultSet.getString("name"));
                LOGGER.info("PRICE: " +  resultSet.getString("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}