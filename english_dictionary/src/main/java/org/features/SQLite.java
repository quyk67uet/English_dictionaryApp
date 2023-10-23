package org.features;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLite {

    private static final String databaseLocation = "jdbc:sqlite:english_dictionary/src/main/resources/assets/databases/en-vi.db";
    private static Connection connection;
    
    private static boolean checkSQLite() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Error on checking");
            return false;
        }
    }

    private static Connection getConnection() {
        try {
           connection = DriverManager.getConnection(databaseLocation); 
           System.out.println("Setup successfully");
           return connection;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error on using database!");
            e.printStackTrace();
            return null;

        }

    } 
    public static void main(String[] args) {
        checkSQLite();
        getConnection();
    }
}
