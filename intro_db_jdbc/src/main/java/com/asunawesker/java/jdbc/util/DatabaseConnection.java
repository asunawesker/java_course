package com.asunawesker.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String URL = "jdbc:postgresql://172.17.0.2:5432/java_course?currentSchema=intro_db";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";
    private static Connection CONNECTION;

    public static Connection getInstance() throws SQLException {
        if(CONNECTION == null){
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return CONNECTION;
    }
}
