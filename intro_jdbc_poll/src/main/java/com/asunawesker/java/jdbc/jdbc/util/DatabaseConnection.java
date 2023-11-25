package com.asunawesker.java.jdbc.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    /* Connection Pooling
    * Data access pattern with the main purpose of reduce the overhead involved in performing database connections
    * and read/write database operations
    * */

    private static String URL = "jdbc:postgresql://172.17.0.2:5432/java_course?currentSchema=intro_db";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";
    private static BasicDataSource POOL;

    private static BasicDataSource getInstance() throws SQLException {
        if(POOL == null){
            POOL = new BasicDataSource();
            POOL.setUrl(URL);
            POOL.setUsername(USERNAME);
            POOL.setPassword(PASSWORD);
            POOL.setInitialSize(3);
            POOL.setMinIdle(3);
            POOL.setMaxIdle(5);
            POOL.setMaxTotal(5);
        }
        return POOL;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
