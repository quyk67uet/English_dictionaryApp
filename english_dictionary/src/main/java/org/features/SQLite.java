package org.features;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SQLite {
    private static final String databaseLocation = "jdbc:sqlite:english_dictionary/src/main/resources/assets/databases/en-vi.db";
    private static Connection connection;

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

    private static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(databaseLocation); 
        System.out.println("Setup successfully");
        return connection;
    }

    public static Set<String> getAllWordDatabase() {
        String query = "SELECT word FROM av";
        Set<String> wordDatabase = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wordDatabase.add(resultSet.getString("word"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other error.");
            e.printStackTrace();
        }
        return wordDatabase;
    }

    public boolean deleteWordDatabase(String word) {
        String query = "DELETE word FROM av WHERE word = \"" + word + "\"";
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateWordDatabase(String word, String html) {
        String query = "UPDATE av SET html = \"" + html + "\"" + " WHERE word = \"" + word + "\"";
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertWordDatabase(String word, String html) {
        String query = "INSERT INTO av(word, html) VALUES (\"" + word + "\"" + ", \"" + html + "\")";
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String wordHTML(String word) {
        String query = "SELECT html FROM av WHERE word = \"" + word + "\"";
        String wordHtml = "";
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            wordHtml = resultSet.getString("html");
        } catch (SQLException e) {
            System.out.println("SQL exceptions found in wordHTML");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other exceptions found in wordHTML");
            e.printStackTrace();
        }
        return wordHtml;
    }

    public static String wordDescription(String word) {
        String query = "SELECT description FROM av WHERE word = \"" + word + "\"" ;
        String description = "";
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            description = resultSet.getString("description");
        } catch (SQLException e) {
            System.out.println("SQL exceptions found in wordDescription");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other exceptions found in wordDescription");
            e.printStackTrace();
        }
        return description;
    }

    public String wordPronunciation(String word) {
        String query = "SELECT pronunciation FROM av WHERE word = \"" + word + "\"";
        String wordHtml = "";
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            wordHtml = resultSet.getString("pronunciation");
        } catch (SQLException e) {
            System.out.println("SQL exceptions found in wordPronunciation");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Other exceptions found in wordPronunciation");
            e.printStackTrace();
        }
        return wordHtml;
    }

    public static void main(String[] args) {
        checkSQLite();

        System.out.println(wordDescription("dog's ear"));
    }
}
