package org.features.dictionary;

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
    private static final String databaseLocation = "jdbc:sqlite:english_dictionary/src/main/resources/assets/datas/en-vi.db";
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

    public ResultSet getTableDatabase(String databaseTable, int number) throws SQLException {
        String query = "SELECT * FROM " + databaseTable + " ORDER BY RANDOM() LIMIT " + Integer.toString(number);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet getTableDatabase(String databaseTable, String condition, int number) throws SQLException {
        String query = "SELECT * FROM " + databaseTable + " WHERE " + condition + " ORDER BY RANDOM() LIMIT " + Integer.toString(number);
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet; 
    }

    public boolean deleteRowDatabase(String databaseTable, String word) {
        String query = "DELETE FROM " + databaseTable +  " WHERE word = \'" + word + "\'";
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
        // String query = "DELETE FROM engvie\r\n" + //
        //         "WHERE rowid NOT IN (\r\n" + //
        //         "    SELECT MAX(rowid)\r\n" + //
        //         "    FROM engvie\r\n" + //
        //         "    GROUP BY word\r\n" + //
        //         ");";
        // String query = "DELETE c1 FROM contacts c1 INNER JOIN contacts c2 WHERE c1.id > c2.id AND c1.email = c2.email;";
        // String query = "DROP TABLE history";
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
        String query = "INSERT INTO " + databaseTable + " (word, html, description, pronunciation)\n"
                        + "SELECT DISTINCT word, html, description, pronunciation\n"
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
        String query = "UPDATE " + databaseTable + " SET " + column + " = '" + content.replace("'", "''") + "'" + " WHERE word = \"" + word + "\";";
        System.out.println(query);
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
        String query = "SELECT " + column + " FROM engvie WHERE word = \'" + word + "\'";
        String property = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                property += resultSet.getString(column);
            }
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
        
    }
}
