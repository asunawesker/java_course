package com.asunawesker.java.jdbc.jdbc;


import com.asunawesker.java.jdbc.jdbc.model.Category;
import com.asunawesker.java.jdbc.jdbc.model.Product;
import com.asunawesker.java.jdbc.jdbc.repository.CategoryRepository;
import com.asunawesker.java.jdbc.jdbc.repository.ProductRepository;
import com.asunawesker.java.jdbc.jdbc.repository.Repository;
import com.asunawesker.java.jdbc.jdbc.service.CatalogueService;
import com.asunawesker.java.jdbc.jdbc.service.Service;
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
    * Enable you to control if and when changes area applied to the database. It treats a single SQL statement or a group
    * of SQL statements as one logical unit, and if any statement fails, the whole transaction fails.
    *   - Turn off auto-commit as line 33.
    *   - Once you are done with your changes, and you want to commit the changes then call commit() method on connection
    *   object as line 48.
    *   - Otherwise, to roll back updates to the database made using a Connection object as line 51.
    * */
    public static void main(String[] args) throws SQLException {
        Service service = new CatalogueService();

        service.getAllProducts().forEach(p ->
                LOGGER.info("Name: " + p.getName() + " Category: " + p.getCategory().getName()));

        service.getAllCategories().forEach(c -> LOGGER.info("Name: " + c.getName()));
    }

}