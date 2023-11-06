package org.features;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SQLite {
    private static final String databaseLocation = "jdbc:sqlite:english_dictionary/src/main/resources/assets/databases/en-vi.db";
    private static Connection connection = getConnection();

    private static boolean checkSQLite() {
        boolean sqLite = false;
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            sqLite = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Error on starting SQLite.");
        }

        if (sqLite) {
            System.out.println("SQLite connected!");
        }
        return sqLite;
    }

    private static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(databaseLocation);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllWordDatabase() throws SQLException {
        String query = "SELECT * FROM engvie";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getTableDatabase(String databaseTable) throws SQLException {
        String query = "SELECT * FROM " + databaseTable +  " ORDER BY id DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public boolean deleteRowDatabase(String databaseTable, String word) {
        String query = "DELETE FROM " + databaseTable +  " WHERE word = \"" + word + "\"";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createTableDatabase(String databaseTable) {
        String query = "CREATE TABLE IF NOT EXISTS " + databaseTable + " (\n" + "id INTEGER PRIMARY KEY,\n" 
                        + "word TEXT,\n" + "html TEXT,\n" + "description TEXT,\n" + "pronunciation TEXT\n);";
        // String query = "ALTER TABLE history AUTO_INCREMENT = 1, modify column id int AUTO_INCREMENT;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
        return true;

    }

    public boolean insertWordDatabase(String word, String html, String description) {
        // deleteRowDatabase("engvie", word);
        String query = "INSERT INTO engvie(word, html, description) VALUES (\'" + word + "\'" + ",\'" + html + "\'" + ",\'" + description + "\')";
        try {
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertTableDatabase(String databaseTable, String word) {
        String query = "INSERT INTO  " + databaseTable + " (word, html, description, pronunciation)\n"
                        + "SELECT word, html, description, pronunciation\n"
                        + "FROM engvie\n"
                        + "WHERE word = \"" + word + "\";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean updateWordDatabase(String databaseTable, String column, String word, String content) {
        String query = "UPDATE " + databaseTable + " SET " + column + " = \"" + content + "\"" + " WHERE word = \"" + word + "\";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String wordProperty(String column, String word) {
        String query = "SELECT " + column + " FROM engvie WHERE word = \"" + word + "\"";
        String property = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            property = resultSet.getString(column);
        } catch (SQLException e) {
            System.out.println("SQL exceptions found in wordHTML");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other exceptions found in wordHTML");
            e.printStackTrace();
        }
        return property;
    }

    public static void main(String[] args) {
        // checkSQLite();

        // System.out.println(wordDescription("dog's ear"));
    }
}
